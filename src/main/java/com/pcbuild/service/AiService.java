package com.pcbuild.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import java.util.*;

@Service
public class AiService {

    @Value("${groq.api.key}")
    private String groqApiKey;

    private final WebClient webClient = WebClient.builder()
            .baseUrl("https://api.groq.com")
            .build();

    private static final String SYSTEM_PROMPT = """
            You are an expert PC building assistant for a PC building website in India.
            Help users choose PC components based on their budget and use case.
            Always recommend specific components like CPU, GPU, RAM, Storage, Motherboard, PSU, Cabinet.
            Give prices in Indian Rupees (₹). Be concise and friendly.
            Focus on: Gaming PCs, Video Editing PCs, Office/Work PCs.
            Always ask about budget first if not provided.
            """;

    public String chat(List<Map<String, String>> userMessages) {
        List<Map<String, String>> messages = new ArrayList<>();
        messages.add(Map.of("role", "system", "content", SYSTEM_PROMPT));
        messages.addAll(userMessages);

        Map<String, Object> requestBody = Map.of(
                "model", "llama-3.3-70b-versatile",
                "messages", messages
        );

        try {
            Map response = webClient.post()
                    .uri("/openai/v1/chat/completions")
                    .header("Authorization", "Bearer " + groqApiKey)
                    .header("Content-Type", "application/json")
                    .bodyValue(requestBody)
                    .retrieve()
                    .bodyToMono(Map.class)
                    .block();

            List<Map> choices = (List<Map>) response.get("choices");
            Map message = (Map) choices.get(0).get("message");
            return (String) message.get("content");

        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }
}
