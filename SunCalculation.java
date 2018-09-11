import java.util.concurrent.RecursiveTask;
import java.util.ArrayList;

public class SunCalculation extends RecursiveTask<Double>{
    int lo;
    int hi;
    ArrayList<Double> arr;
    static int SEQUENTIAL_CUTOFF = 500;
    double ans = 0;

    public SunCalculation(ArrayList<Double> a, int l, int h){
        lo = l;
        hi = h;
        arr = a;
    }

    @Override
    protected Double compute(){
        if((hi - lo) < SEQUENTIAL_CUTOFF){
            double ans = 0;
            for(Double valu : arr){
                ans = ans + valu;
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
