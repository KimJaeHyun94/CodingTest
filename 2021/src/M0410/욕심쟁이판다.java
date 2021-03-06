package M0410;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 욕심쟁이판다 {
	static int dirs[][] = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
	static int N;
	static int map[][];
	static int memo[][];
	static int max = Integer.MIN_VALUE;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		memo = new int[N][N];

		for (int r = 0; r < N; r++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int c = 0; c < N; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				max = Math.max(max, DFS(r, c));
			}
		}

		System.out.println(max);
	}

	private static int DFS(int r, int c) {
		if (memo[r][c] != 0) {
			return memo[r][c];
		}

		memo[r][c] = 1; // 처음에 시간 재기

		for (int d = 0; d < dirs.length; d++) {
			int dr = r + dirs[d][0];
			int dc = c + dirs[d][1];

			if (isOK(dr, dc) && map[dr][dc] > map[r][c]) {
				memo[r][c] = Math.max(memo[r][c], DFS(dr, dc) + 1);
				DFS(dr, dc);
			}
		}
		return memo[r][c];
	}

	private static boolean isOK(int dr, int dc) {
		return dr >= 0 && dr < N && dc >= 0 && dc < N;
	}
}
