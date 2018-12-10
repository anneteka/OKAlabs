public class Animal implements Comparable<Animal>{

	int legs;
	int age;
	String name;
	
	Animal(int legs,int age ,String name){
		this.legs = legs;
		this.age = age;
		this.name = name;
	}
	
	@Override
	public int compareTo(Animal other) {
		if(this.age == other.age) return 0;
		else if(this.age > other.age) return 1;
		else return -1;
	}
	
	public String toString() {
		return "\nName : " + name + "\nAge : " + age + "\nLegs : "+legs ;
		
	}

}
