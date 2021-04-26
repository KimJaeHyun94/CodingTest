package 비트마스킹;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 외판원순회 {
	static int N;
	static int W[][];
	static int dp[][];
	static int INF = 987654321;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		W = new int[N][N];
		dp = new int[N][(1 << N) - 1];

		for (int r = 0; r < N; r++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int c = 0; c < N; c++) {
				W[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		for (int i = 0; i < N; i++) {
			Arrays.fill(dp[i], INF);
		}

		System.out.println(DFS(0, 1));

	}

	private static int DFS(int n, int visit) {
		if (visit == (1 << N) - 1) {
			if (W[n][0] == 0)
				return INF;
			return W[n][0];
		}

		if (dp[n][visit] != INF)
			return dp[n][visit];

		for (int i = 0; i < N; i++) {
			if (W[n][i] == 0 || (visit & (1 << i)) != 0)
				continue;
			dp[n][visit] = Math.min(dp[n][visit], DFS(i, visit | (1 << i)) + W[n][i]);
		}
		return dp[n][visit];
	}

}
