import java.util.Comparator;

public class ZooKeeper implements Comparator<Animal>{

	@Override
	public int compare(Animal first, Animal second) {
		if(first.age == second.age) return 0;
		else if(first.age > second.age) return 1;
		else return -1;
	}

}
