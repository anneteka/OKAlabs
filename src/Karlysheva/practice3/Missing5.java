package practice3;

public class Missing5 {
    /*
    время О(n^2)
    место О(1)
    */
    int missing(int n, int[] numbers) {
        int sum=0;
        for (int i = 0; i < numbers.length; i++) {
            sum+=numbers[i];
        }
        return (n*(n+1))/2-sum;
    }

    public static void main(String[] args) {
        Missing5 m = new Missing5();
        System.out.println(m.missing(3, new int[]{3,0,2}));
        System.out.println(m.missing(5, new int[]{5,4,3,1,0}));
    }
}
