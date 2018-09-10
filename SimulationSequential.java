import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileReader;

public class SimulationSequential{
    public static void main(String[] args){
        String input_filename = args[0];
        //String output_filename = args[1];
        BufferedReader br = null;
        float[][] flArr = null;
        int x_size = 0;
        int y_size = 0;
        try{
            br = new BufferedReader(new FileReader(input_filename));
            String[] lineArr = br.readLine().split(" ");
            x_size = Integer.valueOf(lineArr[0]);
            y_size = Integer.valueOf(lineArr[1]);
            flArr = new float[x_size][y_size];

            lineArr = br.readLine().split(" "); //Array of values as Strings
            int x = 0;
            int y = 0;
            for(String val : lineArr){
                flArr[x][y] = Float.valueOf(val); //Parsing values as float and inserting into array
                y++;
                if(y == y_size){
                    x++;
                    y = 0;
                }
            }
            int iterations = Integer.valueOf(br.readLine());
            for(int i = 0; i < iterations; i++){
                lineArr = br.readLine().split(" ");
                int x_coord = Integer.valueOf(lineArr[0]);
                int y_coord = Integer.valueOf(lineArr[1]);
                int extent = Integer.valueOf(lineArr[2]);
                float sum = 0;
                for(int a = x_coord; a < x_coord+extent; a++){
                    if(a == x_size){
                        break;
                    }
                    else{
                        for(int b = y_coord; b < y_coord+extent; b++){
                            if(b == y_size){
                                break;
                            }
                            else{
                                sum = sum + flArr[a][b];
                                System.out.print(flArr[a][b]+" ");
                            }
                        }
                    }

                    System.out.println();

                }
                System.out.println();
                System.out.println(sum);
            }
            //System.out.println(lineArr[0]);
            //System.out.println("Done");
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
}
