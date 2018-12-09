import java.math.BigInteger;
import java.util.Scanner;

public class NewPlanet {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int numberOfEv = sc.nextInt();
        BigInteger first = new BigInteger(sc.next());
        BigInteger second = new BigInteger(sc.next());
        BigInteger two = new BigInteger("2");
        BigInteger power = two.pow(numberOfEv);
        BigInteger powerPrev = two.pow(--numberOfEv);



        while (!((first.compareTo(power) < 0 && first.compareTo(powerPrev)>= 0) && (second.compareTo(power) < 0 && second.compareTo(powerPrev)>= 0))){
            if (first.compareTo(second) > 0){
                first = first.divide(two);
            }else {
                second = second.divide(two);
            }

            power = powerPrev;
            powerPrev = two.pow(--numberOfEv);
        }

        while (first.compareTo(second) != 0) {
            first = first.divide(two);
            second = second.divide(two);
        }

        System.out.println(first.toString());
    }
}