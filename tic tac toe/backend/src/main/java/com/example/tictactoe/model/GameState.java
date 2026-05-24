package com.example.tictactoe.model;

public class GameState {
    private String[] board;
    private String currentPlayer;
    private String winner; // "X","O", or null
    private boolean draw;
    private String status; // e.g., "playing", "finished"

    public GameState() {}

    public GameState(String[] board, String currentPlayer, String winner, boolean draw, String status) {
        this.board = board;
        this.currentPlayer = currentPlayer;
        this.winner = winner;
        this.draw = draw;
        this.status = status;
    }

    public String[] getBoard() { return board; }
    public void setBoard(String[] board) { this.board = board; }
    public String getCurrentPlayer() { return currentPlayer; }
    public void setCurrentPlayer(String currentPlayer) { this.currentPlayer = currentPlayer; }
    public String getWinner() { return winner; }
    public void setWinner(String winner) { this.winner = winner; }
    public boolean isDraw() { return draw; }
    public void setDraw(boolean draw) { this.draw = draw; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
