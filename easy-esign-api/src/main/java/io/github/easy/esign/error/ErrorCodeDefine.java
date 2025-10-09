package io.github.easy.esign.error;

import com.fasterxml.jackson.databind.JsonNode;
import io.github.easy.esign.log.Logger;
import io.github.easy.esign.log.LoggerFactory;
import io.github.easy.esign.utils.JsonUtil;
import lombok.Data;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class ErrorCodeDefine {

    private static final Logger log = LoggerFactory.getLogger(ErrorCodeDefine.class);

    private static final String API_URL = "https://tapi.esign.cn/open/public/standard/error/query/";

    // 带超时的 OkHttpClient
    private static final OkHttpClient client = new OkHttpClient.Builder()
            .connectTimeout(2, java.util.concurrent.TimeUnit.SECONDS)
            .readTimeout(2, java.util.concurrent.TimeUnit.SECONDS)
            .build();

    // 缓存：code -> CacheEntry
    private static final Map<String, CacheEntry> cache = new ConcurrentHashMap<>();

    // 缓存有效期（毫秒）
    private static final long CACHE_TTL = 5 * 60 * 1000L; // 5 分钟

    public static List<Explanation> searchCauseByCode(String code) {
        CacheEntry entry = cache.get(code);
        if (entry != null && !entry.isExpired()) {
            return entry.getExplanations();
        }

        Request request = new Request.Builder()
                .url(API_URL + code)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                log.warn("Failed to fetch explanation for code: {}, status={}", code, response.code());
                return fallback(code);
            }

            String respBody = Objects.requireNonNull(response.body()).string();
            JsonNode jsonNode = JsonUtil.parseString(respBody);

            List<Explanation> result = new ArrayList<>();
            jsonNode.path("data").forEach(node -> {
                Explanation explanation = new Explanation();
                explanation.setBizKeyId(node.get("bizKeyId").asText());
                explanation.setDescription(node.get("description").asText());
                String explain = node.get("errorCodeExplanation").asText().replaceAll("<[^>]*>", "");
                explanation.setErrorCodeExplanation(explain);
                result.add(explanation);
            });

            // 放入缓存
            cache.put(code, new CacheEntry(result, System.currentTimeMillis()));

            return result;
        } catch (IOException e) {
            log.warn("Error explanation fetch failed for code={}, reason={}", code, e.getMessage());
            return fallback(code);
        }
    }

    private static List<Explanation> fallback(String code) {
        CacheEntry entry = cache.get(code);
        if (entry != null) {
            log.info("Using cached explanation for code={}", code);
            return entry.getExplanations();
        }
        return Collections.emptyList();
    }

    // 缓存条目
    private static class CacheEntry {
        private final List<Explanation> explanations;
        private final long timestamp;

        CacheEntry(List<Explanation> explanations, long timestamp) {
            this.explanations = explanations;
            this.timestamp = timestamp;
        }

        boolean isExpired() {
            return System.currentTimeMillis() - timestamp > CACHE_TTL;
        }

        List<Explanation> getExplanations() {
            return explanations;
        }
    }

    @Data
    public static class Explanation {
        private String bizKeyId;
        private String description;
        private String errorCodeExplanation;
    }
}
