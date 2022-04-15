import java.util.ArrayList;
import java.util.LinkedList;

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
                    index = currentData.indexOf('.');
                    String subString = currentData.substring(index + 1);
                    subString = subString.replaceAll("\\D", " ");
                    System.out.println(subString);

                case 9:
                    // Go

                case 10:
                    index = currentData.indexOf('.');
                    String subStr = currentData.substring(index + 1);
                    subString = subStr.replaceAll("\\D", " ");
                    System.out.println(subString);

                case 11:
                    // Go back 3 spaces

                case 12:
                    index = currentData.indexOf("to");
                    OK13 = currentData.substring(index + 3);
                    period = OK13.indexOf('.');
                    OK13 = OK13.substring(0, period);
                    System.out.println(OK13);
                    break;

                case 13:
                    // Go jail

                case 14:
                    index = currentData.indexOf('£');
                    moneyString = currentData.substring(index + 1);
                    OK15 = moneyString.replaceAll("\\D+","");
                    System.out.println(OK15);
                    break;

                case 15:
                    // Get out of jail free
            }

        }
    }

    public static void main(String[] args) {
        OpportunityKnock o = new OpportunityKnock("d");
        o.initialize();
    }
}
