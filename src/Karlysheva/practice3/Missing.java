package practice3;

public class Missing {
    /*
    O(n)
    space O(n)

    проходит по массиву, копирует все елементы а в ячейку [a]
    */
    int[] income;
    int[] outcome;
    int n;

    int missing(int n, int[] numbers) {
        income = numbers;
        outcome=new int[n+1];
        this.n = n;
        sort();
        for (int i = 0; i < outcome.length; i++) {
            if (outcome[i]+1!=outcome[i+1]) return i+1;
        }
        return 0;
    }

    void sort() {
        int a, b;
        for (int i = 0; i < income.length; i++) {
            a=income[i];
            if (a!=n) {
                b = income[a];
                outcome[a]=a;
                outcome[i]=b;
            }
            else {
                outcome[a]=a;
            }
        }
    }

    public static void main(String[] args) {
        Missing m = new Missing();
        System.out.println(m.missing(3, new int[]{3,0,2}));
        System.out.println(m.missing(5, new int[]{5,4,3,1,0}));
        //System.out.println(m.missing(3, new int[]{3,0,2}));
    }
}
