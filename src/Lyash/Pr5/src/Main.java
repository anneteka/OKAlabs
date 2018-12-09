public class Main {

    public static void main(String[] args) {
        Stopwatch st=null;

        Sorts sorts = new Sorts();
        int[] num = { 3,6,1,7,2,8,10,4,9,5};

        st = new Stopwatch();
        num = new int[]{3,6,1,7,2,8,10,4,9,5};
        System.out.println("Selection sort:");
        sorts. new SelectionSort().selectionSort(num,0,num.length-1);
        out(num,st);

        st = new Stopwatch();
        System.out.println("Merge sort(stable): ");
        sorts.new MergeSort().mergeSort(num,0,num.length-1);
        out(num,st);

        st = new Stopwatch();
        num = new int[]{3,6,1,7,2,8,10,4,9,5};
        System.out.println("Insertion sort(stable):");
        sorts.insertionSort(num);
        out(num,st);


    }

    public static void out(int[] num, Stopwatch stopwatch){
        for (int i = 0; i < num.length; i++) {
            System.out.print(num[i]+" ");
        }
        if(stopwatch!=null)
        System.out.println("Time: "+ stopwatch.elapsedTime());
        System.out.println();
    }
}
