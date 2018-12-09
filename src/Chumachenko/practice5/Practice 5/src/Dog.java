import java.util.Comparator;

public class Dog implements Comparable<Dog> {

public final Comparator<Dog> SortedByName = new SName();
public final Comparator<Dog>  SortedByAge = new SAge();
public final Comparator<Dog> SortedByBreed = new SBreed();

private String name;
private int age;
private String breed;

Dog(String name, String breed,  int age ){
	this.name = name;
	this.age =age;
	this.breed = breed;
	
}

public String toString() {
	return this.name+" "+this.breed+" "+this.age;
}

@Override
	public int compareTo(Dog o) {
		
		return this.name.compareTo(o.name);
	}

private static class SName implements Comparator<Dog>{

	@Override
	public int compare(Dog o1, Dog o2) {
		
		return o1.name.compareTo(o2.name);
	}
	
}

private static class SAge implements Comparator<Dog>{

	@Override
	public int compare(Dog o1, Dog o2) {
		
		return Integer.valueOf(o1.age).compareTo(Integer.valueOf(o2.age));
	}
	
}
	
private static class SBreed implements Comparator<Dog>{

	@Override
	public int compare(Dog o1, Dog o2) {
		
		return o1.breed.compareTo(o2.breed);
	}
	
}
}
