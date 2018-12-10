package comparator;
import java.util.Comparator;

import help.Messages;
import help.NullArrayOrComparatorException;

public class InsertionWithComparator implements ComparatorSort {

    @Override
    public void sort(final Object[] aArray, final Comparator aComparator) {
        if (aArray == null || aComparator == null) {
          //  throw new NullArrayOrComparatorException(Messages.NULL_ARRAY_OR_COMPARATOR_MESSAGE.toString());
        }

        for (int i = 0; i < aArray.length; i++) {
            insertMin(aArray, aComparator, i);
        }
    }


    private void insertMin(final Object[] aArray, final Comparator aComparator, final int aI) {
        for (int j = aI; j > 0; j--) {
            if (less(aComparator, aArray[j], aArray[j - 1])) {
                exchange(aArray, j - 1, j);
            }
        }
    }


    private void exchange(final Object[] aArray, final int aMin, final int aJ) {
        final Object aux = aArray[aMin];
        aArray[aMin] = aArray[aJ];
        aArray[aJ] = aux;
    }


    private boolean less(final Comparator aComparator, final Object aThis, final Object aThat) {
        return aComparator.compare(aThis, aThat) < 0;
    }
}