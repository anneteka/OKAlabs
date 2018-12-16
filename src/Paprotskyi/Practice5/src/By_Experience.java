import java.util.Comparator;

public class By_Experience implements Comparator<Worker> {
        @Override
        public int compare(Worker v, Worker w) {
            return v.compareToByExperience(w);
        }
}
