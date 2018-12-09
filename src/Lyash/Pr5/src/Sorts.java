public class Sorts{

    class SelectionSort {
        // Selection sort algorithm
        public  void selectionSort(int[] numbers, int low, int high) {
            for (int h = low; h <= high; h++)
                swap(numbers, h, getSmallest(numbers, h, high));
        }

        // Get the position of the smallest value from numbers[low] to numbers[high]
        public  int getSmallest(int[] numbers, int low, int high) {
            int small = low;
            for (int i = low + 1; i <= high; i++)
                if (numbers[i] < numbers[small])
                    small = i;
            return small;
        }

        // swap numbers
        public  void swap(int[] numbers, int i, int j) {
            int temp = numbers[i];
            numbers[i] = numbers[j];
            numbers[j] = temp;
        }

    }

    public void insertionSort(int[] elements) {
        for (int i = 1; i < elements.length; i++) {
            int key = elements[i];
            int j = i - 1;
            while (j >= 0 && key < elements[j]) {
                elements[j + 1] = elements[j];
                j--;
            }// end while loop
            elements[j + 1] = key;
        }// end for loop
    }

    class MergeSort {

        /*
         * Internal method that makes recursive calls to sort the data
         * elements is the array of elements to be sorted
         * low is the left most position of the array
         * high is the right most position of the array
         */
        public void mergeSort(int[] elements, int low, int high) {
            if (low < high) { // list contains at least 2 elements
                int mid = (low + high) / 2;
                mergeSort(elements, low, mid); // recursion : sort first half
                mergeSort(elements, mid + 1, high); // recursion : sort second half
                merge(elements, low, mid, high); // merge both sorted halves
            }
        }

        /*
         * Merge sorted array of elements from low to mid and mid+1
         * low is the left most position of the subset of elements
         * high is the right most position of the subset of elements
         */
        private void merge(int[] subset, int low, int mid, int high) {

            int n = high-low+1;
            int[] Temp = new int[n];

            int i = low, j = mid + 1;
            int k = 0;

            while (i <= mid || j <= high) {
                if (i > mid)
                    Temp[k++] = subset[j++];
                else if (j > high)
                    Temp[k++] = subset[i++];
                else if (subset[i] < subset[j])
                    Temp[k++] = subset[i++];
                else
                    Temp[k++] = subset[j++];
            }
            for (j = 0; j < n; j++)
                subset[low + j] = Temp[j];
        } // end merge
    }


}
