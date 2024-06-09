package io.github.easy.esign.core;

import io.github.easy.esign.core.config.ESignConfig;
import io.github.easy.esign.core.error.ESignException;
import io.github.easy.esign.core.log.Logger;
import io.github.easy.esign.core.log.LoggerFactory;
import io.github.easy.esign.struct.ESignResp;
import io.github.easy.esign.utils.Base64Util;
import io.github.easy.esign.utils.StrUtil;
import okhttp3.*;

import java.io.IOException;

import static io.github.easy.esign.utils.DigestUtil.md5Digest;
import static io.github.easy.esign.utils.DigestUtil.signatureBase64;
import static io.github.easy.esign.utils.JsonUtil.*;

public final class BaseExecute {

    private static ESignConfig config;

    public static final String DELETE = "DELETE";
    public static final String GET = "GET";
    public static final String POST = "POST";
    public static final String PUT = "PUT";


    public BaseExecute(ESignConfig config, Class<?> clazz) {
        log = LoggerFactory.getLogger(clazz);
        if (config == null) {
            config = ESignManager.getConfig();
        }
        BaseExecute.config = config;
        /* if (!config.getLazyInit()) {
            init();
        }*/
        init();
    }

    private static OkHttpClient httpClient;

    private static Logger log;

    // sandbox url
    private static final String endpointSandbox = "https://smlopenapi.esign.cn";
    // prod url
    private static final String endpoint = "https://openapi.esign.cn";

    public static final String CONTENT_TYPE = "application/json; charset=UTF-8";
    public static final String PDF_CONTENT_TYPE = "application/pdf;";


    private static void init() {
        httpClient = new OkHttpClient().newBuilder()
                .addInterceptor(chain -> {
                    log.debug("method: %s", chain.request().method());
                    log.debug("url: %s", chain.request().url().url());
                    log.debug("headers: \n%s", chain.request().headers());
                    return chain.proceed(chain.request());
                })
                .build();
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
        String text = request(path, GET, null);
        return parseESignResp(text, clazz);
    }

    private static String request(String path, String method, String payload) {
//        if (httpClient == null) {
//            init();
//        }
        String contentType = CONTENT_TYPE;
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
            body = RequestBody.create(payload, MediaType.parse(CONTENT_TYPE));
        }
        builder.method(method, body);

        Request httpRequest = builder.build();
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
