package shuffle;

import java.util.*;

import help.Messages;
import help.NullArrayException;

/**
 * @author dbesliu
 * @created 4/1/13
 */
public class Shuffle<T> {

    public T[] shuffle(final T[] aArray) {
        if (aArray == null) {
            throw new NullArrayException(Messages.NULL_ARRAY_EXCEPTION_MESSAGE.toString());
        }

        Random random = new Random();
        for (int i = 0; i < aArray.length; i++) {
            int j = random.nextInt(i + 1);
            exchange(aArray, i, j);
        }

        return aArray;
    }


    private void exchange(final T[] aArray, final int aI, final int aJ) {
        final T aux = aArray[aI];
        aArray[aI] = aArray[aJ];
        aArray[aJ] = aux;
    }
}