import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;


public class CelestialClash {
    // Global variables for player decks
    private static List<String> playerOneDeck;
    private static List<String> playerTwoDeck;

    /**
     * The main method that starts the game by displaying the menu.
     *
     */
    public static void main(String[] args) {
        showMenu();
    }

    /**
     * Displays the game menu and handles user input for menu options.
     */
    public static void showMenu() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Menu:");
        System.out.println("1. Play");
        System.out.println("2. View Cards/Decks");
        System.out.println("3. Rules");
        System.out.println("4. Exit");
        System.out.print("Enter your choice: ");

        // Validate user input
        if (scanner.hasNextInt()) {
            int option = scanner.nextInt();
            scanner.nextLine(); // Clear the scanner buffer

            switch (option) {
                case 1:
                    System.out.println("You selected Play");
                    playGame();
                    break;
                case 2:
                    System.out.println("You selected View Cards/Decks");
                    System.out.println("Select the deck you want to view:");
                    System.out.println("1. Angels Deck");
                    System.out.println("2. Demons Deck");
                    System.out.print("Enter your choice: ");

                    if (scanner.hasNextInt()) {
                        int deckOption = scanner.nextInt();
                        scanner.nextLine(); // Clear the scanner buffer

                        switch (deckOption) {
                            case 1:
                                System.out.println("Deck One:");
                                showDeck(deckOne());
                                break;
                            case 2:
                                System.out.println("Deck Two:");
                                showDeck(deckTwo());
                                break;
                            default:
                                System.out.println("Error: Invalid deck option.");
                        }
                    } else {
                        System.out.println("Error: Please enter a valid number.");
                        scanner.nextLine(); // Clear the scanner buffer
                    }
                    break;
                case 3:
                    System.out.println("You selected Rules");
                    System.out.println("Each player receives four cards in the first round. In each subsequent round, they pick one card to fight against the opponent's card.\nAfter each round, players can lose health points based on the chosen card's stats.\nAfter each round, players receive a new card so they can have 4 cards for the next round.");
                    break;
                case 4:
                    System.out.println("Exiting the program...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Error: Invalid option. Please enter a choice from 1 to 4.");
            }
        } else {
            System.out.println("Error: Please enter a valid number.");
            scanner.nextLine(); // Clear the scanner buffer
        }

