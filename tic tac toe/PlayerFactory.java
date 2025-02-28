public class PlayerFactory {

    /**
     * Default constructor for PlayerFactory.
     */
    public PlayerFactory() {
        // No initialization required for the factory
    }

    /**
     * Builds and returns a Player instance based on the specified type.
     *
     * @param type The type of player to create (e.g., "human", "whatever", "clever", "genius").
     * @return A Player object of the specified type.
     * @throws IllegalArgumentException if the type is not recognized.
     */
    public Player buildPlayer(String type) {
        switch (type.toLowerCase()) {
            case "human":
                return new HumanPlayer();
            case "whatever":
                return new WhateverPlayer();
            case "clever":
                return new CleverPlayer();
            case "genius":
                return new GeniusPlayer();
            default:
                return null;
        }
    }
}
