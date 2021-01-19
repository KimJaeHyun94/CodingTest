import java.util.Scanner;
 
public class SWEA_1208_D3_Flattern {
     
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
 
        for (int i = 1; i <= 10; i++) {
            int N=sc.nextInt();
            int arr[]=new int[100];
            for (int j = 0; j < 100; j++) {
                arr[j]=sc.nextInt();
            }
         
            
            for (int j = 0; j < N; j++) {
                int min=Integer.MAX_VALUE;
                int minindex=0;
                int max=Integer.MIN_VALUE;
                int maxindex=0;
                int temp=0;
                for (int j2 = 0; j2 < 100; j2++) {
                    if(min>arr[j2]) {
                        min=arr[j2];
                        minindex=j2;
                        }
                    if(max<arr[j2]) {
                        max=arr[j2];
                        maxindex=j2;
                        }
            }
            arr[minindex]++;
            arr[maxindex]--;
            }
            int min=Integer.MAX_VALUE;
            int max=Integer.MIN_VALUE;
            for (int j2 = 0; j2 < 100; j2++) {
                if(min>arr[j2]) {
                    min=arr[j2];
                    }
                if(max<arr[j2]) {
                    max=arr[j2];
                    }
                }
            System.out.println("#"+i+" "+(max-min));
        }
    }
}
