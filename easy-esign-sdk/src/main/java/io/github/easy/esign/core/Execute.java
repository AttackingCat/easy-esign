package io.github.easy.esign.core;

import io.github.easy.esign.core.config.ESignConfig;
import io.github.easy.esign.core.error.ESignException;
import io.github.easy.esign.core.log.Logger;
import io.github.easy.esign.core.log.LoggerFactory;
import io.github.easy.esign.struct.ESignResp;
import io.github.easy.esign.utils.Base64Util;
import io.github.easy.esign.utils.StrUtil;
import lombok.Synchronized;
import okhttp3.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static io.github.easy.esign.core.constant.Constant.*;
import static io.github.easy.esign.utils.DigestUtil.md5Digest;
import static io.github.easy.esign.utils.DigestUtil.signatureBase64;
import static io.github.easy.esign.utils.JsonUtil.*;

public final class Execute {

    private static ESignConfig config;

    private final static Map<String, Logger> loggerMap = new HashMap<>();

    private static OkHttpClient httpClient;

    private final static Logger baseLog = LoggerFactory.getLogger(Execute.class);

    public Execute(ESignConfig config) {
        if (config == null) {
            config = Manager.getConfig();
        }
        Execute.config = config;
        init();
    }

    @Synchronized
    public static void setLog(Class<?> clazz) {
        if (clazz == null) {
            return;
        }
        if (loggerMap.get(clazz.getName()) == null) {
            loggerMap.put(clazz.getName(), LoggerFactory.getLogger(clazz));
        }
    }

    private Logger getLog() {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        String key = null;
        for (StackTraceElement stackTraceElement : stackTrace) {
            if (stackTraceElement.getClassName().contains("io.github.easy.esign.core.api")) {
                key = stackTraceElement.getClassName();
            }
        }
        return loggerMap.getOrDefault(key, baseLog);
    }

    @Synchronized
    private void init() {
        if (httpClient == null) {
            httpClient = new OkHttpClient().newBuilder()
                    .addInterceptor(chain -> {
                        baseLog.debug("method: %s", chain.request().method());
                        baseLog.debug("url: %s", chain.request().url().url());
                        baseLog.debug("headers: \n%s", chain.request().headers());
                        return chain.proceed(chain.request());
                    })
                    .build();
        }
    }

    public <T> ESignResp<T> post(String path, Object request, Class<T> clazz) {
        String text = request(path, POST, toJsonStr(request));
        return parseESignResp(text, clazz);
    }

    /*<T> ESignResp<T> post(String path) {
        String text = request(path, POST, null);
        return parseObject(text, ESignResp.class);
    }*/

    public <T> ESignResp<T> get(String path, Class<T> clazz) {
        String text = request(path, GET, "");
        return parseESignResp(text, clazz);
    }

    private String request(String path, String method, String payload) {
        Logger log = getLog();

        String contentType = JSON_CT;
        if (method.equals(GET) || method.equals(DELETE)) {
            if (StrUtil.isBlank(payload)) {
                contentType = "";
            }
        }
        String url = getUrl(path);

        // signature doc：https://open.esign.cn/doc/opendoc/dev-guide3/tggw2e
        String contentMD5 = StrUtil.isBlank(payload) ? "" : Base64Util.encodeToString(md5Digest(payload.getBytes()));

        String pathAndParameters = url.replace(getUrl(""), "");

        String signString = String.join("\n", method, "*/*", contentMD5, contentType, "", pathAndParameters);
        String sign = signatureBase64(signString, config.getSecret());

        Request.Builder builder = new Request.Builder()
                .url(url)
                .header("X-Tsign-Open-App-Id", config.getAppId())
                .header("X-Tsign-Open-Auth-Mode", "Signature")
                .header("X-Tsign-Open-Ca-Timestamp", String.valueOf(System.currentTimeMillis()))
                .header("X-Tsign-Open-Ca-Signature", sign)
                .header("Accept", "*/*")
                .header("Date", "")
                .header("Content-MD5", contentMD5)
                .header("Content-Type", contentType);

        RequestBody body = null;
        if (StrUtil.isNotBlank(payload)) {
            body = RequestBody.create(payload, MediaType.parse(JSON_CT));
        }
        builder.method(method, body);

        Request httpRequest = builder.build();
        log.info("path: " + path);
        log.info("payload: " + payload);

        try (Response resp = httpClient.newCall(httpRequest).execute()) {
            if (resp.body() == null) {
                throw new ESignException("response body is null");
            }
            if (!resp.isSuccessful()) {
                String msg = String.format("request failed: %s", resp.body().string());
                throw new ESignException(msg);
            }
            String text = resp.body().string();
            log.info("response: " + text);
            return text;
        } catch (IOException e) {
            throw new ESignException(e);
        }
    }

    private static String getUrl(String path) {
        return (config.getSandbox() ? endpointSandbox : endpoint) + path;
    }


    public OkHttpClient getHttpClient() {
        return httpClient;
    }

    public void close() {
        httpClient.connectionPool().evictAll();
    }
}
