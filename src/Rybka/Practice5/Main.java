import java.util.ArrayList;
import java.util.Collections;

public class Main {

	public static void main(String[] args) {
		ArrayList<Animal> arr= new ArrayList<Animal>();
		
		arr.add(new Animal(1,5,"StaryiOdnonogyiGolub"));
		arr.add(new Animal(2,2,"Golub"));
		arr.add(new Animal(1,3,"OdnonogiyGolub"));
		
		Collections.sort(arr);
		
		for(Animal id : arr){
		      System.out.println(id);
		}
		
		System.out.println("\n\n");
		
		arr.add(new Animal(1,10,"OchenStaryiOdnonogyiGolub"));
		arr.add(new Animal(2,1,"MolodoyGolub"));
		arr.add(new Animal(3,4,"TrexnogiyGolub"));
		
		ZooKeeper golubiyatnik = new ZooKeeper();
		
		arr.sort(golubiyatnik);
		
		for(Animal id : arr){
		      System.out.println(id);
		}
	}

}
