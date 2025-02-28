import java.util.Random;

/**
 * Represents a player that places marks randomly on the board.
 * This player does not follow a specific strategy and simply chooses an empty spot at random.
 */
public class CleverPlayer implements Player {

    private static final int STREAK = 3;
    private final Random random = new Random(); // Random generator for selecting row and column

    /**
     * Default constructor for WhateverPlayer.
     */
    public CleverPlayer() {
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
            //there's an immidiate win or lose, we must act accordingly
            return;
        }
        //act like whatever player
        int row, col;
        int size = board.getSize();
        while (true) {
            // Generate random row and column within board size
            row = random.nextInt(size);
            col = random.nextInt(size);

            // Check if the chosen cell is empty, and place the mark if it is
            if (board.getMark(row,col) == Mark.BLANK && board.putMark(mark, row, col)) {
                break; // Valid move found, exit loop
            }
        }
        // Turn ended successfully
    }

    private boolean thinkOneStepAhead(Board board, Mark mark) {
        int size = board.getSize();
        Mark opponentMark = (mark == Mark.X) ? Mark.O : Mark.X;

        // Loop through each cell on the board
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {

                if (board.getMark(row, col) != Mark.BLANK) {
                    // dont consider used cells
                    continue;
                }
                // empty cell
                // Check if this move can complete a streak for the current player
                if (isWinningMove(board, row, col, mark)) {
                    board.putMark(mark, row, col); //Win the game
                    return true; // Winning move found, exit
                }


            }
        }
        return false; // No winning or blocking move found
    }

    private boolean isWinningMove(Board board, int row, int col, Mark mark) {
        // Check each direction for a potential winning line
        return checkDirection(board, row, col, mark, 1, 0) // Horizontal
                || checkDirection(board, row, col, mark, 0, 1) // Vertical
                || checkDirection(board, row, col, mark, 1, 1) // Diagonal down-right
                || checkDirection(board, row, col, mark, 1, -1); // Diagonal down-left
    }

    private boolean checkDirection(Board board, int row, int col,
                                   Mark mark, int rowDelta, int colDelta) {
        int count = 1; // Start with the current cell
        int size = board.getSize();

        // Check in the positive direction
        for (int i = 1; i < STREAK; i++) {
            int newRow = row + i * rowDelta;
            int newCol = col + i * colDelta;
            if (newRow < 0 || newRow >= size || newCol < 0 || newCol >= size ||
                    board.getMark(newRow, newCol) != mark) {
                break;
            }
            count++;
        }

        // Check in the negative direction
        for (int i = 1; i < STREAK; i++) {
            int newRow = row - i * rowDelta;
            int newCol = col - i * colDelta;
            if (newRow < 0 || newRow >= size || newCol < 0 || newCol >= size ||
                    board.getMark(newRow, newCol) != mark) {
                break;
            }
            count++;
        }

        return count >= STREAK;
    }

}
