/**
 * Represents a Tic-Tac-Toe board for an n x n game.
 * Handles board size, marking positions, and validating moves.
 */
public class Board {
    private static final int DEFAULT_BOARD_SIZE = 4; // Default board size
    private final int boardSize; // Current board size
    private Mark[][] board; // 2D array representing the board

    //====== CONSTRUCTORS ======

    /**
     * Default constructor initializes a board with the default size.
     */
    Board() {
        board = new Mark[DEFAULT_BOARD_SIZE][DEFAULT_BOARD_SIZE];
        this.boardSize = DEFAULT_BOARD_SIZE;
        initializeBoard();
    }

    /**
     * Constructor that allows setting a custom board size.
     *
     * @param size the size of the board (nxn)
     */
    Board(int size) {
        board = new Mark[size][size];
        boardSize = size;
        initializeBoard();
    }

    //====== GETTERS & SETTERS ======

    /**
     * Gets the size of the board (n).
     *
     * @return the size of one dimension of the square board
     */
    public int getSize() {
        return boardSize;
    }

    /**
     * Retrieves the mark at a specified position.
     *
     * @param row the row coordinate
     * @param column the column coordinate
     * @return the mark at the specified coordinates, or BLANK if invalid
     */
    public Mark getMark(int row, int column) {
        if (!checkCoordinates(row, column)) {
            return Mark.BLANK; // Invalid coordinates, return BLANK
        }
        return board[row][column];
    }


    /**
     * Attempts to place a mark on the board at specified coordinates.
     *
     * @param mark the mark to place (X or O)
     * @param row the row coordinate
     * @param column the column coordinate
     * @return true if the mark was placed successfully, false if occupied or invalid
     */
    public boolean putMark(Mark mark, int row, int column) {
        if (!checkCoordinates(row, column)) {
            return false; // Invalid coordinates
        }
        if (board[row][column] == Mark.BLANK) {
            board[row][column] = mark;
            return true;
        } else {
            System.out.println("Mark position is already occupied." +
                    " Please choose a different position:");
            return false;
        }
    }

    //====== HELPERS ======

    /**
     * Initializes the board by setting all positions to BLANK.
     */
    private void initializeBoard() {
        for (int row = 0; row < board.length; row++) {
            for (int column = 0; column < board[row].length; column++) {
                board[row][column] = Mark.BLANK;
            }
        }
    }

    /**
     * Checks if given coordinates are within the board's bounds.
     *
     * @param row the row coordinate
     * @param column the column coordinate
     * @return true if the coordinates are valid, false otherwise
     */
    private boolean checkCoordinates(int row, int column) {
        if (row < 0 || row >= boardSize || column < 0 || column >= boardSize) {
            System.out.println("Invalid mark position. Please choose a different position:");
            return false;
        }
        return true;
    }
}
