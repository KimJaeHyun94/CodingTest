package M0428;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 크리보드topdown {
	static long dp[] = new long[101];

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		if (N < 7) {
			System.out.println(N);
		} else {

			System.out.println(dfs(N));
		}
	}

	private static long dfs(int n) {
		if (n <= 6) {
			return n;
		}
		if (dp[n] != 0) {
			return dp[n];
		}

		dp[n] = dfs(n - 1) + 1;
		for (int i = 1; i <= n - 3; i++) {
			dp[n] = Math.max(dp[n], dfs(n - (i + 2)) * (i + 1));
		}

		return dp[n];
	}
}
