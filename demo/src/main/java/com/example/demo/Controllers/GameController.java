package com.example.demo.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

@RestController
public class GameController {

    @Autowired
    private ResourceLoader resourceLoader;


    @GetMapping("/")
    public String helloWorld() {
        return "Hello World";
    }

    @GetMapping("/game/{gameId}")
    public String getGame(@PathVariable String gameId) throws IOException {
        // Load the JSON file from the resources directory
        Resource resource = resourceLoader.getResource("classpath:games/game1/" + gameId + ".json");
        // Read the file into a String
        String json = new String(Files.readAllBytes(resource.getFile().toPath()));
        // Return the JSON string
        return json;
    }
}