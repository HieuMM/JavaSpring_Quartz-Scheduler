package com.example.quartzscheduler;

import com.example.quartzscheduler.playground.PlaygroundService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/timer")
public class Controller {
    private final PlaygroundService playgroundService;

    public Controller(PlaygroundService playgroundService) {
        this.playgroundService = playgroundService;
    }

    @PostMapping("/runHelloWorldJob")
    public void runHelloWorldJob() {
        playgroundService.runHelloWorldJob();
    }
}
