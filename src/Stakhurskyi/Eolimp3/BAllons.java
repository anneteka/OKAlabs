package Stakhurskyi.Eolimp3;

public class BAllons {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();

        while(N<1){
            System.out.println("Incorrect n; repeat: ");
            N = in.nextInt();
        }

        int arr[] = new int[9];
        for(int i=0;i<N;i++){
            int x = in.nextInt();
            while(x<1||x>9){
                System.out.println("Incorrect x; repeat: ");
                x = in.nextInt();
            }
            arr[x-1]++;
        }
        int max=0;
        for(int j=0;j<9;j++){
            if(arr[j]>max){
                max=arr[j];
            }
        }
        int check=N-max;

        System.out.println(check);
    }
}