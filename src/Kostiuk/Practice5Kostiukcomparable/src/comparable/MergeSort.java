package comparable;
import help.Messages;
import help.NullArrayException;

/**
 * @author dbesliu
 * @created 4/2/13
 */
public class MergeSort implements Sort {

    @Override
    public void sort(final Comparable[] aArray) {
        if (aArray == null) {
            throw new NullArrayException(Messages.NULL_ARRAY_EXCEPTION_MESSAGE.toString());
        }

        final Comparable[] aux = new Comparable[aArray.length];
        sort(aArray, aux, 0, aArray.length - 1);
    }


    private void sort(final Comparable[] aArray, final Comparable[] aAux, final int aBegin, final int aEnd) {
        if (aBegin >= aEnd) {
            return;
        }

        final int middle = aBegin + (aEnd - aBegin) / 2;
        sort(aArray, aAux, aBegin, middle);
        sort(aArray, aAux, middle + 1, aEnd);
        merge(aArray, aAux, aBegin, middle, aEnd);
    }


    private void merge(final Comparable[] aArray, final Comparable[] aAux, final int aBegin, final int aMiddle, final int aEnd) {
        for (int i = aBegin; i <= aEnd; i++) {
            aAux[i] = aArray[i];
        }

        int i = aBegin;
        int j = aMiddle + 1;
        for (int k = aBegin; k <= aEnd; k++) {
            if (i > aMiddle) {
                aArray[k] = aAux[j++];
            } else if (j > aEnd) {
                aArray[k] = aAux[i++];
            } else if (less(aAux[j], aAux[i])) {
                aArray[k] = aAux[j++];
            } else {
                aArray[k] = aAux[i++];
            }
        }
    }


    private boolean less(final Comparable aThis, final Comparable aThat) {
        return aThis.compareTo(aThat) < 0;
    }
}