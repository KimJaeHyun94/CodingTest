import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class JO1828_냉장고_김재현 {

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int N=sc.nextInt();
		int arr[][]=new int[N][2];
		
		for (int i = 0; i < N; i++) {
			int min=sc.nextInt();
			int max=sc.nextInt();
			arr[i][0]=min;
			arr[i][1]=max;
		}
		
		Arrays.sort(arr,new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[0]-o2[0];
			}
		});
		int min=-Integer.MAX_VALUE;
		int max=Integer.MAX_VALUE;
		int cnt=1;
		for (int i = 0; i < N; i++) {
			if(arr[i][0]>max) {
				min=arr[i][0];
				max=arr[i][1];
				cnt++;
			}
			else
			{
				min=Math.max(min, arr[i][0]);
				max=Math.min(max, arr[i][1]);
			}
		}
		System.out.println(cnt);
	}
}
