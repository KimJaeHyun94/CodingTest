package M0429;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 기타리스트topdown {
	static int N, S, M;
	static int V[];
	static int dp[][];
	static int INF = -987654321;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		V = new int[N + 1];

		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			V[i] = Integer.parseInt(st.nextToken());
		}

		dp = new int[N + 1][M + 1];

		for (int[] arr : dp) {
			Arrays.fill(arr, INF);
		}

		System.out.println(dfs(1, S));
	}

	private static int dfs(int idx, int s) {

		if (s > M || s < 0) {
			return -1;
		}

		if (idx == N + 1) {
			return s;
		}

		if (dp[idx][s] != INF) {
			return dp[idx][s];
		}

		dp[idx][s] = Math.max(dp[idx][s], Math.max(dfs(idx + 1, s + V[idx]), dfs(idx + 1, s - V[idx])));

		return dp[idx][s];
	}
}
