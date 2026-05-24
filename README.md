# Tic Tac Toe — Spring Boot

This is a simple Tic Tac Toe project with a Java Spring Boot backend and an improved static UI.

## Features

- Human plays as `X`
- AI plays as `O`
- AI move is made automatically after each valid human move
- Clean responsive UI with game status and score display
- Reset button to start a new game instantly

## Project structure

- `backend/` — Spring Boot application
  - `src/main/java/com/example/tictactoe` — backend application source files
  - `src/main/resources/static/index.html` — frontend UI
  - `pom.xml` — Maven build file

## Requirements

- Java 17 JDK
- Maven

## Run locally

```powershell
cd "C:/Users/pc/OneDrive/Desktop/tic tac toe/backend"
mvn spring-boot:run
```

Then open the browser at:

```text
http://localhost:8080
```

## API Endpoints

- `GET /api/state` — returns the current board and game state
- `POST /api/move?pos={0..8}` — make a human move at position `pos`; AI will respond automatically
- `POST /api/reset` — reset the game

## Gameplay notes

- The board positions are numbered left-to-right, top-to-bottom from `0` to `8`.
- The UI colors X in blue and O in pink.
- The game shows current score and status messages while playing.

## AI Agent

- Implementation: A deterministic minimax algorithm implemented in `backend/src/main/java/com/example/tictactoe/service/GameService.java`.
- Role: The AI plays as O and the human plays as X. After each valid human move the backend runs the AI move automatically.
- Evaluation: Terminal states are scored so the AI prefers faster wins:
  - AI win: `+ (10 - depth)`
  - Human win: `- (10 - depth)`
  - Draw: `0`
  Depth is used to prefer quicker wins and delay losses.
- Performance: The search branches with at most 9 moves initially and decreases each ply. Complexity is roughly O(b^d) (b ≤ 9, d ≤ 9) — small enough to run instantly in this game without pruning.
- Deterministic behavior: The current AI has no randomness (plays optimally), so games against it will be very hard to beat.
- Possible enhancements:
  - Add alpha–beta pruning to reduce nodes searched and improve speed.
  - Introduce a depth limit + heuristic to create difficulty levels.
  - Add slight randomness or move-ordering to make play less deterministic.
  - Replace or augment with MCTS or a learned policy for more varied play.
- Where to change: Modify `aiMove()` / `minimax()` in `GameService.java` to tweak scoring, add pruning, or implement difficulty modes.

## Files of interest

- `backend/src/main/java/com/example/tictactoe/TicTacToeApplication.java`
- `backend/src/main/java/com/example/tictactoe/controller/GameController.java`
- `backend/src/main/java/com/example/tictactoe/service/GameService.java`
- `backend/src/main/java/com/example/tictactoe/model/GameState.java`
- `backend/src/main/resources/static/index.html`

## Next improvements

- Add game history or move log
- Allow AI vs AI mode
- Add sound and animations for a richer UI
