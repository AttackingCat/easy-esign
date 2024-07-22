package io.github.easy.esign.utils;

import com.fasterxml.jackson.databind.JsonNode;
import io.github.easy.esign.struct.constant.Action;


public class CallbackUtil {
    @SuppressWarnings("unchecked")
    public static <T> T convert(String jsonStr) {
        JsonNode jsonNode = JsonUtil.parseString(jsonStr);
        String action = jsonNode.get("action").asText();
        if (StrUtil.isNotBlank(action)) {
            Action a = Action.getByAction(action);
            if (a != null) {
                Class<?> clazz = a.getClazz();
                Object res = JsonUtil.parseObject(jsonStr, clazz);
                try {
                    if (clazz.isInstance(res)) {
                        return (T) clazz.cast(res);
                    }
                } catch (ClassCastException e) {
                    throw new ClassCastException("Callback data type error");
                }
            }
        }
        return null;
    }

    public static <T> T ok() {
        return null;
    }

    public static <T> T fail() {
        return null;
    }
}


