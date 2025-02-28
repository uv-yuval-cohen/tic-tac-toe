/**
 * Enum representing the possible marks on the Tic-Tac-Toe board.
 * Includes BLANK, X, and O.
 */
public enum Mark {
    BLANK, // Represents an empty cell
    X,     // Represents the X mark
    O;     // Represents the O mark

    /**
     * Returns the string representation of the mark.
     * If the mark is BLANK, it returns null.
     *
     * @return the string representation of the mark, or null if BLANK.
     */
    @Override
    public String toString() {
        if (this == BLANK) {
            return null;
        }
        return this.name();
    }
}
