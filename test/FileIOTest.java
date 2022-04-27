import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class FileIOTest {

    ArrayList<ArrayList<String>> BoardData = FileIO.readFile("PropertyTycoonBoardData.csv", 4, 43);
    ArrayList<ArrayList<String>> PotLuckCardData = FileIO.readFile("PropertyTycoonCardData.csv", 5, 21);
    ArrayList<ArrayList<String>> OpKnocksCardData = FileIO.readFile("PropertyTycoonCardData.csv", 26, 41);

    @Test
    public void testBoardDataReading(){
        ArrayList<String> testArrayList = BoardData.get(5);
        String[] input = {"6", "Brighton Station", "", "Station", "", "Yes", "", "200", "See notes"};
        ArrayList<String> trueArrayList = new ArrayList<>(Arrays.asList(input));

        assertEquals(testArrayList, trueArrayList);
    }

    @Test
    public void testPotLuckReading(){
        ArrayList<String> testArrayList = PotLuckCardData.get(5);
        String[] input = {"\"\"\"Pay bill for text books of £100\"\"\"", "", "", "Player pays £100 to the bank"};
        ArrayList<String> trueArrayList = new ArrayList<>(Arrays.asList(input));

        assertEquals(testArrayList, trueArrayList);
    }

    @Test
    public void testOpportunityKnockReading(){
        ArrayList<String> testArrayList = OpKnocksCardData.get(5);
        String[] input = {"\"\"\"Pay university fees of £150\"\"\"", "", "", "Player pays £150 to the bank"};
        ArrayList<String> trueArrayList = new ArrayList<>(Arrays.asList(input));

        assertEquals(testArrayList, trueArrayList);
    }
}
