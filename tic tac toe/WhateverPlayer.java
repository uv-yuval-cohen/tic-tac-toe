import java.util.Random;

/**
 * Represents a player that places marks randomly on the board.
 * This player does not follow a specific strategy and simply chooses an empty spot at random.
 */
public class WhateverPlayer implements Player {

    private final Random random = new Random(); // Random generator for selecting row and column

    /**
     * Default constructor for WhateverPlayer.
     */
    public WhateverPlayer() {
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

}
