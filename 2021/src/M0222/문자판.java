package M0222;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 문자판 {

	static int N, M, K, L;
	static char map[][];
	static int memo[][][];
	static char Route[];
	static int[][] dirs = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		map = new char[N][M];
		for (int r = 0; r < N; r++) {
			String line = br.readLine();
			for (int c = 0; c < M; c++) {
				map[r][c] = line.charAt(c);
			}
		}

		String line = br.readLine();
		Route = line.toCharArray();
		L = Route.length;
		memo = new int[N][M][Route.length];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				Arrays.fill(memo[i][j], -1);
			}
		}
		int ans = 0;
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				if (map[r][c] == Route[0]) {
					ans += DFS(r, c, 0);
				}
			}
		}
		System.out.println(ans);
	}

	private static int DFS(int r, int c, int i) {
		if (i == L - 1) {
			return memo[r][c][i] = 1;
		}
		if (memo[r][c][i] != -1) {
			return memo[r][c][i];
		}
		memo[r][c][i] = 0;
		for (int k = 1; k <= K; k++) {
			for (int d = 0; d < dirs.length; d++) {
				int dr = r + dirs[d][0] * k;
				int dc = c + dirs[d][1] * k;
				if (isOK(dr, dc) && Route[i + 1] == map[dr][dc]) {
					memo[r][c][i] += DFS(dr, dc, i + 1);
				}
			}
		}
		return memo[r][c][i];
	}

	private static boolean isOK(int dr, int dc) {
		return dr >= 0 && dr < N && dc >= 0 && dc < M;
	}

}
