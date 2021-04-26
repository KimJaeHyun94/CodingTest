package DP_DFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 게임 {
	static int N, M;
	static int map[][];
	static int dirs[][] = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
	static int dp[][];
	static boolean visited[][];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		visited = new boolean[N][M];
		dp = new int[N][M];
		for (int i = 0; i < N; i++) {
			String line = br.readLine();
			for (int j = 0; j < M; j++) {
				char ch = line.charAt(j);
				if (ch == 'H') {
					map[i][j] = 0;
				} else
					map[i][j] = ch - '0';
			}
		}
		System.out.println(DFS(0, 0));

	}

	private static int DFS(int r, int c) {
		if (!isOK(r, c) || map[r][c] == 0)
			return 0;
		if (visited[r][c]) {
			System.out.println(-1);
			System.exit(0);
		}
		if(dp[r][c]!=0) {
			return dp[r][c];
		}
		visited[r][c] = true;
		for (int d = 0; d < dirs.length; d++) {
			dp[r][c] = Math.max(dp[r][c], DFS(r + map[r][c] * dirs[d][0], c + map[r][c] * dirs[d][1]) + 1);
		}
		visited[r][c] = false;
		return dp[r][c];
	}

	private static boolean isOK(int r, int c) {
		return r >= 0 && r < N && c >= 0 && c < M;
	}

}
