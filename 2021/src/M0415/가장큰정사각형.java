package M0415;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 가장큰정사각형 {
	static int arr[][];
	static int dp[][];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		arr = new int[N][M];
		dp = new int[N][M];
		int ans = 0;
		for (int i = 0; i < N; i++) {
			String line = br.readLine();
			for (int j = 0; j < M; j++) {
				arr[i][j] = line.charAt(j) - '0';
				dp[i][j] = arr[i][j];
				if (dp[i][j] == 1)
					ans = 1;
			}
		}

		for (int i = 1; i < N; i++) {
			for (int j = 1; j < M; j++) {
				if (arr[i][j] == 1) {
					dp[i][j] = Math.min(dp[i][j - 1], Math.min(dp[i - 1][j], dp[i - 1][j - 1])) + 1;
				}
				ans = Math.max(dp[i][j], ans);
			}
		}
		System.out.println(ans * ans);

	}
}
