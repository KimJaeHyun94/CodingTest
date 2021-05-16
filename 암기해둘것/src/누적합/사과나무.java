package 누적합;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 사과나무 {
	static int N;
	static int arr[][], dp[][];

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		arr = new int[N + 1][N + 1];
		dp = new int[N + 1][N + 1];

		for (int i = 1; i <= N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				dp[i][j] = dp[i - 1][j] + dp[i][j - 1] - dp[i - 1][j - 1] + arr[i][j];
			}
		}

		int ans = Integer.MIN_VALUE;
		for (int i = 1; i <= N; i++) {
			for (int j = 0; j < N - i + 1; j++) {
				for (int k = 0; k < N - i + 1; k++) {
					int nr = j + i;
					int nc = k + i;
					ans = Math.max(ans, dp[nr][nc] - dp[nr][k] - dp[j][nc] + dp[j][k]);
				}
			}
		}
		System.out.println(ans);
	}
}
