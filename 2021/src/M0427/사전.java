package M0427;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 사전 {
	static int N, M, K;
	static long dp[][];
	static int skip;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		dp = new long[201][201];

		for (int i = 0; i <= 200; i++) {
			for (int j = 0; j <= i; j++) {
				if (j == 0 || j == i) {
					dp[i][j] = 1;
					continue;
				}
				dp[i][j] = Math.min(1000000000, dp[i - 1][j] + dp[i - 1][j - 1]);
			}
		}

		if (dp[N + M][M] <= K - 1) {
			System.out.println(-1);
		} else {
			skip = K - 1;
			solve(N, M, "");
		}
	}

	private static void solve(int n, int m, String str) {
		if (skip < 0)
			return;
		if (n == 0 && m == 0) {
			if (skip == 0)
				System.out.println(str);
			--skip;
			return;
		}

		if (dp[n + m][m] <= skip) {
			skip -= dp[n + m][m];
			return;
		}
		if (n > 0) {
			solve(n - 1, m, str + 'a');
		}
		if (m > 0) {
			solve(n, m - 1, str + 'z');
		}
	}
}
