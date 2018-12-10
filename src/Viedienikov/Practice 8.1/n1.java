import java.util.Arrays;
import java.util.Scanner;

public class n1
{
    public static void main(String[] args)
    {
        Scanner s = new Scanner(System.in);
        int n=s.nextInt();
        double k = s.nextInt();
        M[] arr = new M[n];
        for(int i = 0; i < n; i++)
        {arr[i] = new M(s.nextDouble(), s.nextDouble());}
        Arrays.sort(arr);
        for (int i = 0; i < n; i++) {
            System.out.println(arr[i].points+" "+arr[i].time);
        }
        double minP=0;
        double minTime=0;
        int j=0;
        for (int i = 0; i < n; i++) {
            minP+=arr[j].points;
            minTime+=arr[j].time;
            j++;
            if(minP>=k){
                break;
            }
        }
        System.out.println(minTime);
    }
}

class M implements Comparable<M>
{
    public double points, time, pointsPerTime;
    public M(double points, double time)
    {
        this.time = time;
        this.points = points;
        this.pointsPerTime = points/time;
    }
    @Override
    public int compareTo(M that)
    {
        if(this.pointsPerTime > that.pointsPerTime)return -1;
        if(this.pointsPerTime < that.pointsPerTime)return 1;
        if(this.time > that.time)return -1;
        if(this.time < that.time)return 1;
        return 0;
    }
}