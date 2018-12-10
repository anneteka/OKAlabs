import java.util.Comparator;

public class SmthStrComporator  implements Comparator<Smth> {
    @Override
    public int compare(Smth o1, Smth o2) {
        return o1.compareTo(o2);
    }
}
