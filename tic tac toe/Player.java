/**
 * Interface representing a player in the Tic-Tac-Toe game.
 */
public interface Player {

    /**
     * Executes the player's turn on the given board.
     *
     * @param board the current game board
     * @param mark the player's mark (X or O) to place on the board
     */
    void playTurn(Board board, Mark mark);
}
