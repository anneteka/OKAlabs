public class msort {
    private int[] num;
    private int[] help;

    private int number;

    public int[] sort(int[] values) {
        this.num = values;
        number = values.length;
        this.help = new int[number];
        msort(0, number - 1);
        return num;
    }

    private void msort(int low, int high) {
        if (low < high) {
            int middle = low + (high - low) / 2;
            msort(low, middle);
            msort(middle + 1, high);
            me(low, middle, high);
        }
    }

    private void me(int low, int middle, int h) {
        for (int i = low; i <= h; i++) {
            help[i] = num[i];
        }

        int i = low;
        int j = middle + 1;
        int k = low;
        while (i <= middle && j <= h) {
            if (help[i] <= help[j]) {
                num[k] = help[i];
                i++;
            } else {
                num[k] = help[j];
                j++;
            }
            k++;
        }
        while (i <= middle) {
            num[k] = help[i];
            k++;
            i++;
        }
	
    }
    
}