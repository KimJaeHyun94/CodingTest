package M0411;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 점프 {
	static long map[][];
	static long memo[][];
	static int dirs[][] = { { 0, 1 }, { 1, 0 } };
	static int N;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		map = new long[N][N];
		memo = new long[N][N];

		for (int r = 0; r < N; r++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int c = 0; c < N; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
				memo[r][c] = -1;
			}
		}

		System.out.println(DFS(0, 0));
	}

	private static long DFS(int r, int c) {
		if (memo[r][c] != -1) {
			return memo[r][c];
		}
		if (r == N - 1 && c == N - 1) {
			return 1;
		}
		memo[r][c] = 0;

		for (int d = 0; d < dirs.length; d++) {
			int dr = (int) (r + dirs[d][0] * map[r][c]);
			int dc = (int) (c + dirs[d][1] * map[r][c]);

			if (isOK(dr, dc)) {
				memo[r][c] += DFS(dr, dc);
				DFS(dr, dc);
			}

		}

		return memo[r][c];

	}

	private static boolean isOK(int dr, int dc) {
		return dr >= 0 && dr < N && dc >= 0 && dc < N;
	}
}
