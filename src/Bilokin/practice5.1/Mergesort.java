public class Mergesort {
    private int[] nub;
    private int[] help;

    private int number;

    public void sort(int[] values) {
        this.nub = values;
        number = values.length;
        this.help = new int[number];
        msortix(0, number - 1);
    }

    private void msortix(int low, int high) {
        if (low < high) {
            int middle = low + (high - low) / 2;
            msortix(low, middle);
            msortix(middle + 1, high);
            mm(low, middle, high);
        }
    }

    private void mm(int low, int middle, int high) {
        for (int i = low; i <= high; i++) {
            help[i] = nub[i];
        }

        int i = low;
        int j = middle + 1;
        int k = low;
        while (i <= middle && j <= high) {
            if (help[i] <= help[j]) {
                nub[k] = help[i];
                i++;
            } else {
                nub[k] = help[j];
                j++;
            }
            k++;
        }
        while (i <= middle) {
            nub[k] = help[i];
            k++;
            i++;
        }

    }
}