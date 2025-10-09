package io.github.easy.esign.core;

import com.fasterxml.jackson.databind.JsonNode;
import io.github.easy.esign.config.ESignConfig;
import io.github.easy.esign.error.ESignException;
import io.github.easy.esign.error.ErrorCodeDefine;
import io.github.easy.esign.log.Logger;
import io.github.easy.esign.log.LoggerFactory;
import io.github.easy.esign.struct.ESignResp;
import io.github.easy.esign.utils.Base64Util;
import io.github.easy.esign.utils.JsonUtil;
import io.github.easy.esign.utils.StrUtil;
import lombok.Getter;
import lombok.SneakyThrows;
import lombok.Synchronized;
import okhttp3.*;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import static io.github.easy.esign.constant.ContentType.JSON_CT;
import static io.github.easy.esign.constant.Domain.endpoint;
import static io.github.easy.esign.constant.Domain.endpointSandbox;
import static io.github.easy.esign.constant.HttpMethod.*;
import static io.github.easy.esign.utils.DigestUtil.md5Digest;
import static io.github.easy.esign.utils.DigestUtil.signatureBase64;
import static io.github.easy.esign.utils.JsonUtil.parseESignResp;
import static io.github.easy.esign.utils.JsonUtil.toJsonStr;

public final class Execute {

    private final ESignConfig config;

    @Getter
    private final String appName;

    private static OkHttpClient httpClient;

    private final long connectTimeout;

    private final static Logger logger = LoggerFactory.getLogger(Execute.class);

    public Execute(ESignConfig config) {
        if (config == null) {
            throw new ESignException("config is null");
        }
        this.config = config;
        this.appName = config.getName();
        this.connectTimeout = config.getConnectTimeout();
        init();
    }

    @Synchronized
    private void init() {
        if (httpClient == null) {

            Proxy proxy = null;
            if (config.getProxy() != null) {
                proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(config.getProxy().getHostName(), config.getProxy().getPort()));
            }

            httpClient = new OkHttpClient().newBuilder()
                    .proxy(proxy)
                    .addInterceptor(chain -> {
                        logger.debug("method: {}", chain.request().method());
                        logger.debug("url: {}", chain.request().url().url());
                        logger.debug("headers: \n{}", chain.request().headers());
                        return handlerError(chain.proceed(chain.request()));
                    })
                    .connectTimeout(connectTimeout, TimeUnit.MILLISECONDS)
                    .build();
        }
    }

    public <T> ESignResp<T> post(String path, Object request, Class<T> clazz) {
        String text = request(path, POST, toJsonStr(request));
        return parseESignResp(text, clazz);
    }

    public <T> ESignResp<T> put(String path, Object request, Class<T> clazz) {
        String text = request(path, PUT, toJsonStr(request));
        return parseESignResp(text, clazz);
    }

    public ESignResp<String> put(String path, Object request) {
        String text = request(path, PUT, toJsonStr(request));
        return parseESignResp(text, String.class);
    }

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
        String failCode = null;
        String failMsg = null;
        JsonNode respJson = JsonUtil.parseString(respStr);
        if (!response.isSuccessful()) {
            failCode = String.valueOf(response.code());
            failMsg = respJson.path("message").asText();
        } else {
            if (!StrUtil.isBlank(respStr)) {
                String code = respJson.path("code").asText();
                if (!"0".equals(code)) {
                    failCode = code;
                    failMsg = respJson.path("message").asText();
                }
            }
        }

        if (StrUtil.isNotBlank(failCode)) {
            logger.warn("AppId: " + config.getAppId());
            logger.warn("Url: " + response.request().url().url());
            if (ESignManager.getAutoExplanation()) {
                List<ErrorCodeDefine.Explanation> explanations = ErrorCodeDefine.searchCauseByCode(failCode);
                String finalFailMsg = failMsg;
                Optional<ErrorCodeDefine.Explanation> maybe = explanations
                        .stream()
                        .filter(e -> Objects.equals(e.getDescription(), finalFailMsg))
                        .findAny();

                if (maybe.isPresent()) {
                    logger.warn("Error solution: " + maybe.get().getErrorCodeExplanation());
                } else {
                    explanations.forEach(e -> {
                        if (StrUtil.isNotBlank(e.getErrorCodeExplanation())) {
                            logger.warn("Error solution: " + e.getErrorCodeExplanation());
                        }
                    });
                }
            }
            logger.warn("Document helper: " + "https://open.esign.cn/doc/opendoc/pdf-sign3/nx6lc2cfnhk4qaxt");
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

    @Synchronized
    @SuppressWarnings("resource")
    public void close() {
        if (httpClient == null) return;

        Dispatcher dispatcher = httpClient.dispatcher();
        dispatcher.executorService().shutdown(); // 停止接收新请求

        try {
            if (!dispatcher.executorService().awaitTermination(connectTimeout, TimeUnit.MILLISECONDS)) {
                logger.warn("Execute[{}] still has active requests after {} ms", appName, connectTimeout);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            logger.warn("Execute[{}] closing interrupted", appName);
        }

        // 清理连接池和缓存
        httpClient.connectionPool().evictAll();
        if (httpClient.cache() != null) {
            try {
                httpClient.cache().close();
            } catch (IOException e) {
                logger.warn("Error closing http cache", e);
            }
        }

        httpClient = null;
    }
}
