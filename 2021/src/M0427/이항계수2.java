package M0427;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 이항계수2 {
	static int N, K;
	static int dp[][];
	static int mod = 10007;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		dp = new int[N + 1][N + 1];
		for (int i = 0; i <= N; i++) {
			Arrays.fill(dp[i], -1);
		}
		System.out.println(dfs(N, K) % mod);

	}

	private static int dfs(int n, int k) {

		if (k == 0 || n == k) {
			return 1;
		}
		if (dp[n][k] != -1) {
			return dp[n][k];
		}

		dp[n][k] = 0;
		dp[n][k] = dfs(n - 1, k - 1) + dfs(n - 1, k) % mod;
		return dp[n][k];
	}

}
