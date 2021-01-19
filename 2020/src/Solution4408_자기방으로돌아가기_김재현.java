import java.util.Arrays;
import java.util.Scanner;

public class Solution4408_자기방으로돌아가기_김재현 {

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int T=sc.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			int N=sc.nextInt();
			int arr[][]=new int[N][2];
			for (int i = 0; i < N; i++) {
				arr[i][0]=sc.nextInt();
				arr[i][1]=sc.nextInt();
			}
			int arr2[]=new int[201];
			for (int i = 0; i < N; i++) {
				int lol=Math.max(arr[i][1],arr[i][0]);
				int lol2=Math.min(arr[i][1],arr[i][0]);
				if(lol%2==0)
					lol/=2;
				else
					lol=(lol+1)/2;
				if(lol2%2==0)
					lol2/=2;
				else
					lol2=(lol2+1)/2;
				for (int j = lol2; j <= lol; j++) {
						arr2[j]++;
					}
			}
			int max=0;
			for (int i = 0; i < 201; i++) {
				if(max<arr2[i])
					max=arr2[i];
			}
			System.out.println("#"+ tc+" "+max);
		}		
	}
}

