/**
 * A factory class for creating Renderer objects.
 * Responsible for constructing and returning the appropriate Renderer implementation
 * based on the specified type and board size.
 */
public class RendererFactory {

    /**
     * Default constructor for RendererFactory.
     * No initialization is required for this factory.
     */
    RendererFactory() {
        // Empty constructor
    }

    /**
     * Constructs and returns a Renderer based on the specified type and size.
     *
     * @param type The type of renderer to build (e.g., "console" or "void").
     * @param size The size of the board, used by some renderers if needed.
     * @return A Renderer object of the specified type, or null if the type is unrecognized.
     */
    public Renderer buildRenderer(String type, int size) {
        switch (type.toLowerCase()) {
            case "console":
                return new ConsoleRenderer(size); // Renderer for console output
            case "void":
                return new VoidRenderer(); // Renderer that does nothing
            default:
                return null; // Return null for unknown types
        }
    }
}
