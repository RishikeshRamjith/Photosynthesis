import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileReader;

public class SimulationSequential{
    public static void main(String[] args){
        String input_filename = args[0];
        String output_filename = args[1];
        BufferedReader br = null;
        try{
            br = new BufferedReader(new FileReader(input_filename));
            int x_size = br.nextInt();
            int y_size = br.nextInt();
            System.out.println("Done");
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
}
