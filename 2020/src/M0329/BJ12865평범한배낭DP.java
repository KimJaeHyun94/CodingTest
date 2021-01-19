package M0329;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BJ12865평범한배낭DP {
	static int N, K;
	static int dp[][];
	static bag bagging[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		dp = new int[N + 1][K + 1];
		bagging = new bag[N + 1];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			int w = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			bagging[i] = new bag(w, v);
		}
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= K; j++) {
				dp[i][j] = dp[i - 1][j];
				if (j - bagging[i].w >= 0) {
					dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - bagging[i].w] + bagging[i].v);
				}
			}
		}
		System.out.println(dp[N][K]);
	}

	static class bag {
		public int w;
		public int v;

		public bag(int w, int v) {
			this.w = w;
			this.v = v;
		}
	}
}
