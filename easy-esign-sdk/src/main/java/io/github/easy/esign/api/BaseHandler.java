package io.github.easy.esign.api;

import io.github.easy.esign.core.ESignManager;
import io.github.easy.esign.core.config.ESignConfig;
import io.github.easy.esign.core.error.ESignExecution;
import io.github.easy.esign.core.log.Logger;
import io.github.easy.esign.core.log.LoggerFactory;
import io.github.easy.esign.struct.ESignResp;
import io.github.easy.esign.utils.Base64Util;
import io.github.easy.esign.utils.DigestUtil;
import io.github.easy.esign.utils.JsonUtil;
import io.github.easy.esign.utils.StrUtil;
import okhttp3.*;
import org.jetbrains.annotations.NotNull;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public final class BaseHandler {

    private static ESignConfig config;


    public BaseHandler(ESignConfig config) {
        if (config == null) {
            config = ESignManager.getConfig();
        }
        BaseHandler.config = config;
        if (!config.getLazyInit()) {
            init();
        }
    }

    private static OkHttpClient httpClient;

    private final static Logger log = LoggerFactory.getLogger(BaseHandler.class);

    // 沙盒地址
    private static final String endpointSandbox = "https://smlopenapi.esign.cn";
    private static final String endpoint = "https://openapi.esign.cn";
    static final String CONTENT_TYPE = "application/json; charset=UTF-8";
    static final String PDF_CONTENT_TYPE = "application/pdf;";


    private void init() {
        httpClient = new OkHttpClient().newBuilder()
                .addInterceptor(chain -> {
                    log.debug("method: %s", chain.request().method());
                    log.debug("url: %s", chain.request().url().url());
                    log.debug("headers: \n%s", chain.request().headers());
                    return chain.proceed(chain.request());
                })
                .build();
    }

    <T> ESignResp<T> post(String path, Object request, Class<T> clazz) {
        String text = request(path, "POST", JsonUtil.toJson(request));
        return JsonUtil.parseObject(text, ESignResp.class, clazz);
    }

    private <T> ESignResp<T> post(String path) {
        String text = request(path, "POST", null);
        return JsonUtil.parseObject(text, ESignResp.class);
    }

    <T> ESignResp<T> get(String path, Class<T> clazz) {
        String text = request(path, "GET", "");
        return JsonUtil.parseObject(text, ESignResp.class, clazz);
    }

    @NotNull
    private String request(String path, String method, String payload) {
        if (httpClient == null) {
            init();
        }
        String contentType = CONTENT_TYPE;
        if (method.equals("GET") || method.equals("DELETE")) {
            if (StrUtil.isBlank(payload)) {
                contentType = "";
            }
        }
        String url = getUrl(path);

        //
        // 请求签名鉴权方式说明：https://open.esign.cn/doc/opendoc/dev-guide3/tggw2e
        //
        String contentMD5 = StrUtil.isBlank(payload) ? "" : Base64Util.encodeToString(DigestUtil.md5Digest(payload.getBytes()));

        String pathAndParameters = url.replace(getUrl(""), "");

        String signString = String.join("\n", method, "*/*", contentMD5, contentType, "", pathAndParameters);
        String sign = doSignatureBase64(signString, config.getSecret());

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
        Response r;
        try {
            r = httpClient.newCall(httpRequest).execute();
        } catch (IOException e) {
            throw new ESignExecution(e);
        }
        if (!r.isSuccessful()) {
            log.error("request err: " + r);
            return null;
        }

        String text;
        try {
            text = r.body().string();
        } catch (IOException e) {
            throw new ESignExecution(e);
        }
        log.info("response: " + text);
        return text;
    }

    @NotNull
    private String getUrl(String path) {
        return (config.getSandbox() ? endpointSandbox : endpoint) + path;
    }


    /***
     * 计算请求签名值
     *
     * @param message 待签名字符串
     * @param secret  密钥APP KEY
     * @return HmacSHA256计算后摘要值的Base64编码
     */
    @SuppressWarnings("all")
    private String doSignatureBase64(String message, String secret) {
        String algorithm = "HmacSHA256";
        Mac hmacSha256;
        try {
            hmacSha256 = Mac.getInstance(algorithm);
        } catch (NoSuchAlgorithmException e) {
            throw new ESignExecution(e);
        }
        byte[] keyBytes = secret.getBytes(StandardCharsets.UTF_8);
        byte[] messageBytes = message.getBytes(StandardCharsets.UTF_8);
        try {
            hmacSha256.init(new SecretKeySpec(keyBytes, 0, keyBytes.length, algorithm));
        } catch (InvalidKeyException e) {
            throw new ESignExecution(e);
        }
        // 使用HmacSHA256对二进制数据消息Bytes计算摘要
        byte[] digestBytes = hmacSha256.doFinal(messageBytes);
        // 把摘要后的结果digestBytes使用Base64进行编码
        return Base64Util.encodeToString(digestBytes);
    }

    OkHttpClient getHttpClient() {
        return httpClient;
    }
}
