package DP_배낭;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class 함께블록쌓기 {
	static int N, M, H;
	static List<Integer> graph[];
	static int MOD = 10007;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());

		graph = new List[N + 1];
		for (int i = 1; i <= N; i++) {
			graph[i] = new ArrayList<>();
		}

		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			while (st.hasMoreTokens()) {
				int child = Integer.parseInt(st.nextToken());
				graph[i].add(child);
			}
		}

		int dp[][] = new int[N + 1][H + 1];

		for (int i = 0; i <= N; i++)
			dp[i][0] = 1; // 미리 세팅

		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= H; j++) {
				dp[i][j] = dp[i - 1][j]; // 배낭문제
				for (int child : graph[i]) {
					if (j >= child) { // 만약 높이가 가지고있는 블록보다 크다면
						dp[i][j] += dp[i - 1][j - child] % MOD; //블록을 놓는다.
					}
				}
			}
		}
		System.out.println(dp[N][H]);

	}
}
