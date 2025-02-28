/**
 * Manages a single game of Tic-Tac-Toe, handling board, players, renderer, and win conditions.
 */
public class Game {
    //====== INSTANCE VARIABLES ======
    private final Board board; // The game board
    private final Player playerX; // Player representing 'X'
    private final Player playerO; // Player representing 'O'
    private final Renderer renderer; // Renderer to display the board
    private int winStreak = 3; // Default win streak requirement

    private Player nextTurn; // Tracks whose turn it is

    private Mark winnerMark = Mark.BLANK; // Tracks the winner's mark, initially BLANK

    // Direction vectors for right, down, diagonal down-right, and diagonal down-left
    private static final int[][] DIRECTIONS = {
            {0, 1},  // Right
            {1, 0},  // Down
            {1, 1},  // Diagonal down-right
            {1, -1}  // Diagonal down-left
    };

    //====== CONSTRUCTORS ======

    /**
     * Default constructor initializes a game with default board size and win streak.
     *
     * @param playerX   Player assigned to mark X
     * @param playerO   Player assigned to mark O
     * @param renderer  Renderer to display the board
     */
    Game(Player playerX, Player playerO, Renderer renderer) {
        this.playerO = playerO;
        this.playerX = playerX;
        this.board = new Board();
        this.renderer = renderer;
        this.nextTurn = playerX; // Player X starts by default
    }

    /**
     * Constructor that allows setting custom board size and win streak.
     *
     * @param playerX   Player assigned to mark X
     * @param playerO   Player assigned to mark O
     * @param size      The board size (n x n)
     * @param winStreak The number of consecutive marks required to win
     * @param renderer  Renderer to display the board
     */
    Game(Player playerX, Player playerO, int size, int winStreak, Renderer renderer) {
        this.board = new Board(size);
        if (winStreak <= size && winStreak >= 2) { // Ensure winStreak is within valid range
            this.winStreak = winStreak;
        }
        this.playerO = playerO;
        this.playerX = playerX;
        this.renderer = renderer;
        this.nextTurn = playerX; // Player X starts by default

    }

    //====== GETTERS ======

    /**
     * Gets the win streak required to win the game.
     *
     * @return the win streak value
     */
    public int getWinStreak() {
        return winStreak;
    }

    /**
     * Gets the board size.
     *
     * @return the board size
     */
    public int getBoardSize() {
        return board.getSize();
    }

    /**
     * Runs the game loop until a player wins or the board is full.
     *
     * @return the Mark of the winner or BLANK if there is a draw
     */
    public Mark run() {
        while (doesGameContinue()) {
            // Current player takes their turn
            Mark currentMark = (nextTurn == playerX) ? Mark.X : Mark.O;
            nextTurn.playTurn(board, currentMark);

            // Render the board after each turn
            renderer.renderBoard(board);

            // Switch turns to the other player
            switchTurns();
        }
        return winnerMark; // Returns the winning mark or BLANK if it’s a tie
    }

    //====== HELPERS ======

    /**
     * Switches the turn to the other player.
     */
    private void switchTurns() {
        nextTurn = (nextTurn == playerX) ? playerO : playerX;
    }

    /**
     * Checks if the game should continue.
     * The game continues if there is no winner and the board is not full.
     *
     * @return true if the game should continue, false otherwise
     */
    private boolean doesGameContinue() {
        return !isThereAWinner() && !isItATie(); // Continue if no winner and not a tie
    }

    /**
     * Checks if there is a winning streak on the board.
     * Updates winnerMark if a winner is found.
     *
     * @return true if a player has won, false otherwise
     */
    private boolean isThereAWinner() {
        for (int row = 0; row < board.getSize(); row++) {
            for (int col = 0; col < board.getSize(); col++) {
                if (board.getMark(row, col) != Mark.BLANK) {
                    for (int[] direction : DIRECTIONS) {
                        if (checkDirection(row, col, direction[0], direction[1])) {
                            winnerMark = board.getMark(row, col); // Set winner's mark
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    /**
     * Checks if a winning streak exists starting from a given cell in a specific direction.
     *
     * @param row       the starting row
     * @param col       the starting column
     * @param rowDelta  the row increment direction
     * @param colDelta  the column increment direction
     * @return true if a winning streak is found in this direction, false otherwise
     */
    private boolean checkDirection(int row, int col, int rowDelta, int colDelta) {
        Mark startMark = board.getMark(row, col);

        for (int i = 1; i < winStreak; i++) {
            int newRow = row + i * rowDelta;
            int newCol = col + i * colDelta;

            // Check bounds
            if (isOutOfBounds(newRow, newCol)) {
                return false;
            }

            // Check if the mark matches
            if (board.getMark(newRow, newCol) != startMark) {
                return false;
            }
        }
        return true;  // All marks in this direction match
    }

    /**
     * Checks if a cell is out of board bounds.
     *
     * @param row the row index
     * @param col the column index
     * @return true if the cell is out of bounds, false otherwise
     */
    private boolean isOutOfBounds(int row, int col) {
        return row < 0 || row >= board.getSize() || col < 0 || col >= board.getSize();
    }

    /**
     * Checks if the game is a tie (board is full and no winner).
     *
     * @return true if the game is a tie, false otherwise
     */
    private boolean isItATie() {
        for (int row = 0; row < board.getSize(); row++) {
            for (int col = 0; col < board.getSize(); col++) {
                if (board.getMark(row, col) == Mark.BLANK) {
                    return false; // If there's a blank spot, it's not a tie
                }
            }
        }
        return true; // No blank spots found, so it's a tie
    }
}
