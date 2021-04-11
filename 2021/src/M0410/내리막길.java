package M0410;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 내리막길 {
	static int N, M;
	static int map[][];
	static int memo[][];
	static int dirs[][] = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());

		map = new int[M][N];
		memo = new int[M][N];

		for (int r = 0; r < M; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < N; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
				memo[r][c] = -1;
			}
		}

		System.out.println(DFS(0, 0));

	}

	private static int DFS(int r, int c) {
		if (r == M - 1 && c == N - 1) {
			return 1;
		}
		if (memo[r][c] != -1) {
			return memo[r][c];
		}
		memo[r][c] = 0;
		for (int d = 0; d < dirs.length; d++) {

			int dr = r + dirs[d][0];
			int dc = c + dirs[d][1];

			if (isOK(dr, dc) && map[dr][dc] < map[r][c]) {
				memo[r][c] += DFS(dr, dc);
				DFS(dr, dc);
			}
		}
		return memo[r][c];
	}

	private static boolean isOK(int dr, int dc) {
		return dr >= 0 && dr < M && dc >= 0 && dc < N;
	}
}
