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
	static Double sum(ArrayList<ArrayList<Double>> arr){
	  return pool.invoke(new SunCalculation(arr, 0, arr.size()));
	}

    public static void main(String[] args){
        String input_filename = args[0];
        BufferedReader br = null;
        BufferedWriter bw = null;
        Double[][] flArr = null;
        int x_size = 0;
        int y_size = 0;
        try{
            br = new BufferedReader(new FileReader(input_filename));
            String[] lineArr = br.readLine().split(" ");
            x_size = Integer.valueOf(lineArr[0]);
            y_size = Integer.valueOf(lineArr[1]);
            flArr = new Double[x_size][y_size];

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
            iterations = Integer.valueOf(args[2]);
            double average = 0;
            Double[] sumOfEachTreeArr = new Double[iterations];
            ArrayList<ArrayList<Double>> ArrayTreeArray = new ArrayList<ArrayList<Double>>();
            for(int i = 0; i < iterations; i++){
                ArrayList<Double> treeArray = new ArrayList<Double>();
                lineArr = br.readLine().split(" ");
                int x_coord = Integer.valueOf(lineArr[0]);
                int y_coord = Integer.valueOf(lineArr[1]);
                int extent = Integer.valueOf(lineArr[2]);
                Double Treesum = 0.0;
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
                                treeArray.add(flArr[a][b]);
                                Treesum = Treesum + flArr[a][b];
                            }
                        }

                    }
                }
                sumOfEachTreeArr[i] = Treesum;
                ArrayTreeArray.add(treeArray);
            }

            tick();
            Double sum = sum(ArrayTreeArray);
            float time = tock();
            System.out.println(time + " seconds");
            String output_filename = args[1];
            bw = new BufferedWriter(new FileWriter(output_filename));
            average = sum/iterations;
            bw.write(Double.toString(average) +"\n");
            bw.write(Integer.toString(iterations) + "\n");
            for(Double val : sumOfEachTreeArr){
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
