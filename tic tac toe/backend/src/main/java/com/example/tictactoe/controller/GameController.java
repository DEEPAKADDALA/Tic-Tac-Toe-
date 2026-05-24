package com.example.tictactoe.controller;

import com.example.tictactoe.model.GameState;
import com.example.tictactoe.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class GameController {

    @Autowired
    private GameService gameService;

    @GetMapping("/state")
    public GameState state() {
        return gameService.getState();
    }

    @PostMapping("/move")
    public GameState move(@RequestParam int pos) {
        GameState state = gameService.makeMove(pos);
        if ("playing".equals(state.getStatus()) && "O".equals(state.getCurrentPlayer())) {
            state = gameService.aiMove();
        }
        return state;
    }

    @PostMapping("/ai-move")
    public GameState aiMove() {
        return gameService.aiMove();
    }

    @PostMapping("/reset")
    public GameState reset() {
        return gameService.reset();
    }
}
