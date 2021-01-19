import java.util.Scanner;

public class SWEA_2817_D3_부분수열의합_김재현 {

	static int[] arr;
	static int S;
	static boolean[] child = { true, false };
	// 각 원소의 포함 여부를 저장할 배열
	static boolean[] status;
	static int cnt = 0;

	public static void dfs(int n, int r) {
		if (r == n ) {
			int sum=0;
			for (int i = 0; i < status.length; i++) {
				if (status[i]) {
					sum+=arr[i];
				}
			}
			if(sum==S)
				cnt++;
		} else {
			for (int i = 0; i < child.length; i++) {
				status[r] = child[i];
				dfs(n, r + 1);
			}
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			cnt=0;
			int N = sc.nextInt();
			S = sc.nextInt();
			arr = new int[N];
			status = new boolean[N];
			for (int i = 0; i < N; i++) {
				arr[i] = sc.nextInt();
			}
			dfs(N, 0);
			if (S == 0)
				cnt--;
			System.out.println("#" + tc + " " + cnt);
		}

	}

}