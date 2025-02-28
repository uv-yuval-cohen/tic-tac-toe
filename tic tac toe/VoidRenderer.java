/**
 * A no-operation renderer that does not display the board.
 * Useful for running games without rendering output.
 */
public class VoidRenderer implements Renderer {

    /**
     * Default constructor for VoidRenderer.
     * Does not require any initialization.
     */
    public VoidRenderer() {
        // No initialization required
    }

    /**
     * Does nothing, as this renderer is intended to not display the board.
     *
     * @param board the game board (ignored in this implementation)
     */
    public void renderBoard(Board board) {
        return;
    }
}
