import java.util.Scanner;

public class KingGunHo {
	private static int arr[];
	private static int origin[];
	private static int N;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			N = sc.nextInt();
			arr = new int[N+1];
			origin = new int[N+1];
			for (int i = 1; i <=N; i++) {
				arr[i] = sc.nextInt();
			}
			int count = 0;
			for (int i = 1; i <= N; i++) {
				if (arr[i] == 1 && origin[i]==0) {
					change(i);
					origin[i]=1;
					count++;
				} 
				else if(arr[i]==0 && origin[i]==1){
					change(i);
					origin[i]=0;
					count++;
				}
				else
					continue;
			}
			System.out.println("#"+tc+" "+count);
		}
	}
	public static void change(int i) {
		for (int j = i + 1; j <= N; j++) {
			if (j % i == 0) {
				if(origin[j]==0) {
					origin[j] = 1;
				}
				else
					origin[j]=0;
			}
		}
	}
}
