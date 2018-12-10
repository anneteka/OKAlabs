import java.util.Comparator;

public class By_Grade implements Comparator<Worker> {
        @Override
        public int compare(Worker v, Worker w) {
            return v.compareToByGrade(w);
        }
}