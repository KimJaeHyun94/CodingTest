import java.util.Arrays;
import java.util.Scanner;

public class BJ1015_수열정렬 {

	public static void main(String[] args) {
		
		Scanner sc=new Scanner(System.in);
		int N=sc.nextInt();
		int arr[]=new int[N];
		int origin[]=new int[N];
		for (int i = 0; i < arr.length; i++) {
			arr[i]=sc.nextInt();
			origin[i]=arr[i];
		}
		Arrays.sort(arr);
		int P[]=new int[N];
		int cnt=0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(origin[i]==arr[j]) {
					P[cnt++]=j;
				}
			}
		}
		for (int i = 0; i < N; i++) {
			System.out.print(P[i]+" ");
		}
	}
}
