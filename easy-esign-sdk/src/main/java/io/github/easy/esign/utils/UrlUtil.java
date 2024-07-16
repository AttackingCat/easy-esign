package io.github.easy.esign.utils;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.easy.esign.core.error.ESignException;
import lombok.SneakyThrows;

import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

public class UrlUtil {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    static {
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    @SneakyThrows
    public static String toParam(Object params) {
        JsonNode jsonNode = objectMapper.valueToTree(params);
        return toParam(jsonNode);
    }

    public static String fmtPathUrl(String url, Object... params) {
        for (int i = 0; i < params.length; i++) {
            url = url.replace("{" + i + "}", params[i].toString());
        }
        return url;
    }


    private static String toParam(JsonNode node) {
        StringBuilder urlParams = new StringBuilder();
        Iterator<Map.Entry<String, JsonNode>> fields = node.fields();

        // dictionary sorting
        Map<String, String> sortedParams = new TreeMap<>();
        while (fields.hasNext()) {
            Map.Entry<String, JsonNode> entry = fields.next();
            String key = entry.getKey();
            JsonNode valueNode = entry.getValue();
            if (valueNode.isNull()) {
                continue;
            }
            String value = valueNode.asText();
            sortedParams.put(key, value);
        }

        sortedParams.forEach((key, value) -> {
            if (urlParams.length() > 0) {
                urlParams.append("&");
            }
            try {
                urlParams.append(key)
                        .append("=")
                        .append(value);
            } catch (Exception e) {
                throw new ESignException("Error encoding URL parameter", e);
            }
        });
        if (urlParams.length() > 0) {
            urlParams.insert(0, "?");
        }
        return urlParams.toString();
    }

}
