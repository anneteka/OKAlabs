
public class SquareRoot {

	public static void main(String[] args) {
		System.out.println(sqrt(10));

	}

	
	public static int sqrt(int number) {
		double thisX=number;
		double nextX = number / 2;
		
		while ((thisX - nextX) != 0) {
			
			thisX = nextX;
			nextX = ((number / thisX)+thisX ) / 2;
			
		} 
		return (int)nextX;
	}
}
