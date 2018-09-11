import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.util.concurrent.ForkJoinPool;
import java.util.ArrayList;
import java.io.IOException;

public class SimulationParallel{

    static long startTime = 0;

	private static void tick(){
		startTime = System.currentTimeMillis();
	}
	private static float tock(){
		return (System.currentTimeMillis() - startTime) / 1000.0f;
	}
	static final ForkJoinPool pool = new ForkJoinPool();
	static Double sum(ArrayList<Double> arr){
	  return pool.invoke(new SunCalculation(arr,0,arr.size()));
	}

    public static void main(String[] args){
        String input_filename = args[0];
        BufferedReader br = null;
        BufferedWriter bw = null;
        double[][] flArr = null;
        int x_size = 0;
        int y_size = 0;
        try{
            br = new BufferedReader(new FileReader(input_filename));
            String[] lineArr = br.readLine().split(" ");
            x_size = Integer.valueOf(lineArr[0]);
            y_size = Integer.valueOf(lineArr[1]);
            flArr = new double[x_size][y_size];

            lineArr = br.readLine().split(" "); //Array of values as Strings
            int x = 0;
            int y = 0;
            //Inserting into array
            for(String val : lineArr){
                flArr[x][y] = Double.valueOf(val); //Parsing values as float and inserting into array
                y++;
                if(y == y_size){
                    x++;
                    y = 0;
                }
            }

            int iterations = Integer.valueOf(br.readLine());
            double average = 0;
            Double[] sumArr = new Double[iterations];
            //float[] auxArr = null;
            for(int i = 0; i < iterations; i++){
                lineArr = br.readLine().split(" ");
                int x_coord = Integer.valueOf(lineArr[0]);
                int y_coord = Integer.valueOf(lineArr[1]);
                int extent = Integer.valueOf(lineArr[2]);
                Double sum = 0.0;
                ArrayList<Double> auxArray = new ArrayList<Double>();
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
                                auxArray.add(flArr[a][b]);
                            }
                        }
                    }
                    tick();
                    sum = sum(auxArray);
                    float time = tock();
                    //System.out.println(sum);
                    System.out.println(time + "seconds");
                }
                sumArr[i] = sum;
            }
            String output_filename = args[1];
            bw = new BufferedWriter(new FileWriter(output_filename));
            Double sum = 0.0;
            for(Double val : sumArr){
                sum += val;
            }
            average = sum/iterations;
            bw.write(Double.toString(average) +"\n");
            bw.write(Integer.toString(iterations) + "\n");
            for(Double val : sumArr){
                bw.write(Double.toString(val) + "\n");
            }
            br.close();
            bw.close();

        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
}