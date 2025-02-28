/**
 * Interface for rendering a Tic-Tac-Toe game board.
 */
public interface Renderer {

    /**
     * Renders the current state of the game board.
     *
     * @param board the Tic-Tac-Toe board to be displayed
     */
    void renderBoard(Board board);
}
