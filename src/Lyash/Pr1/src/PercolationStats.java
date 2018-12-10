public class PercolationStats {
    public PercolationStats(int N, int T){
        Percolation pc;
        double[] xss = new double[T];
        for (int i = 0; i < T; i++) {
            pc = new Percolation(N);
            System.out.println("Test: "+(i+1));
            System.out.println(pc.getOpened()+ " "+pc.getAll());
            xss[i]=(pc.getOpened()/pc.getAll());
        }
        double m=mean(xss, T);
        System.out.println("Mean: "+m);
        System.out.println("Stddev: "+stddev(xss,m,T));
    }

    public double mean(double[] xss,int T){

        double up=0;
        for (int i = 0; i < T; i++) {
            up+=xss[i];
        }

        return up/T;
    }


    public double stddev(double[] xss,double m,int T){
        double up=0;
        for (int i = 0; i < T; i++) {
            up+=Math.pow((xss[i]-m),2);
        }

        return Math.sqrt(up/(T-1));
    }


    public static void main(String[] args) {

        PercolationStats ps = new PercolationStats(6,1);
    }

}
