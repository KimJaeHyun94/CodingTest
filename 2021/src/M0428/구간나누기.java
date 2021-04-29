package M0428;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 구간나누기 {
	static int N, M;
	static int dp[][];
	static int sum[];
	static int INF = -987654321;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		sum = new int[N + 1];
		dp = new int[N + 1][M + 1];

		for (int i = 1; i <= N; i++) {
			int n = Integer.parseInt(br.readLine());
			if (i == 1)
				sum[i] = n;
			else
				sum[i] = sum[i - 1] + n;
			Arrays.fill(dp[i], -1);
		}

		System.out.println(dfs(N, M));
	}

	private static int dfs(int n, int m) {
		if (m == 0) {
			return 0;
		}
		if (n <= 0) {
			return INF;
		}

		if (dp[n][m] != -1) {
			return dp[n][m];
		}

		dp[n][m] = dfs(n - 1, m);
		for (int i = n; i > 0; i--) {
			dp[n][m] = Math.max(dp[n][m], dfs(i-2, m-1)+sum[n]-sum[i-1]);
		}
		return dp[n][m];
	}

}
