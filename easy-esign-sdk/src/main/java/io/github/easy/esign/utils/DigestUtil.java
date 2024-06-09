package io.github.easy.esign.utils;

import io.github.easy.esign.core.error.ESignException;
import lombok.SneakyThrows;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class DigestUtil {
    private static final String MD5_ALGORITHM_NAME = "MD5";

    public static byte[] md5Digest(byte[] bytes) {
        return digest(bytes);
    }

    private static MessageDigest getDigest() {
        try {
            return MessageDigest.getInstance(DigestUtil.MD5_ALGORITHM_NAME);
        } catch (NoSuchAlgorithmException var2) {
            throw new IllegalStateException("Could not find MessageDigest with algorithm \"" + DigestUtil.MD5_ALGORITHM_NAME + "\"", var2);
        }
    }

    private static byte[] digest(byte[] bytes) {
        return getDigest().digest(bytes);
    }

    @SneakyThrows
    public static String getStringContentMd5(File file) throws IOException {
        byte[] md5Bytes;
        try (FileInputStream fis = new FileInputStream(file)) {
            MessageDigest md5 = MessageDigest.getInstance(MD5_ALGORITHM_NAME);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = fis.read(buffer, 0, 1024)) != -1) {
                md5.update(buffer, 0, length);
            }
            md5Bytes = md5.digest();
            return Base64Util.encodeToString(md5Bytes);
        }
    }

    /***
     * 计算请求签名值
     *
     * @param message 待签名字符串
     * @param secret  密钥APP KEY
     * @return HmacSHA256计算后摘要值的Base64编码
     */
    public static String signatureBase64(String message, String secret) {
        String algorithm = "HmacSHA256";
        Mac hmacSha256;
        try {
            hmacSha256 = Mac.getInstance(algorithm);
        } catch (NoSuchAlgorithmException e) {
            throw new ESignException(e);
        }
        byte[] keyBytes = secret.getBytes(StandardCharsets.UTF_8);
        byte[] messageBytes = message.getBytes(StandardCharsets.UTF_8);
        try {
            hmacSha256.init(new SecretKeySpec(keyBytes, 0, keyBytes.length, algorithm));
        } catch (InvalidKeyException e) {
            throw new ESignException(e);
        }
        // 使用HmacSHA256对二进制数据消息Bytes计算摘要
        byte[] digestBytes = hmacSha256.doFinal(messageBytes);
        // 把摘要后的结果digestBytes使用Base64进行编码
        return Base64Util.encodeToString(digestBytes);
    }
}
