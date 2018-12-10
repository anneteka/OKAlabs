package comparator;
import java.util.Comparator;

import help.Messages;
import help.NullArrayOrComparatorException;

public class SelectionWithComparator implements ComparatorSort {

    @Override
    public void sort(final Object[] aArray, final Comparator aComparator) {
        if (aArray == null || aComparator == null) {
         //   throw new NullArrayOrComparatorException(Messages.NULL_ARRAY_OR_COMPARATOR_MESSAGE.toString());
        }

        for (int i = 0; i < aArray.length; i++) {
            int min = findMin(aArray, aComparator, i);
            exchange(aArray, i, min);
        }
    }


    private int findMin(final Object[] aArray, final Comparator aComparator, final int aI) {
        int min = aI;
        for (int j = aI + 1; j < aArray.length; j++) {
            if (less(aComparator, aArray[j], aArray[min])) {
                min = j;
            }
        }
        return min;
    }


    private boolean less(final Comparator aComparator, final Object aThis, final Object aThat) {
        return aComparator.compare(aThis, aThat) < 0;
    }


    private void exchange(final Object[] aArray, final int aI, final int aMin) {
        final Object aux = aArray[aI];
        aArray[aI] = aArray[aMin];
        aArray[aMin] = aux;
    }
}