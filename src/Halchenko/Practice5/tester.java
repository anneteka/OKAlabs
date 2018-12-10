public class tester {
	public static void main(String[] args) {
		BubleSort bb=new BubleSort();
		InsertionSort ins=new InsertionSort();
		Mergesort mer=new Mergesort();
		
		int[] array={10,23,24141,0,123,2,11,44,3,2,5};
		//bb.bubbleSort(array);//stable
		//ins.sort(array);//stable
		//mer.sort(array);//stable
		for(int i=0;i<array.length;i++){
			System.out.println(array[i]);
		}
		
		
	}
}
