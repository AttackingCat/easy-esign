package io.github.easy.esign.error;

import com.fasterxml.jackson.databind.JsonNode;
import io.github.easy.esign.utils.JsonUtil;
import lombok.Data;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ErrorCodeDefine {
    private static final String API_URL = "https://tapi.esign.cn/open/public/standard/error/query/";

    private final static OkHttpClient client = new OkHttpClient();

    public static List<Explanation> searchCauseByCode(String code) {
        Request request = new Request.Builder()
                .url(API_URL + code)
                .build();
        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException();
            }
            List<Explanation> result = new ArrayList<>();

            String respBody = Objects.requireNonNull(response.body()).string();
            JsonNode jsonNode = JsonUtil.parseString(respBody);
            jsonNode.path("data").forEach(node -> {
                Explanation explanation = new Explanation();
                explanation.setBizKeyId(node.get("bizKeyId").asText());
                explanation.setDescription(node.get("description").asText());
                String explain = node.get("errorCodeExplanation").asText().replaceAll("<[^>]*>", "");
                explanation.setErrorCodeExplanation(explain);
                result.add(explanation);
            });
            return result;
        } catch (Exception e) {
            throw new ESignException("Network abnormality, please check the configuration or try again later");
        }
    }

    @Data
    public static class Explanation {
        private String bizKeyId;
        private String description;
        private String errorCodeExplanation;
    }
}
