package M0304;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ14620꽃길 {
	static int N;
	static int map[][];
	static int dir[][] = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
	static int min = Integer.MAX_VALUE;
	static boolean visited[][];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		visited = new boolean[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		dfs(0, 0, 1);
		System.out.println(min);
	}

	private static void dfs(int cnt, int sum, int start) {
		if (cnt == 3) {
			min = Math.min(min, sum);
			return;
		}

		for (int i = start; i < N - 1; i++) {
			for (int j = 1; j < N - 1; j++) {
				if (!isOK(i, j) || visited[i][j]) {
					continue;
				}
				int k = map[i][j];
				visited[i][j] = true;
				for (int k2 = 0; k2 < dir.length; k2++) {
					int dy = i + dir[k2][0];
					int dx = j + dir[k2][1];

					visited[dy][dx] = true;
					k += map[dy][dx];
				}
				dfs(cnt + 1, sum + k, i);

				visited[i][j] = false;
				for (int k2 = 0; k2 < dir.length; k2++) {
					int dy = i + dir[k2][0];
					int dx = j + dir[k2][1];
					visited[dy][dx] = false;
				}
			}

		}
	}

	private static boolean isOK(int y, int x) {
		for (int i = 0; i < 4; i++) {
			int dy = y + dir[i][0];
			int dx = x + dir[i][1];

			if (x < 0 || x >= N || y < 0 || y >= N || visited[dy][dx])
				return false;
		}
		return true;
	}
}
