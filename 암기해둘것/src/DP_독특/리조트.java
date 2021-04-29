package DP_독특;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 리조트 {
	static boolean resort[];
	static int dp[][];
	static int N, M;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		resort = new boolean[N + 1];

		if (M > 0) {
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < M; i++) {
				resort[Integer.parseInt(st.nextToken())] = true;
			}
		}

		dp = new int[N + 1][N + 1];
		System.out.println(dfs(1, 0));

	}

	private static int dfs(int d, int c) {
		if (d > N)
			return 0;
		if (dp[d][c] != 0)
			return dp[d][c];
		if (resort[d]) { // 방문하지 못하는 날은 그 다음날로 넘긴다.
			dp[d][c] = dfs(d + 1, c);
			return dp[d][c];
		}

		dp[d][c] = Math.min(dfs(d + 1, c) + 10000, Math.min(dfs(d + 3, c + 1) + 25000, dfs(d + 5, c + 2) + 37000));
		if (c >= 3) { // 쿠폰 3장 이상 쓰면
			dp[d][c] = Math.min(dp[d][c], dfs(d + 1, c - 3));
		}
		return dp[d][c];

	}
}
