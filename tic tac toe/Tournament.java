/**
 * Manages a Tic-Tac-Toe tournament between two players.
 * Handles multiple rounds, tracks results, and displays final scores.
 */
public class Tournament {

    private final int rounds; // Total number of rounds in the tournament
    private final Player player1; // Player 1 instance
    private final Player player2; // Player 2 instance
    private final Renderer renderer; // Renderer for displaying the board
    private Game game; // The current game instance
    // results[0]: player1 wins, results[1]: player2 wins, results[2]: ties
    private int[] results = {0, 0, 0};


    /**
     * Constructs a Tournament with specified rounds, renderer, and players.
     *
     * @param rounds   Number of rounds to be played in the tournament.
     * @param renderer Renderer for board display.
     * @param player1  First player instance.
     * @param player2  Second player instance.
     */
    public Tournament(int rounds, Renderer renderer, Player player1, Player player2) {
        this.rounds = rounds;
        this.renderer = renderer;
        this.player1 = player1;
        this.player2 = player2;
    }

    /**
     * Plays the tournament by executing the specified number of rounds.
     * Alternates the starting player each round, updates results, and prints the final score.
     *
     * @param size         Size of the board.
     * @param winStreak    Number of consecutive marks required to win.
     * @param playerName1  Name of the first player.
     * @param playerName2  Name of the second player.
     */
    public void playTournament(int size, int winStreak, String playerName1, String playerName2) {
        for (int i = 0; i < rounds; i++) {
            // Alternate starting players based on the round number
            Player xPlayer = (i % 2 == 0) ? player1 : player2;
            Player oPlayer = (i % 2 == 0) ? player2 : player1;

            // Initialize a new Game with the specified size and win streak
            game = new Game(xPlayer, oPlayer, size, winStreak, renderer);

            // Play the game and get the winner
            Mark winner = game.run();

            // Update results based on the outcome
            updateResults(winner, xPlayer, oPlayer);
        }

        // Print the final results at the end of the tournament
        printResults(playerName1, playerName2);
    }

    // ================ HELPERS ================

    /**
     * Updates the results array based on the game outcome.
     *
     * @param winner  The mark of the winner (X, O, or BLANK for tie).
     * @param xPlayer Player playing as X in this round.
     * @param oPlayer Player playing as O in this round.
     */
    private void updateResults(Mark winner, Player xPlayer, Player oPlayer) {
        if (winner == Mark.X) {
            if (xPlayer == player1) {
                results[0]++; // player1 won
            } else {
                results[1]++; // player2 won
            }
        } else if (winner == Mark.O) {
            if (oPlayer == player1) {
                results[0]++; // player1 won
            } else {
                results[1]++; // player2 won
            }
        } else if (winner == Mark.BLANK) {
            results[2]++; // It's a tie
        }
    }

    /**
     * Prints the final results of the tournament, showing wins and ties.
     *
     * @param playerName1 Name of the first player.
     * @param playerName2 Name of the second player.
     */
    private void printResults(String playerName1, String playerName2) {
        System.out.println("######### Results #########");
        System.out.println("Player 1, " + playerName1 + " won: " + results[0] + " rounds");
        System.out.println("Player 2, " + playerName2 + " won: " + results[1] + " rounds");
        System.out.print("Ties: " + results[2]);
    }

    /**
     * Main method to parse command-line arguments, create players, renderer, and start the tournament.
     *
     * @param args Command-line arguments:
     *            [round count] [size] [win_streak] [render target] [first player] [second player]
     */
    public static void main(String[] args) {
        int roundCount = Integer.parseInt(args[0]);
        int size = Integer.parseInt(args[1]);
        int winStreak = Integer.parseInt(args[2]);

        // Validate win streak and board size
        if (winStreak > size) {
            System.out.println("Win streak cannot be greater than the board size.");
            return;
        }

        // Create renderer using RendererFactory
        RendererFactory rendererFactory = new RendererFactory();
        Renderer renderer = rendererFactory.buildRenderer(args[3], size);
        if (renderer == null) {
            return;
        }

        // Create players using PlayerFactory
        PlayerFactory playerFactory = new PlayerFactory();
        Player player1 = playerFactory.buildPlayer(args[4]);
        Player player2 = playerFactory.buildPlayer(args[5]);
        if (player1 == null || player2 == null) {
            return;
        }

        // Start the tournament
        Tournament tournament = new Tournament(roundCount, renderer, player1, player2);
        tournament.playTournament(size, winStreak, args[4], args[5]);
    }
}
