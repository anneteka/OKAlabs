package comparable;
import help.Messages;
import help.NullArrayException;

/**
 * @author dbesliu
 * @created 4/2/13
 */
public class InsertionSort implements Sort {

    @Override
    public void sort(final Comparable[] aArray) {
        if (aArray == null) {
            throw new NullArrayException(Messages.NULL_ARRAY_EXCEPTION_MESSAGE.toString());
        }

        for (int i = 0; i < aArray.length; i++) {
            sortAtLeft(aArray, i);
        }
    }


    private void sortAtLeft(final Comparable[] aArray, final int aI) {
        for (int j = aI; j > 0; j--) {
            if (less(aArray[j], aArray[j - 1])) {
                exchange(aArray, j - 1, j);
            }
        }
    }


    private boolean less(final Comparable aThis, final Comparable aThat) {
        return aThis.compareTo(aThat) < 0;
    }


    private void exchange(final Comparable[] aArray, final int aI, final int aJ) {
        final Comparable aux = aArray[aI];
        aArray[aI] = aArray[aJ];
        aArray[aJ] = aux;
    }
}