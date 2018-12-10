import java.util.Arrays;
import java.util.Comparator;

public class ShellSort<T extends Comparable> {
    private class DefaultComparator implements Comparator<T> {
        public int compare(T i1, T i2) {
            return i1.compareTo(i2);
        }
    }

    private void  hSort(T[] array, Comparator<T> comparator, int h) {
        for (int hi = 0; hi < h; hi++) {
            for (int  i = hi + h; i < array.length; i += h) {
                int ti = i;
                while (ti >= h && comparator.compare(array[ti], array[ti-h]) < 0) {
                    T temp = array[ti - h];
                    array[ti - h] = array[ti];
                    array[ti] = temp;
                    ti -= h;
                }
            }
        }
    }

    public void sort(T[] array, Comparator<T> comparator) {
        for (int i = (int)Math.floor(Math.log(array.length) / Math.log(2)); i >= 0; --i)
            this.hSort(array, comparator, (int)Math.pow(2, i) - 1);
    }

    public void sort(T[] array) {
        DefaultComparator def = new DefaultComparator();
        this.sort(array, def);
    }
}
