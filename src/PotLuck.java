import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Random;

/**
 * This class represents all the cards from PotLuck which are read from an external file.
 * This is a linked list of cards which is shuffled in the beginning;
 * a player takes a card from the head, and the card will be added back to the tail.
 * This class serves as an assistant class which provides information to the game logic,
 * and the actual action is implemented in the game logic.
 *
 * @author Hanzhen Gong
 */
public class PotLuck extends BoardSpace {
    private final FileIO fileIO = new FileIO();
    private String potLuck1;
    private String potLuck2;
    private String potLuck3; // = "Old Creek"
    private String potLuck4;
    private String potLuck5;
    private String potLuck6;
    private String potLuck7;
    private final String potLuck8 = "Go";
    private String potLuck9;
    private String potLuck10;
    private String potLuck11;
    private String potLuck12;
    private String potLuck13;
    private final String potLuck14 = "Jail";
    private String potLuck15;
    private String potLuck16;
    private final String potLuck17 = "Get out of jail free";
    private final LinkedList<Integer> cards;

    /**
     * Constructs a new PotLuck object.
     *
     * @param name The PotLuck object's name.
     */
    public PotLuck(String name) {
        super(name);
        this.initialize();
        cards = new LinkedList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17));
        for (int i = 0; i < 20; i++) {
            shuffleCards();
        }
        System.out.println(cards);
    }

    /**
     * Returns the next potluck card.
     *
     * @return The next potluck card as an {@code ArrayList<String>}.
     */
    public ArrayList<String> getNextCard() {
        ArrayList<String> info = new ArrayList<>();
        String nextCard = String.valueOf(cards.getFirst());
        if (!nextCard.equals("17")) {
            cards.addLast(cards.removeFirst());
        } else {
            cards.removeFirst();
        }
        info.add(nextCard);
        switch (nextCard) {
            case "1":
                info.add(potLuck1);
                break;
            case "2":
                info.add(potLuck2);
                break;
            case "3":
                info.add(potLuck3);
                break;
            case "4":
                info.add(potLuck4);
                break;
            case "5":
                info.add(potLuck5);
                break;
            case "6":
                info.add(potLuck6);
                break;
            case "7":
                info.add(potLuck7);
                break;
            case "8":
                info.add(potLuck8);
                break;
            case "9":
                info.add(potLuck9);
                break;
            case "10":
                info.add(potLuck10);
                break;
            case "11":
                info.add(potLuck11);
                break;
            case "12":
                info.add(potLuck12);
                break;
            case "13":
                info.add(potLuck13);
                break;
            case "14":
                info.add(potLuck14);
                break;
            case "15":
                info.add(potLuck15);
                break;
            case "16":
                info.add(potLuck16);
                break;
            case "17":
                info.add(potLuck17);

                break;
        }
        System.out.println(info);
        return info;
    }

    /**
     * Initializes the cards with the data from the csv file which is read by the FileIO class.
     */
    private void initialize() {
        ArrayList<ArrayList<String>> cardsData = fileIO.PotLuckCardData;
        for (int i = 0; i < cardsData.size() - 1; i++) {
            String currentData = cardsData.get(i).get(0);
            switch (i) {
                case 0:
                    int index = currentData.indexOf('£');
                    String moneyString = currentData.substring(index + 1);
                    potLuck1 = moneyString.replaceAll("\\D+", "");
                    System.out.println(potLuck1);
                    break;
                case 1:
                    index = currentData.indexOf('£');
                    moneyString = currentData.substring(index + 1);
                    potLuck2 = moneyString.replaceAll("\\D+", "");
                    System.out.println(potLuck2);
                    break;
                case 2:
                    index = currentData.indexOf("to");
                    potLuck3 = currentData.substring(index + 3);
                    potLuck3 = potLuck3.replaceAll("[^a-zA-Z ]", "");
                    System.out.println(potLuck3);
                    break;
                case 3:
                    index = currentData.indexOf('£');
                    moneyString = currentData.substring(index + 1);
                    potLuck4 = moneyString.replaceAll("\\D+", "");
                    System.out.println(potLuck4);
                    break;
                case 4:
                    index = currentData.indexOf('£');
                    moneyString = currentData.substring(index + 1);
                    potLuck5 = moneyString.replaceAll("\\D+", "");
                    System.out.println(potLuck5);
                    break;
                case 5:
                    index = currentData.indexOf('£');
                    moneyString = currentData.substring(index + 1);
                    potLuck6 = moneyString.replaceAll("\\D+", "");
                    System.out.println(potLuck6);
                    break;
                case 6:
                    index = currentData.indexOf('£');
                    moneyString = currentData.substring(index + 1);
                    potLuck7 = moneyString.replaceAll("\\D+", "");
                    System.out.println(potLuck7);
                    break;
                case 8:
                    index = currentData.indexOf('£');
                    moneyString = currentData.substring(index + 1);
                    potLuck9 = moneyString.replaceAll("\\D+", "");
                    System.out.println(potLuck9);
                    break;
                case 9:
                    index = currentData.indexOf('£');
                    moneyString = currentData.substring(index + 1);
                    potLuck10 = moneyString.replaceAll("\\D+", "");
                    System.out.println(potLuck10);
                    break;
                case 10:
                    index = currentData.indexOf('£');
                    moneyString = currentData.substring(index + 1);
                    potLuck11 = moneyString.replaceAll("\\D+", "");
                    System.out.println(potLuck11);
                    break;
                case 11:
                    index = currentData.indexOf('£');
                    moneyString = currentData.substring(index + 1);
                    potLuck12 = moneyString.replaceAll("\\D+", "");
                    System.out.println(potLuck12);
                    break;
                case 12:
                    index = currentData.indexOf('£');
                    moneyString = currentData.substring(index + 1);
                    potLuck13 = moneyString.replaceAll("\\D+", "");
                    System.out.println(potLuck13);
                    break;
                case 14:
                    index = currentData.indexOf('£');
                    moneyString = currentData.substring(index + 1);
                    potLuck15 = moneyString.replaceAll("\\D+", "");
                    System.out.println(potLuck15);
                    break;
                case 15:
                    index = currentData.indexOf('£');
                    moneyString = currentData.substring(index + 1);
                    potLuck16 = moneyString.replaceAll("\\D+", "");
                    System.out.println(potLuck16);
                    break;
            }
        }
    }

    /**
     * A function used to shuffle the opportunity knock cards.
     */
    private void shuffleCards() {
        Random random = new Random();

        int index = random.nextInt(cards.size());
        Integer element = cards.get(index);
        cards.remove(element);
        cards.addLast(element);
    }

    /**
     * Adds the get out of jail card back to the cards linked list.
     */
    public void addGetOutOfJailFreeBack() {
        cards.addLast(17);
    }

    /**
     * Call this method when PotLuck cards 1, 2, 4, 5, 9, 13, 15 are used.
     *
     * @param player The player that triggered this opportunity knock.
     * @param money  The amount of money to give to the player.
     */
    public void playerReceivesMoney(Player player, int money) {
        Cash currentMoney = player.getMoney();
        currentMoney.addAmount(money);
        player.setMoney(currentMoney);
    }

    /**
     * Call this method when PotLuck cards 16 are used.
     *
     * @param player       The player that triggered this opportunity knock.
     * @param otherPlayers A list of otherPlayers.
     * @param money        Money to give players.
     */
    public void playerReceivesMoney(Player player, ArrayList<Player> otherPlayers, int money) {
        int collectMoney = 0;
        for (Player currentPlayer : otherPlayers) {
            Cash currentMoney = currentPlayer.getMoney();
            currentMoney.subtractAmount(money);
            currentPlayer.setMoney(currentMoney);
            collectMoney += money;
        }
        Cash currentMoney = player.getMoney();
        currentMoney.addAmount(collectMoney);
        player.setMoney(currentMoney);
    }

    /**
     * Call this method when PotLuck cards 6, 7, 10, 12 are used.
     *
     * @param player The player that triggered this opportunity knock.
     * @param money  The amount of money to take from the player.
     */
    public void playerLosesMoney(Player player, int money) {
        Cash currentMoney = player.getMoney();
        currentMoney.subtractAmount(money);
        player.setMoney(currentMoney);
    }

    /**
     * Call this method when PotLuck cards 17 are used.
     *
     * @param player The player that triggered this opportunity knock.
     */
    public void getFreeJailCard(Player player) {
        player.setHasCard(true);
    }

    /**
     * Call this method when PotLuck cards 3, 8, 14 are used.
     *
     * @param player   The player that triggered this opportunity knock.
     * @param location The player's new location.
     */
    public void setPlayerTo(Player player, int location) {
        player.setLocation(location);
    }
}
