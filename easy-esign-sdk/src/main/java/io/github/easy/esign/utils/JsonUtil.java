package io.github.easy.esign.utils;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.easy.esign.core.error.ESignExecution;

public class JsonUtil {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    static {
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }


    public static String toJson(Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (Exception e) {
            throw new ESignExecution(e);
        }
    }

    public static <T> T parseObject(String text, Class<T> clazz) {
        if (StrUtil.isBlank(text)) {
            return null;
        }
        try {
            return objectMapper.readValue(text, clazz);
        } catch (Exception e) {
            throw new ESignExecution(e);
        }
    }

    public static <T> T parseObject(String text, Class<T> mainType, Class<?> clazz) {
        try {
            JavaType type = objectMapper.getTypeFactory().constructParametricType(mainType, clazz);
            return objectMapper.readValue(text, type);
        } catch (Exception e) {
            throw new ESignExecution(e);
        }
    }
}
