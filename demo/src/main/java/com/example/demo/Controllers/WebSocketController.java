package com.example.demo.Controllers;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import java.io.IOException;
import java.nio.file.Files;

@RestController
public class WebSocketController {

    @Autowired
    private SimpMessagingTemplate template;

    @Autowired
    private ResourceLoader resourceLoader;

    @GetMapping("/websocket/game/{gameId}")
    public String getGame(@PathVariable String gameId) throws IOException {
        // Load the JSON file from the resources directory
        Resource resource = resourceLoader.getResource("classpath:games/game1/" + gameId + ".json");
        // Read the file into a String
        String json = new String(Files.readAllBytes(resource.getFile().toPath()));

        // Send a message to all subscribed clients
        template.convertAndSend("/topic/game", json);

        // Return the JSON string
        return json;
    }
}
