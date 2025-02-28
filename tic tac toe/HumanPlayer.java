/**
 * Represents a human player in a Tic-Tac-Toe game.
 * This player provides input for their moves via the console.
 */
public class HumanPlayer implements Player {


    /**
     * Default constructor for HumanPlayer.
     */
    public HumanPlayer() {
        // No parameters as specified in instructions
    }

    /**
     * Prompts the human player to enter their move coordinates, validates the input,
     * and places the mark on the board if the move is valid.
     *
     * @param board The current game board where the move will be made.
     * @param mark  The mark (X or O) representing the player making the move.
     */
    public void playTurn(Board board, Mark mark) {
        int row, column;
        // Ask for coordinates based on the player's mark
        System.out.println(humanPlayerInputRequest(mark.toString()));
        while (true) {


            // Take input from the user
            int userInput = KeyboardInput.readInt();


            // Check if the input is a two-digit number
            if (userInput < 0 || userInput > 99) {
                System.out.println("Invalid mark position. Please choose a different position:");
                continue; // Ask for input again if not two digits
            }

            // Extract row and column from the two-digit input
            row = userInput / 10; // First digit
            column = userInput % 10; // Second digit

            // Attempt to place the mark on the board; if successful, exit loop
            if (board.putMark(mark, row, column)) {
                break;
            }
        }
        // Turn ended successfully
    }

    private String humanPlayerInputRequest(String markSymbol) {
        return "Player " + markSymbol + ", type coordinates: ";

    }
}
