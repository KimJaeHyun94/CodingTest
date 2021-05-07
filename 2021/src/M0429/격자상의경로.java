package M0429;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 격자상의경로 {
	static int N, M, K;
	static long dp[][];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		dp = new long[N][M];

		for (int i = 0; i < N; i++) {
			Arrays.fill(dp[i], -1);
		}
		long ans = 0;
		if (K == 0) {
			ans = dfs(0, 0, N - 1, M - 1);
		} else { // 경유지가 있다.
			int kx = (K - 1) / M;
			int ky = (K - 1) % M;
			
			ans = dfs(0,0,kx,ky)*dfs(kx,ky,N-1,M-1);
		}
		
		System.out.println(ans);
	}

	private static long dfs(int x, int y, int ex, int ey) {
		if (ex == x && ey == y) {
			return 1;
		}
		if (dp[x][y] != -1) {
			return dp[x][y];
		}
		dp[x][y] = 0;

		if (x >= 0 && x < ex) {
			dp[x][y] += dfs(x + 1, y, ex, ey);
		}
		if (y >= 0 && y < ey) {
			dp[x][y] += dfs(x, y + 1, ex, ey);
		}

		return dp[x][y];
	}

}
