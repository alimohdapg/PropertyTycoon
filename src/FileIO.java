import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * A class used for reading and storing Board Data and Card data.
 *
 * @author Leon Feng
 */
public class FileIO {

    ArrayList<ArrayList<String>> BoardData;
    ArrayList<ArrayList<String>> PotLuckCardData;
    ArrayList<ArrayList<String>> OpKnocksCardData;

    /**
     * Constructs a new FileIO object.
     */
    public FileIO() {
        this.BoardData = readFile("PropertyTycoonBoardData.csv", 4, 43);
        this.PotLuckCardData = readFile("PropertyTycoonCardData.csv", 5, 21);
        this.OpKnocksCardData = readFile("PropertyTycoonCardData.csv", 26, 41);
    }

    /**
     * Reads the csv file from the path "src\\resource\\" to get the
     * PropertyTycoonBoardData and PropertyTycoonCardData, the function then
     * returns the data in the form of {@code ArrayList<ArrayList<String>>}, with
     * each line in the outer ArrayList and each block in the inner ArrayList
     *
     * @param filename The name of the file.
     * @param start    Start reading from this line.
     * @param end      Stop reading at this line.
     * @return An {@code ArrayList<ArrayList<String>>} containing the read file.
     */
    public static ArrayList<ArrayList<String>> readFile(String filename, int start, int end) {
        String file = "src/resource/" + filename;
        BufferedReader reader = null;
        ArrayList<ArrayList<String>> file_whole = new ArrayList<>();
        String line;
        try {
            reader = new BufferedReader(new FileReader(file));
            int i = 0;
            while ((line = reader.readLine()) != null) {
                if (i >= start && i <= end) {
                    ArrayList<String> file_row = new ArrayList<>(Arrays.asList(line.split(",")));
                    file_whole.add(file_row);
                }
                i++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                reader.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return file_whole;
    }

    /**
     * A main method used for showcasing how this class works.
     *
     * @param arg The command line arguments, unused here.
     */
    public static void main(String[] arg) {
        ArrayList<ArrayList<String>> Board = readFile("PropertyTycoonBoardData.csv", 4, 43);
        ArrayList<ArrayList<String>> PotLuck = readFile("PropertyTycoonCardData.csv", 5, 21);
        ArrayList<ArrayList<String>> OpKnocks = readFile("PropertyTycoonCardData.csv", 26, 41);

        System.out.println("----\n" + Board.get(5) + "\n----"); // should be "Brighton Station"
        System.out.println("----\n" + PotLuck.get(5) + "\n----"); // should be "Pay bill for text books of £100"
        System.out.println("----\n" + OpKnocks.get(5) + "\n----"); // should be "Pay university fees of £150"
    }
}
