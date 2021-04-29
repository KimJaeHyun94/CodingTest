package DP_독특;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class RGB거리2 {
	static int dp[][], cost[][];
	static final int INF = 987654321;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		cost = new int[N + 1][3]; // 빨, 초, 파
		dp = new int[N + 1][3];
		for (int i = 1; i <= N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 3; j++) {
				cost[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		int ans = INF;
		for (int k = 0; k < 3; k++) {
			for (int i = 0; i < 3; i++) {
				dp[1][i] = INF;
			}
			dp[1][k] = cost[1][k];
			for (int i = 2; i <= N; i++) {
				dp[i][0] = Math.min(dp[i - 1][1], dp[i - 1][2]) + cost[i][0];
				dp[i][1] = Math.min(dp[i - 1][0], dp[i - 1][2]) + cost[i][1];
				dp[i][2] = Math.min(dp[i - 1][0], dp[i - 1][1]) + cost[i][2];
			}
			for (int i = 0; i < 3; i++) {
				if (i == k)
					continue;
				ans = Math.min(ans, dp[N][i]);
			}
		}
		System.out.println(ans);
	}
}
