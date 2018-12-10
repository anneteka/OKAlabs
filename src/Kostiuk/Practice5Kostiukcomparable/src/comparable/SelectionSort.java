package comparable;
import help.Messages;
import help.NullArrayException;

/**
 * @author dbesliu
 * @created 4/2/13
 */
public class SelectionSort implements Sort {

    @Override
    public void sort(final Comparable[] aArray) {
        if (aArray == null) {
            throw new NullArrayException(Messages.NULL_ARRAY_EXCEPTION_MESSAGE.toString());
        }

        for (int i = 0; i < aArray.length; i++) {
            int min = findMin(aArray, i);
            exchange(aArray, i, min);
        }
    }


    private int findMin(final Comparable[] aArray, final int aI) {
        int min = aI;
        for (int j = aI + 1; j < aArray.length; j++) {
            if (less(aArray[j], aArray[min])) {
                min = j;
            }
        }
        return min;
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