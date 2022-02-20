import java.io.*;
import java.util.ArrayList;

/**
 *
 * @author Leon
 */

public class FileIO {
    public static void main(String[] arg){

//        Test
        ArrayList<ArrayList<String>> myFile = FileIO.readFile("PropertyTycoonCardData.csv");
        System.out.println("----\n" + myFile.get(4).get(3) + "\n----");

    }

    public static ArrayList readFile(String filename){
        String file = "src\\resource\\" + filename;
        BufferedReader reader = null;
        ArrayList<ArrayList<String>> file_whole = new ArrayList<>();
        String line = "";

        try{
            reader = new BufferedReader(new FileReader(file));
            while( (line = reader.readLine()) != null ) {

                ArrayList<String> file_row = new ArrayList<>();
//                String[] row = line.split(",");

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
