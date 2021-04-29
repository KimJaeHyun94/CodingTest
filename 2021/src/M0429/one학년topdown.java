package M0429;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class one학년topdown {
	static int N;
	static int map[];
	static long dp[][];

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N];

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			map[i] = Integer.parseInt(st.nextToken());
		}

		dp = new long[N][21];

		for (int i = 0; i < N; i++) {
			Arrays.fill(dp[i], -1);
		}

		System.out.println(dfs(0, map[0]));

	}

	private static long dfs(int i, int j) {
		if (j < 0 || j > 20) { // 범위 넘어가면 0
			return 0;
		}
		if (i == N - 2) {
			if (j == map[N - 1])
				return 1;
			else
				return 0;
		}
		if (dp[i][j] != -1) {
			return dp[i][j];
		}
		dp[i][j] = 0;

		return dp[i][j] += dfs(i + 1, j + map[i + 1]) + dfs(i + 1, j - map[i + 1]);
	}
}
