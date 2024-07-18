package io.github.easy.esign.core;

import io.github.easy.esign.core.config.ESignConfig;
import io.github.easy.esign.core.error.ESignException;
import io.github.easy.esign.core.error.ErrorCodeDefine;
import io.github.easy.esign.core.log.Logger;
import io.github.easy.esign.core.log.LoggerFactory;
import io.github.easy.esign.struct.ESignResp;
import io.github.easy.esign.utils.Base64Util;
import io.github.easy.esign.utils.JsonUtil;
import io.github.easy.esign.utils.StrUtil;
import lombok.Getter;
import lombok.SneakyThrows;
import lombok.Synchronized;
import okhttp3.*;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static io.github.easy.esign.core.constant.Constant.*;
import static io.github.easy.esign.utils.DigestUtil.md5Digest;
import static io.github.easy.esign.utils.DigestUtil.signatureBase64;
import static io.github.easy.esign.utils.JsonUtil.parseESignResp;
import static io.github.easy.esign.utils.JsonUtil.toJsonStr;

public final class Execute {

    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(Execute.class);
    private ESignConfig config;

    @Getter
    private final String appName;

    private static OkHttpClient httpClient;

    private final static Logger logger = LoggerFactory.getLogger(Execute.class);

    public Execute(ESignConfig config) {
        if (config == null) {
            throw new ESignException("config is null");
        }
        this.config = config;
        this.appName = config.getName();
        init();
    }

    @Synchronized
    private void init() {
        if (httpClient == null) {
            httpClient = new OkHttpClient().newBuilder()
                    .addInterceptor(chain -> {
                        logger.debug("method: %s", chain.request().method());
                        logger.debug("url: %s", chain.request().url().url());
                        logger.debug("headers: \n%s", chain.request().headers());
                        return handlerError(chain.proceed(chain.request()));
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
        logger.info("path: " + path);
        logger.info("payload: " + payload);

        try (Response resp = httpClient.newCall(httpRequest).execute()) {
            String text = Objects.requireNonNull(resp.body()).string();
            logger.info("response: " + text);
            return text;
        } catch (IOException e) {
            throw new ESignException(e);
        }
    }

    private String getUrl(String path) {
        return (config.getSandbox() ? endpointSandbox : endpoint) + path;
    }

    @SneakyThrows
    private Response handlerError(Response response) {
        if (response.body() == null) {
            throw new ESignException("response body is null");
        }
        String respStr = response.body().string();
        String failCode = "";
        String failMsg;
        if (!response.isSuccessful()) {
            failCode = String.valueOf(response.code());
            failMsg = JsonUtil.parseString(respStr).path("message").asText();
        } else {
            ESignResp<?> eSignResp = Objects.requireNonNull(JsonUtil.parseObject(respStr, ESignResp.class));
            if (eSignResp.getCode() != 0) {
                failCode = String.valueOf(eSignResp.getCode());
                failMsg = eSignResp.getMessage();
            } else {
                failMsg = "";
            }
        }

        if (StrUtil.isNotBlank(failCode)) {
            logger.error("AppId: " + config.getAppId());
            logger.error("Url: " + response.request().url().url());
            List<ErrorCodeDefine.Explanation> explanations = ErrorCodeDefine.searchCauseByCode(failCode);
            Optional<ErrorCodeDefine.Explanation> maybe = explanations
                    .stream()
                    .filter(e -> Objects.equals(e.getDescription(), failMsg))
                    .findAny();

            if (maybe.isPresent()) {
                logger.error("Error solution: " + maybe.get().getErrorCodeExplanation());
            } else {
                explanations.forEach(e -> logger.info("Error solution: " + e.getErrorCodeExplanation()));
            }
            logger.error("Document helper: " + "https://open.esign.cn/doc/opendoc/pdf-sign3/nx6lc2cfnhk4qaxt");
            throw new ESignException(respStr);
        }

        return response
                .newBuilder()
                .body(ResponseBody.create(respStr, response.body().contentType()))
                .code(response.code())
                .build();
    }


    public OkHttpClient getHttpClient() {
        return httpClient;
    }

    public void close() {
        httpClient.connectionPool().evictAll();
        config = null;
    }
}
