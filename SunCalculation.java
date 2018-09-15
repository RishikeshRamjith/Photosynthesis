import java.util.concurrent.RecursiveTask;
import java.util.ArrayList;

public class SunCalculation extends RecursiveTask<Double>{
    int lo;
    int hi;
    ArrayList<ArrayList<Double>> arr;
    static int SEQUENTIAL_CUTOFF = 420;
    double ans = 0;

    public SunCalculation(ArrayList<ArrayList<Double>> a, int l, int h){
        lo = l;
        hi = h;
        arr = a;
    }

    @Override
    protected Double compute(){
        if((hi - lo) < SEQUENTIAL_CUTOFF){
            Double ans = 0.0;
            for(ArrayList<Double> tree : arr){
                for(Double val : tree){
                    ans += val;
                }
            }
            return ans;
        }
        else{
            SunCalculation left = new SunCalculation(arr, lo, (hi+lo)/2);
            SunCalculation right = new SunCalculation(arr, (hi+lo)/2, hi);

            left.fork();
            double rightAns = right.compute();
            double leftAns = left.join();
            return rightAns + leftAns;
        }
    }
}
