package comparable;
import shuffle.Shuffle;
import help.Messages;
import help.NullArrayException;

/**
 * @author dbesliu
 * @created 4/3/13
 */
public class QuickSort implements Sort {

    @Override
    public void sort(final Comparable[] aArray) {
        if (aArray == null) {
            throw new NullArrayException(Messages.NULL_ARRAY_EXCEPTION_MESSAGE.toString());
        }

        Shuffle<Comparable> shuffle = new Shuffle<Comparable>();
        shuffle.shuffle(aArray);
        sort(aArray, 0, aArray.length - 1);
    }


    private void sort(final Comparable[] aArray, final int aBegin, final int aEnd) {
        if (aEnd <= aBegin) {
            return;
        }

        int j = partition(aArray, aBegin, aEnd);
        sort(aArray, aBegin, j);
        sort(aArray, j + 1, aEnd);
    }


    private int partition(final Comparable[] aArray, final int aBegin, final int aEnd) {
        int j = 0;
        while (true) {
            int i = getI(aArray, aBegin, aEnd);
            j = getJ(aArray, aBegin, aEnd);
            if (i >= j) {
                break;
            }
            exchange(aArray, i, j);
        }
        exchange(aArray, aBegin, j);
        return j;
    }


    private int getJ(final Comparable[] aArray, final int aBegin, final int aEnd) {
        int j = aEnd + 1;
        while (less(aArray[aBegin], aArray[--j])) {
            if (j == aBegin) {
                break;
            }
        }
        return j;
    }


    private int getI(final Comparable[] aArray, final int aBegin, final int aEnd) {
        int i = aBegin;
        while (less(aArray[++i], aArray[aBegin])) {
            if (i == aEnd) {
                break;
            }
        }
        return i;
    }


    private void exchange(final Comparable[] aArray, final int aI, final int aJ) {
        final Comparable aux = aArray[aI];
        aArray[aI] = aArray[aJ];
        aArray[aJ] = aux;
    }


    private boolean less(final Comparable aThis, final Comparable aThat) {
        return aThis.compareTo(aThat) < 0;
    }
}