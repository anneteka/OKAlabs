package practice5.competition;

public class Jumps {
    public static void main(String[] args) {

        System.out.println(findJumpsNN(2));
        System.out.println(findJumpsN1(2));
        System.out.println(findJumpsNN(10));
        System.out.println(findJumpsN1(10));
        System.out.println(findJumpsNN(15));
        System.out.println(findJumpsN1(15));
        System.out.println(findJumpsNN(20));
        System.out.println(findJumpsN1(20));
        System.out.println(findJumpsNN(25));
        System.out.println(findJumpsN1(25));

    }

    static int findJumpsNN(int n){
        if (n==1)return 1;
        if(n==2)return 2;
        int[] stairs = new int[n];
        stairs[0]=1;
        stairs[1]=2;
        for (int i = 2; i < n; i++)
            stairs[i]=stairs[i-1]+stairs[i-2];
        return stairs[n-1];
    }

    static int findJumpsN1(int n){
        if (n==1)return 1;
        if(n==2)return 2;
        int stair1=1;
        int stair2=2;
        for (int i = 2; i < n; i++) {
            stair2+=stair1;
            stair1=stair2-stair1;
        }
        return stair2;
    }
}
