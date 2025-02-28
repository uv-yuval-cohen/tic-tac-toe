import java.util.Random;

public class GeniusPlayer implements Player {
    // player programed to achieve streaks of length 3
    private static final int DESIRED_STREAK_LENGTH = 3;
    private final Random random = new Random(); // Random generator for selecting row and column

    /**
     * Default constructor for WhateverPlayer.
     */
    public GeniusPlayer() {
        // No parameters as specified in instructions
    }

    /**
     * Randomly selects an empty cell on the board and places the mark.
     *
     * @param board The current game board where the move will be made.
     * @param mark  The mark (X or O) representing the player making the move.
     */
    @Override
    public void playTurn(Board board, Mark mark) {
        if (thinkOneStepAhead(board, mark)) {
            //there's an immediate win or lose, we acted accordingly
            return;
        }
        //act like whatever player
        int row, column;
        int size = board.getSize();
        while (true) {
            // Generate random row and column within board size
            row = random.nextInt(size);
            column = random.nextInt(size);

            // Check if the chosen cell is empty, and place the mark if it is
            if (board.getMark(row,column) == Mark.BLANK && board.putMark(mark, row, column)) {
                break; // Valid move found, exit loop
            }
        }
        // Turn ended successfully
    }

    private boolean thinkOneStepAhead(Board board, Mark mark) {
        int size = board.getSize();
        Mark opponentMark = (mark == Mark.X) ? Mark.O : Mark.X;

        // Check for a winning move first
        for (int row = 0; row < size; row++) {
            for (int column = 0; column < size; column++) {
                if (board.getMark(row, column) == Mark.BLANK && isWinningMove(board, row, column, mark)) {
                    board.putMark(mark, row, column); // Win the game
                    return true; // Winning move found
                }
            }
        }

        // If no winning move, check for a blocking move
        for (int row = 0; row < size; row++) {
            for (int column = 0; column < size; column++) {
                if (board.getMark(row, column) == Mark.BLANK &&
                        isWinningMove(board, row, column, opponentMark)) {
                    board.putMark(mark, row, column); // Block the opponent
                    return true; // Blocking move found
                }
            }
        }

        return false; // No winning or blocking move found
    }


    private boolean isWinningMove(Board board, int row, int column, Mark mark) {
        // Check each direction for a potential winning line
        return checkDirection(board, row, column, mark, 1, 0) // Horizontal
                || checkDirection(board, row, column, mark, 0, 1) // Vertical
                || checkDirection(board, row, column, mark, 1, 1) // Diagonal down-right
                || checkDirection(board, row, column, mark, 1, -1); // Diagonal down-left
    }

    private boolean checkDirection(Board board, int row,
                                   int column, Mark mark, int rowDelta, int columnDelta) {
        int count = 1; // Start with the current cell
        int size = board.getSize();

        // Check in the positive direction
        for (int i = 1; i < DESIRED_STREAK_LENGTH; i++) {
            int newRow = row + i * rowDelta;
            int newCol = column + i * columnDelta;
            if (newRow < 0 || newRow >= size || newCol < 0 || newCol >= size ||
                    board.getMark(newRow, newCol) != mark) {
                break;
            }
            count++;
        }

        // Check in the negative direction
        for (int i = 1; i < DESIRED_STREAK_LENGTH; i++) {
            int newRow = row - i * rowDelta;
            int newCol = column - i * columnDelta;
            if (newRow < 0 || newRow >= size || newCol < 0 || newCol >= size ||
                    board.getMark(newRow, newCol) != mark) {
                break;
            }
            count++;
        }

        return count >= DESIRED_STREAK_LENGTH;
    }
}
