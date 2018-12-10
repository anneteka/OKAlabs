package practice3;

public class Missing6 {
    /*
    время О(n^2)
    место О(1)
    */
    int missing(int n, int[] numbers) {
        int answ=n;
        for (int i = 0; i < numbers.length; i++) {
            answ^=numbers[i];
        }
        return answ;
    }

    public static void main(String[] args) {
        Missing6 m = new Missing6();
        System.out.println(m.missing(3, new int[]{3,0,2}));
        System.out.println(m.missing(5, new int[]{5,4,3,1,0}));
    }
}
