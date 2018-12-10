public class InsertionSort {
	public int[] sort(int[] arr) {
		int counter=0;
		for(int i=1;i<arr.length;i++){
			for(int j=i; j>0 && arr[j-1]>arr[j];j--){
				counter++;
				int tmp=arr[j-1];
				arr[j-1]=arr[j];
				arr[j]=tmp;
			}
		}
		return arr;
	}
}
