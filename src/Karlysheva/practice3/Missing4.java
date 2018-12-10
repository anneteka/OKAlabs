package practice3;

public class Missing4 {
    /*
    складність О(n)
    */
    int n;
    int income[];
    int missing(int n, int[] numbers) {
        int sum=0;
        for (int i = 0; i < numbers.length-1; i++) {
            sum+=numbers[i];
        }
        return n*(n-1)/2-sum;
    }



    public static void main(String[] args) {
        Missing4 m = new Missing4();
        System.out.println(m.missing(3, new int[]{3,0,2}));
        System.out.println(m.missing(5, new int[]{5,4,3,1,0}));
    }
}