        // Recursive call to show the menu again
        showMenu();
    }

    /**
     * Starts the game by initializing player decks and handling the game rounds.
     */
    public static void playGame() {
        int playerOneLife = 25;
        int playerTwoLife = 25;
        int round = 1;
        System.out.println("The game begins!");

        // Initialize player decks only in the first round
        if (round == 1) {
            playerOneDeck = dealCards(deckOne());
            playerTwoDeck = dealCards(deckTwo());
        }

        while (playerOneLife > 0 && playerTwoLife > 0) {
            System.out.println("Round " + round);

            // Each player chooses a card to play
            int playerOneCard = chooseCard(playerOneDeck);
            System.out.println("\n\n\n\n\n\n\n");
            int playerTwoCard = chooseCard(playerTwoDeck);
            System.out.println("\n\n\n\n\n\n\n");

            // Calculate damage
            int playerOneCardStrength = Integer.parseInt(playerOneDeck.get(playerOneCard * 3 + 1));
            int playerTwoCardLife = Integer.parseInt(playerTwoDeck.get(playerTwoCard * 3 + 2));
            int playerOneDamage = Math.max(0, playerOneCardStrength - playerTwoCardLife);

            int playerTwoCardStrength = Integer.parseInt(playerTwoDeck.get(playerTwoCard * 3 + 1));
            int playerOneCardLife = Integer.parseInt(playerOneDeck.get(playerOneCard * 3 + 2));
            int playerTwoDamage = Math.max(0, playerTwoCardStrength - playerOneCardLife);

            // Update player life
            playerOneLife -= playerOneDamage;
            playerTwoLife -= playerTwoDamage;

            System.out.println("Player 1 dealt " + playerOneDamage + " damage. Remaining life: " + playerOneLife);
            System.out.println("-------------------------------------------");
            System.out.println("Player 2 dealt " + playerTwoDamage + " damage. Remaining life: " + playerTwoLife);
            System.out.println("\n\n\n\n\n\n\n");

            // Remove played cards from each player's deck
            playerOneDeck.remove(playerOneCard * 3);
            playerOneDeck.remove(playerOneCard * 3);
            playerOneDeck.remove(playerOneCard * 3);

            playerTwoDeck.remove(playerTwoCard * 3);
            playerTwoDeck.remove(playerTwoCard * 3);
            playerTwoDeck.remove(playerTwoCard * 3);

            // Check if any player has 0 or less life
            if (playerOneLife <= 0 || playerTwoLife <= 0) {
                System.out.println("The game has ended!");
                if (playerOneLife <= 0 && playerTwoLife <= 0) {
                    System.out.println("It's a tie!");
                } else if (playerOneLife <= 0) {
                    System.out.println("Player 2 wins!");
                } else {
                    System.out.println("Player 1 wins!");
                }
                break;
            }

            // Add a random card at the end of each player's deck after their turn
            playerOneDeck.addAll(getRandomCard(deckOne()));
            playerTwoDeck.addAll(getRandomCard(deckTwo()));

            round++;
        }
    }

    /**
     * Allows a player to choose a card from their deck.
     *
     * @param playerDeck The deck of the player choosing a card.
     * @return The index of the chosen card.
     */
    public static int chooseCard(List<String> playerDeck) {
        Scanner scanner = new Scanner(System.in);
        int selection;

        do {
            System.out.println("Select a card:");
            for (int i = 0; i < playerDeck.size(); i += 3) {
                String cardName = playerDeck.get(i);
                String cardStrength = playerDeck.get(i + 1);
                String cardResistance = playerDeck.get(i + 2);
                System.out.println("(" + (i / 3 + 1) + ") " + cardName + " - Strength: " + cardStrength + ", Resistance: " + cardResistance);
            }
            System.out.print("Enter the card number: ");

            while (!scanner.hasNextInt()) {
                System.out.println("Error: Please enter a valid number.");
                scanner.next(); // Clear the scanner buffer
            }
            selection = scanner.nextInt();

            if (selection < 1 || selection > playerDeck.size() / 3) {
                System.out.println("Invalid card number. Please enter a valid number.");
            } else {
                return selection - 1;
            }
        } while (true);
    }

    /**
     * Generates and returns the deck of angel cards.
     *
     * @return The deck of angel cards.
     */
    public static List<String> deckOne() {
        List<String> deckOne = new ArrayList<>();
        //Tier 1 Max 20 points
        deckOne.addAll(List.of("Gabriel", "10", "10"));
        deckOne.addAll(List.of("Michael", "15", "5"));
        deckOne.addAll(List.of("Rafael", "7", "13"));
        deckOne.addAll(List.of("Uriel", "14", "6"));
        deckOne.addAll(List.of("Ariel", "8", "12"));
        //Tier 2 Max 12 points
        deckOne.addAll(List.of("Seraphiel", "8", "4"));
        deckOne.addAll(List.of("Azrael", "6", "6"));
        deckOne.addAll(List.of("Metatron", "9", "3"));
        deckOne.addAll(List.of("Raziel", "4", "8"));
        deckOne.addAll(List.of("Haniel", "2", "10"));
        //Tier 3 Max 6 points
        deckOne.addAll(List.of("Jophiel", "6", "0"));
        deckOne.addAll(List.of("Zadkiel", "3", "3"));
        deckOne.addAll(List.of("Cassiel", "1", "5"));
        deckOne.addAll(List.of("Chamuel", "4", "2"));
        deckOne.addAll(List.of("Raguel", "2", "4"));

        return deckOne;
    }

    /**
     * Generates and returns the deck of demon cards.
     *
     * @return The deck of demon cards.
     */
    public static List<String> deckTwo() {
        List<String> deckTwo = new ArrayList<>();
        //Tier 1 Max 20 points
        deckTwo.addAll(List.of("Lucifer", "19", "1"));
        deckTwo.addAll(List.of("Belial", "12", "8"));
        deckTwo.addAll(List.of("Asmodeus", "15", "5"));
        deckTwo.addAll(List.of("Mammon", "0", "20"));
        deckTwo.addAll(List.of("Beelzebub", "17", "3"));
        //Tier 2 Max 12 points
        deckTwo.addAll(List.of("Abaddon", "6", "6"));
        deckTwo.addAll(List.of("Leviathan", "6", "6"));
        deckTwo.addAll(List.of("Baphomet", "8", "4"));
        deckTwo.addAll(List.of("Astaroth", "3", "9"));
        deckTwo.addAll(List.of("Lilith", "2", "11"));
        //Tier 3 Max 6 points
        deckTwo.addAll(List.of("Mephistopheles", "3", "3"));
        deckTwo.addAll(List.of("Baal", "2", "4"));
        deckTwo.addAll(List.of("Azazel", "3", "3"));
        deckTwo.addAll(List.of("Moloch", "4", "2"));
        deckTwo.addAll(List.of("Nyarlathotep", "4", "2"));
        return deckTwo;
    }

    /**
     * Deals cards to a player's deck randomly from the provided deck.
     *
     * @param deck The deck from which cards will be dealt.
     * @return The list of cards dealt to the player's deck.
     */
    public static List<String> dealCards(List<String> deck) {
        Random random = new Random();
        List<String> playableDeck = new ArrayList<>();

        for (int a = 0; a < 4; a++) {
            // Generate random numbers based on the length of the list.
            int randomNum = random.nextInt(deck.size() / 3 - 1 + 1) + 1;

            playableDeck.addAll(List.of(getName(randomNum, deck), getStrength(randomNum, deck), getResistance(randomNum, deck)));
            for (int i = 0; i < 3; i++) {
                deck.remove(randomNum * 3 - 3);
            }
        }
        return playableDeck;
    }

    /**
     * Displays the cards in a deck.
     *
     * @param deck The deck to be displayed.
     */
    public static void showDeck(List<String> deck) {
        for (int i = 0; i < deck.size(); i += 3) {
            String cardName = deck.get(i);
            String cardStrength = deck.get(i + 1);
            String cardResistance = deck.get(i + 2);
            System.out.println(cardName + " - Strength: " + cardStrength + ", Resistance: " + cardResistance);
        }
    }

    /**
     * Adds a random card to a player's deck.
     *
     * @param deck The deck to which a random card will be added.
     * @return The list containing the randomly selected card.
     */
    public static List<String> getRandomCard(List<String> deck) {
        Random random = new Random();
        List<String> randomCard = new ArrayList<>();

        int randomNum = random.nextInt(deck.size() / 3);
        randomCard.addAll(List.of(deck.get(randomNum * 3), deck.get(randomNum * 3 + 1), deck.get(randomNum * 3 + 2)));

        return randomCard;
    }

    /**
     * Retrieves the name of a card from the deck.
     *
     * @param num  The index of the card in the deck.
     * @param deck The deck from which the card's name will be retrieved.
     * @return The name of the card.
     */
    public static String getName(int num, List<String> deck) {
        String name = deck.get(num * 3 - 3);
        return name;
    }

    /**
     * Retrieves the strength of a card from the deck.
     *
     * @param num  The index of the card in the deck.
     * @param deck The deck from which the card's strength will be retrieved.
     * @return The strength of the card.
     */
    public static String getStrength(int num, List<String> deck) {
        String strength = deck.get(num * 3 - 2);
        return strength;
    }

    /**
     * Retrieves the resistance of a card from the deck.
     *
     * @param num  The index of the card in the deck.
     * @param deck The deck from which the card's resistance will be retrieved.
     * @return The resistance of the card.
     */
    public static String getResistance(int num, List<String> deck) {
        String resistance = deck.get(num * 3 - 1);
        return resistance;
    }
}
