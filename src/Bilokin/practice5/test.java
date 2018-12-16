
public class test {
	public static void main(String[] args) {
		bublsort bb=new bublsort();
		insort ins=new insort();
		msort mer=new msort();
		
		int[] array={10,23,24141,0,123,2,11,44,3,2,5};
		//bb.bubbleSort(array);//stable
		//ins.sort(array);//stable
		//mer.sort(array);//stable
		for(int i=0;i<array.length;i++){
			System.out.println(array[i]);
		}
		
		
	}
}
