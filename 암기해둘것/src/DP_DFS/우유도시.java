package DP_DFS;

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

		dp = new int[N][N][4]; // 0 딸기 1 초코 2 바나나
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				Arrays.fill(dp[i][j], -1);
			}
		}
		if (map[0][0] == 0) // 딸기 우유를 마시고 시작한다
			System.out.println(DFS(0, 0, 0) + 1);
		else // 딸기 우유를 마시지 못하고 출발한다.
			System.out.println(DFS(0, 0, -1));
	}

	private static int DFS(int r, int c, int k) {
		
		if (dp[r][c][k + 1] != -1) {
			return dp[r][c][k+1];
		}

		dp[r][c][k + 1] = 0;

		for (int d = 0; d < dirs.length; d++) {
			int dr = r + dirs[d][0];
			int dc = c + dirs[d][1];
			
			if (isOK(dr, dc)) {
				int want = (k + 1) % 3;
				if (map[dr][dc] == want) { // 다음 칸에서 마실 수 있다면
					dp[r][c][k + 1] = Math.max(dp[r][c][k + 1], DFS(dr, dc, want) + 1); // 하나 마신다
				} else {
					dp[r][c][k + 1] = Math.max(dp[r][c][k + 1], DFS(dr, dc, k)); // 안마신다.
				}
			}
		}
		return dp[r][c][k + 1];
	}

	private static boolean isOK(int dr, int dc) {
		return dr >= 0 && dr < N && dc >= 0 && dc < N;
	}
}
