package com.pcbuild.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.pcbuild.service.AiService;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/ai")
public class AiController {

    @Autowired
    private AiService aiService;

    @PostMapping("/chat")
    public ResponseEntity<Map<String, String>> chat(@RequestBody Map<String, List<Map<String, String>>> request) {
        List<Map<String, String>> messages = request.get("messages");
        String reply = aiService.chat(messages);
        return ResponseEntity.ok(Map.of("reply", reply));
    }
}
