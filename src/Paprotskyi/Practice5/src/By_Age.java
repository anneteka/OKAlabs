import java.util.Comparator;

public class By_Age implements Comparator<Worker> {
        @Override
        public int compare(Worker v, Worker w) {
            return v.compareTo(w);
        }
    }
