package M1222;

public class 등굣길 {
	static int MOD = 1000000007;

	public static void main(String[] args) {
		solution(4, 3, new int[][] { { 2, 2 } });
	}

	public static int solution(int m, int n, int[][] puddles) {
		int answer = 0;
		int dp[][] = new int[n][m];
		dp[0][0] = 1;

		for (int[] pud : puddles) {
			dp[pud[1] - 1][pud[0] - 1] = -1;
		}

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (dp[i][j] == -1) {
					dp[i][j] = 0;
					continue;
				}
				if (i >= 1)
					dp[i][j] += dp[i - 1][j] % MOD;
				if (j >= 1)
					dp[i][j] += dp[i][j - 1] % MOD;
			}
		}

		answer = dp[n - 1][m - 1]%MOD;
		return answer;
	}
}


