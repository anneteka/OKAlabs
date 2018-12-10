import java.util.Comparator;

public class SmthIntComporator  implements Comparator<Smth> {

    @Override
    public int compare(Smth o1, Smth o2) {
        if(o1.getI()> o2.getI())
            return 1;
        else if(o1.getI()< o2.getI())
            return -1;
        else
            return 0;
    }
}
