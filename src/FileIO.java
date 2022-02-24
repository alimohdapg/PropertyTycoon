import java.io.*;
import java.util.ArrayList;

/**
 *
 * @author Leon
 */

public class FileIO {
    public static void main(String[] arg){

        ArrayList<ArrayList<String>> myFile = FileIO.readFile("PropertyTycoonCardData.csv");
        System.out.println("----\n" + myFile.get(4).get(3) + "\n----");

    }
    
    /**
     *
     * @param filename
     *
     * Read csv file from the path "src\\resource\\" to get the
     * PropertyTycoonBoardData & PropertyTycoonCardData, return these data
     * as the form of ArrayList<ArrayList<String>>, each line for an outer
     * ArrayList and each block for an inner ArrayList
     */
    public static ArrayList readFile(String filename){
        String file = "src\\resource\\" + filename;
        BufferedReader reader = null;
        ArrayList<ArrayList<String>> file_whole = new ArrayList<>();
        String line = "";

        try{
            reader = new BufferedReader(new FileReader(file));
            while( (line = reader.readLine()) != null ) {

                ArrayList<String> file_row = new ArrayList<>();

                for (String index : line.split(",")){
                    System.out.println(index);
                    file_row.add(index);
                }
                file_whole.add(file_row);
                System.out.println();
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
}
