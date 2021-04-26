package M0423;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 우유도시 {
	static int N;
	static int map[][];
	static int dp[][][];
	static int dirs[][] = { { 1, 0 }, { 0, 1 } };

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];

		for (int r = 0; r < N; r++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int c = 0; c < N; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		dp = new int[N][N][4];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				Arrays.fill(dp[i][j], -1);
			}
		}

		System.out.println(DFS(0, 0, 0));

	}

	private static int DFS(int r, int c, int milk) {
		if (r == N || c == N) {
			return 0;
		}

		if (dp[r][c][milk] != -1) {
			return dp[r][c][milk];
		}

		dp[r][c][milk] = 0;

		for (int d = 0; d < 2; d++) {
			int dr = r + dirs[d][0];
			int dc = c + dirs[d][1];

			int want = (milk + 1) % 3;

			if (map[r][c] == milk) {
				dp[r][c][milk] = Math.max(dp[r][c][milk], DFS(dr, dc, want) + 1);
			} else
				dp[r][c][milk] = Math.max(dp[r][c][milk], DFS(dr, dc, milk));
		}
		return dp[r][c][milk];
	}
}