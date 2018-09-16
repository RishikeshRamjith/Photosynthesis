import java.io.BufferedReader;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

public class TxtToCSV{
    public static void main(String[] args) {
        BufferedReader br = null;
        BufferedWriter bw = null;
        String line = null;
        ArrayList<String> arr = new ArrayList<String>();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter input file name: ");
        String input_filename = scanner.nextLine();
        System.out.println("Enter output file name: ");
        String output_filename = scanner.nextLine();

        try{
            br = new BufferedReader(new FileReader(input_filename));
            bw = new BufferedWriter(new FileWriter(output_filename));
            while((line = br.readLine()) != null){
                arr.add(line);
            }
            br.close();
            for(int i = 0; i < arr.size() - 2; i = i+2){
                String dataset_line = arr.get(i);
                String time_line = arr.get(i+1);
                bw.write(dataset_line+","+time_line+"\n");
            }
            bw.close();
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
}
