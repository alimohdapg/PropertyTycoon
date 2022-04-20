import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Random;

/**
 * Same as PotLuck
 *
 * !Opportunity Knock 5, 15 go to Free Parking!
 *
 * @author Hanzhen Gong
 */
public class OpportunityKnock extends BoardSpace
{
    private FileIO fileIO = new FileIO();
    private String OK1;
    private String OK2;
    private String OK3;
    private String OK4;
    private String OK5;
    private String OK6;
    private String OK7;
    private String OK8;
    private String OK9;
    private String OK10 = "Go";
    private String OK11;
    private String OK12;
    private String OK13;
    private String OK14 = "Jail";
    private String OK15;
    private String OK16 = "Get out of jail free";

    private LinkedList<Integer> cards;

    public OpportunityKnock(String name)
    {
        super(name);
        initialize();
        cards = new LinkedList<Integer>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16));
        for (int i = 0; i < 20; i++)
        {
            shuffleCards();
        }
        System.out.println(cards);
    }

    private void shuffleCards()
    {
        Random random = new Random();

        int index = random.nextInt(cards.size());
        Integer element = cards.get(index);
        cards.remove(element);
        cards.addLast(element);
    }

    private void initialize()
    {
        ArrayList<ArrayList<String>> opportunityKnockData = fileIO.OpKnocksCardData;

        for (int i = 0; i < opportunityKnockData.size(); i++)
        {
            String currentData = opportunityKnockData.get(i).get(0);

            switch (i)
            {
                case 0:
                    int index = currentData.indexOf('£');
                    String moneyString = currentData.substring(index + 1);
                    OK1 = moneyString.replaceAll("\\D+","");
                    System.out.println(OK1);
                    break;

                case 1:
                    index = currentData.indexOf('£');
                    moneyString = currentData.substring(index + 1);
                    OK2 = moneyString.replaceAll("\\D+","");
                    System.out.println(OK2);
                    break;

                case 2:
                    index = currentData.indexOf("to");
                    OK3 = currentData.substring(index + 3);
                    OK3 = OK3.replaceAll("[^a-zA-Z ]", "");
                    System.out.println(OK3);
                    break;

                case 3:
                    index = currentData.indexOf("to");
                    OK4 = currentData.substring(index + 3);
                    int period = OK4.indexOf('.');
                    OK4 = OK4.substring(0, period);
                    System.out.println(OK4);
                    break;

                case 4:
                    index = currentData.indexOf('£');
                    moneyString = currentData.substring(index + 1);
                    OK5 = moneyString.replaceAll("\\D+","");
                    System.out.println(OK5);
                    break;

                case 5:
                    index = currentData.indexOf('£');
                    moneyString = currentData.substring(index + 1);
                    OK6 = moneyString.replaceAll("\\D+","");
                    System.out.println(OK6);
                    break;

                case 6:
                    index = currentData.indexOf("to");
                    OK7 = currentData.substring(index + 3);
                    period = OK7.indexOf('.');
                    OK7 = OK7.substring(0, period);
                    System.out.println(OK7);
                    break;

                case 7:
                    index = currentData.indexOf('£');
                    moneyString = currentData.substring(index + 1);
                    OK8 = moneyString.replaceAll("\\D+","");
                    System.out.println(OK8);
                    break;

                case 8:
                    ArrayList<Integer> periodIndices = new ArrayList<>();
                    for (int j = 0; j < currentData.length(); j++)
                    {
                        if (currentData.charAt(j) == '.')
                        {
                            periodIndices.add(j);
                        }
                    }

                    String part1 = currentData.substring(periodIndices.get(0), periodIndices.get(1));
                    String part2 = currentData.substring(periodIndices.get(1));

                    String num1 = part1.replaceAll("\\D", "");
                    String num2 = part2.replaceAll("\\D", "");

                    OK9 = num1 + " " + num2;

                    System.out.println(OK9);
                    break;

                case 10:
                    periodIndices = new ArrayList<>();
                    for (int j = 0; j < currentData.length(); j++)
                    {
                        if (currentData.charAt(j) == '.')
                        {
                            periodIndices.add(j);
                        }
                    }

                    part1 = currentData.substring(periodIndices.get(0), periodIndices.get(1));
                    part2 = currentData.substring(periodIndices.get(1));

                    num1 = part1.replaceAll("\\D", "");
                    num2 = part2.replaceAll("\\D", "");

                    OK11 = num1 + " " + num2;

                    System.out.println(OK11);
                    break;

                case 11:
                    OK12 = currentData.replaceAll("\\D", "");
                    System.out.println(OK12);
                    System.out.println("11 End");
                    break;

                case 12:
                    index = currentData.indexOf("to");
                    OK13 = currentData.substring(index + 3);
                    period = OK13.indexOf('.');
                    OK13 = OK13.substring(0, period);
                    System.out.println(OK13);
                    break;

                case 14:
                    OK15 = currentData.replaceAll("\\D+","");
                    System.out.println(OK15);
                    break;
            }

        }
    }

    public ArrayList<String> getNextCard()
    {
        ArrayList<String> info = new ArrayList<>();
        String nextCard = String.valueOf(cards.getFirst());
        if (!nextCard.equals("16"))
        {
            cards.addLast(cards.removeFirst());
        }
        else
        {
            cards.removeFirst();
        }

        info.add(nextCard);

        switch (nextCard)
        {
            case "1":
                info.add(OK1);
                break;
            case "2":
                info.add(OK2);
                break;
            case "3":
                info.add(OK3);
                break;
            case "4":
                info.add(OK4);
                break;
            case "5":
                info.add(OK5);
                break;
            case "6":
                info.add(OK6);
                break;
            case "7":
                info.add(OK7);
                break;
            case "8":
                info.add(OK8);
                break;
            case "9":
                info.add(OK9);
                break;
            case "10":
                info.add(OK10);
                break;
            case "11":
                info.add(OK11);
                break;
            case "12":
                info.add(OK12);
                break;
            case "13":
                info.add(OK13);
                break;
            case "14":
                info.add(OK14);
                break;
            case "15":
                info.add(OK15);
                break;
            case "16":
                info.add(OK16);
                break;
        }

        return info;
    }

    public void addGetOutOfJailFreeBack()
    {
        cards.addLast(16);
    }

    // Call this method when Opportunity Knock 1, 2, 8
    public void playerReceiveMoney(Player player, int money)
    {
        Cash currentMoney = player.getMoney();
        currentMoney.addAmount(money);
        player.setMoney(currentMoney);
    }

    // Call this method when Opportunity Knock 3, 4, 7, 10, 12, 13, 14
    public void setPlayerTo(Player player, int location)
    {
        player.setLocation(location);
    }

    // Call this method When Opportunity Knock 5, 6, 15
    public void playerLoseMoney(Player player, int money)
    {
        Cash currentMoney = player.getMoney();
        currentMoney.subtractAmount(money);
        player.setMoney(currentMoney);
    }

    // Call this method When Opportunity Knock 9, 11
    public void playerRepairProperties(Player player, String Fees)
    {
        String[] feesArray = Fees.split(" ");
        int houseFee = Integer.parseInt(feesArray[0]);
        int hotelFee = Integer.parseInt(feesArray[1]);

        int totalHouseAmount = 0;
        int totalHotelAmount = 0;

        ArrayList<Property> properties = player.getProperties();
        for (Property currentProperty : properties)
        {
            totalHouseAmount += currentProperty.getHouseCount();
            int hotelAmount = currentProperty.doesHaveHotel() ? 1 : 0;
            totalHotelAmount += hotelAmount;
        }

        int totalFee = totalHouseAmount * houseFee + totalHotelAmount * hotelFee;
        Cash currentMoney = player.getMoney();
        currentMoney.subtractAmount(totalFee);
        player.setMoney(currentMoney);
    }

    // Call this method When Opportunity Knock 16
    public void getFreeJailCard(Player player)
    {
        player.setHasCard(true);
    }
}
