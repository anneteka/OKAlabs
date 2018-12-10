package comparator;
import java.util.Comparator;

import help.Messages;
import help.NullArrayOrComparatorException;

public class MergeWithComparator implements ComparatorSort {

    private Comparator comparator;


    @Override
    public void sort(final Object[] aArray, final Comparator aComparator) {
        if (aArray == null || aComparator == null) {
         //   throw new NullArrayOrComparatorException(Messages.NULL_ARRAY_OR_COMPARATOR_MESSAGE.toString());
        }

        comparator = aComparator;
        final Object[] aux = new Object[aArray.length];
        sort(aArray, aux, 0, aArray.length - 1);
    }


    private void sort(final Object[] aArray, final Object[] aAux, final int aBegin, final int aEnd) {
        if (aBegin >= aEnd) {
            return;
        }

        final int middle = aBegin + (aEnd - aBegin) / 2;
        sort(aArray, aAux, aBegin, middle);
        sort(aArray, aAux, middle + 1, aEnd);
        merge(aArray, aAux, aBegin, middle, aEnd);
    }


    private void merge(final Object[] aArray, final Object[] aAux, final int aBegin, final int aMiddle, final int aEnd) {
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


    private boolean less(final Object aThis, final Object aThat) {
        return comparator.compare(aThis, aThat) < 0;
    }
}