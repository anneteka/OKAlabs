package comparable;
import help.Messages;
import help.NullArrayException;

/**
 * @author dbesliu
 * @created 3/28/13
 */
public class Shell implements Sort {

    public void sort(final Comparable[] aArray) {
        if (aArray == null) {
            throw new NullArrayException(Messages.ARRAY_NOT_EMPTY_MESSAGE.toString());
        }

        int h = 1;
        final int length = aArray.length;
        while (h < length / 3) {
            h = h * 3 + 1;
        }

        while (h >= 1) {
            for (int i = h; i < length; i++) {
                for (int j = i; j >= h && less(aArray[j], aArray[j - h]); j -= h) {
                    exchange(aArray, j, j - h);
                }
            }
            h /= 3;
        }
    }


    private boolean less(final Comparable aThis, final Comparable aThat) {
        return aThis.compareTo(aThat) < 0;
    }


    private void exchange(final Comparable[] aArray, final int aMin, final int aMax) {
        final Comparable aux = aArray[aMax];
        aArray[aMax] = aArray[aMin];
        aArray[aMin] = aux;
    }
}