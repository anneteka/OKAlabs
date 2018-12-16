import java.util.Scanner;

public class Main {

    static boolean isOriented(int[][] matrix, int size) {
        for(int i=0;i<size;i++) {
            for(int j=0;j<size;j++) {
                if(i== j && matrix[i][j] != 0) return false;
                if(matrix[i][j] != matrix[j][i]) {
                    return false;
                }
                if(!(matrix[i][j] == 0 || matrix[i][j] == 1)) return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int size = scanner.nextInt();
        int[][] matrix = new int[size][size];
        for(int i=0;i<size;i++) {
            for(int j=0;j<size;j++) {
                matrix[i][j] = scanner.nextInt();
            }
        }
        System.out.println(isOriented(matrix, size) ? "YES" : "NO");
    }
}
