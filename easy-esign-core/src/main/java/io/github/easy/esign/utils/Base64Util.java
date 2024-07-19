package io.github.easy.esign.utils;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class Base64Util {
    private static final Charset DEFAULT_CHARSET = StandardCharsets.UTF_8;

    public static byte[] encode(byte[] src) {
        return src.length == 0 ? src : Base64.getEncoder().encode(src);
    }

    public static String encodeToString(byte[] src) {
        return src.length == 0 ? "" : new String(encode(src), DEFAULT_CHARSET);
    }
}
