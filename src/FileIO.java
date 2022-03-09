import java.io.*;
import java.util.ArrayList;

/**
 *
 * @author Leon
 */

public class FileIO {
    ArrayList<ArrayList<String>> BoardData;
    ArrayList<ArrayList<String>> PotLuckCardData;
    ArrayList<ArrayList<String>> OpKnocksCardData;

    public FileIO(){
        this.BoardData = readFile("PropertyTycoonBoardData.csv", 4, 43);
        this.PotLuckCardData = readFile("PropertyTycoonCardData.csv", 5, 21);
        this.OpKnocksCardData = readFile("PropertyTycoonCardData.csv", 26, 41);
    }
    
    /**
     *
     * Read csv file from the path "src\\resource\\" to get the
     * PropertyTycoonBoardData & PropertyTycoonCardData, return these data
     * as the form of ArrayList<ArrayList<String>>, each line for an outer
     * ArrayList and each block for an inner ArrayList
     *
     * @param filename the name of file
     * @param start start reading from this line
     * @param end stop reading at this line
     */
    public static ArrayList<ArrayList<String>> readFile(String filename, int start, int end){
        String file = "src\\resource\\" + filename;
        BufferedReader reader = null;
        ArrayList<ArrayList<String>> file_whole = new ArrayList<>();
        String line = "";

        try{
            reader = new BufferedReader(new FileReader(file));

            int i = 0;
            while( (line = reader.readLine()) != null ) {
                if (i >= start && i <= end) {
                    ArrayList<String> file_row = new ArrayList<>();

                    for (String index : line.split(",")){
                        file_row.add(index);
                        // System.out.println(index);
                    }
                    file_whole.add(file_row);
                    // System.out.println();
                }
                i++;
            }

        } catch (Exception e){
            e.printStackTrace();

        } finally {
            try{
                reader.close();
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        return file_whole;
    }

    public static void main(String[] arg){

        ArrayList<ArrayList<String>> Board = readFile("PropertyTycoonBoardData.csv", 4, 43);
        ArrayList<ArrayList<String>> PotLuck = readFile("PropertyTycoonCardData.csv", 5, 21);
        ArrayList<ArrayList<String>> OpKnocks = readFile("PropertyTycoonCardData.csv", 26, 41);

        System.out.println("----\n" + Board.get(5)+ "\n----"); // should be "Brighton Station"
        System.out.println("----\n" + PotLuck.get(5)+ "\n----"); // should be "Pay bill for text books of £100"
        System.out.println("----\n" + OpKnocks.get(5)+ "\n----"); // should be "Pay university fees of £150"

    }
}
