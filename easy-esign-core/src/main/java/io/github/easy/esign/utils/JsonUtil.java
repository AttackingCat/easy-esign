package io.github.easy.esign.utils;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.easy.esign.error.ESignException;
import io.github.easy.esign.struct.ESignResp;

public class JsonUtil {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    static {
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }


    public static String toJsonStr(Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (Exception e) {
            throw new ESignException(e);
        }
    }

    public static <T> T parseObject(String text, Class<T> clazz) {
        if (StrUtil.isBlank(text)) {
            return null;
        }
        try {
            return objectMapper.readValue(text, clazz);
        } catch (Exception e) {
            throw new ESignException(e);
        }
    }

    public static <T, R> T parseObject(String text, Class<T> mainType, Class<R> clazz) {
        try {
            JavaType type = objectMapper.getTypeFactory().constructParametricType(mainType, clazz);
            return objectMapper.readValue(text, type);
        } catch (Exception e) {
            throw new ESignException(e);
        }
    }

    public static <T> ESignResp<T> parseESignResp(String text, Class<T> clazz) {
        try {
            JavaType type = objectMapper.getTypeFactory().constructParametricType(ESignResp.class, clazz);
            return objectMapper.readValue(text, type);
        } catch (Exception e) {
            throw new ESignException(e);
        }
    }

    public static JsonNode parseString(String text) {
        try {
            return objectMapper.readTree(text);
        } catch (Exception e) {
            throw new ESignException(e);
        }
    }

}
