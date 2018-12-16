import java.util.Scanner;

public class Main {
	private static int getMinQuantity(int[] colors) {
		int[] colorQuantity = new int[9];
		for(int i=0, length = colors.length;i<length;i++) {
			colorQuantity[colors[i]-1]	++;		
		}
		int max =  0;
		for(int i: colorQuantity) {
			if(max <i) max = i;
		}
		return colors.length-max;
	}
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		while(scanner.hasNextInt()) {
			int quantity = scanner.nextInt();
			int[] baloons = new int[quantity];
			for(int i=0, length = baloons.length;i<length;i++) {
				baloons[i] = scanner.nextInt();
			}
			System.out.println(Main.getMinQuantity(baloons));
		}
	}
}
