package com.example.tictactoe.service;

import com.example.tictactoe.model.GameState;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GameService {
    // 0 = empty, 1 = X, -1 = O
    private int[] board = new int[9];
    private int currentPlayer = 1; // X starts

    public GameService() { reset(); }

    public synchronized GameState getState() {
        String[] b = new String[9];
        for (int i = 0; i < 9; i++) {
            if (board[i] == 1) b[i] = "X";
            else if (board[i] == -1) b[i] = "O";
            else b[i] = "";
        }
        Integer winner = checkWinner();
        String win = null;
        boolean draw = false;
        String status = "playing";
        if (winner != null) {
            status = "finished";
            win = winner == 1 ? "X" : "O";
        } else if (isFull()) {
            status = "finished";
            draw = true;
        }
        return new GameState(b, currentPlayer == 1 ? "X" : "O", win, draw, status);
    }

    public synchronized GameState reset() {
        for (int i = 0; i < 9; i++) board[i] = 0;
        currentPlayer = 1;
        return getState();
    }

    public synchronized GameState makeMove(int pos) {
        if (pos < 0 || pos >= 9) return getState();
        if (board[pos] != 0) return getState();
        board[pos] = currentPlayer;
        Integer winner = checkWinner();
        if (winner == null) {
            currentPlayer = -currentPlayer;
        }
        return getState();
    }

    public synchronized GameState aiMove() {
        // AI plays as O (-1)
        int bestScore = Integer.MIN_VALUE;
        int bestMove = -1;
        for (int i = 0; i < 9; i++) {
            if (board[i] == 0) {
                board[i] = -1;
                int score = minimax(board, 0, false);
                board[i] = 0;
                if (score > bestScore) {
                    bestScore = score;
                    bestMove = i;
                }
            }
        }
        if (bestMove != -1) {
            board[bestMove] = -1;
            Integer winner = checkWinner();
            if (winner == null) currentPlayer = 1; else currentPlayer = winner;
        }
        return getState();
    }

    private int minimax(int[] state, int depth, boolean isMaximizing) {
        Integer winner = checkWinner(state);
        if (winner != null) {
            if (winner == -1) return 10 - depth; // O (AI) wins
            if (winner == 1) return depth - 10;  // X wins
        }
        if (isBoardFull(state)) return 0;

        if (isMaximizing) {
            int best = Integer.MIN_VALUE;
            for (int i = 0; i < 9; i++) {
                if (state[i] == 0) {
                    state[i] = -1;
                    int val = minimax(state, depth + 1, false);
                    state[i] = 0;
                    best = Math.max(best, val);
                }
            }
            return best;
        } else {
            int best = Integer.MAX_VALUE;
            for (int i = 0; i < 9; i++) {
                if (state[i] == 0) {
                    state[i] = 1;
                    int val = minimax(state, depth + 1, true);
                    state[i] = 0;
                    best = Math.min(best, val);
                }
            }
            return best;
        }
    }

    private boolean isBoardFull(int[] s) {
        for (int v : s) if (v == 0) return false;
        return true;
    }

    private boolean isFull() { return isBoardFull(board); }

    private Integer checkWinner() { return checkWinner(board); }

    private Integer checkWinner(int[] s) {
        int[][] wins = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
        for (int[] w : wins) {
            int sum = s[w[0]] + s[w[1]] + s[w[2]];
            if (sum == 3) return 1;
            if (sum == -3) return -1;
        }
        return null;
    }
}
