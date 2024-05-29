package io.github.easy.esign.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
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

    public static String getStringContentMd5(File file) throws NoSuchAlgorithmException, IOException {
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
}
