
public class insort {
	public int[] sort(int[] array) {
		int counter=0;
		for(int i=1;i<array.length;i++){
			for(int j=i; j>0 && array[j-1]>array[j];j--){
				counter++;
				int temp=array[j-1];
				array[j-1]=array[j];
				array[j]=temp;
			}
		}
		return array;
	}
}
