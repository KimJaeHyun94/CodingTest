package M0302;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_7699_D4_수지의수지맞는여행 {
	static int R, C;
	static char map[][];
	private static int dir[][] = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
	private static boolean visited[];
	private static int max = Integer.MIN_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());

		for (int tc = 1; tc <= T; tc++) {
			 max = Integer.MIN_VALUE;
			st = new StringTokenizer(br.readLine());
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			map = new char[R][C];
			visited = new boolean[26];

			for (int i = 0; i < R; i++) {
				String str = br.readLine();
				for (int j = 0; j < C; j++) {
					map[i][j] = str.charAt(j);
				}
			}
			DFS(0, 0, 1);
			System.out.println("#" + tc + " " + max);
		}
	}

	private static void DFS(int y, int x, int d) {
		max = Math.max(max, d);
		visited[map[y][x] - 'A'] = true;
		if (max == 26)
			return;
		for (int i = 0; i < dir.length; i++) {
			int dy = y + dir[i][0];
			int dx = x + dir[i][1];

			if (isOK(dy, dx)) {
				DFS(dy, dx, d + 1);
			}
		}
		visited[map[y][x] - 'A'] = false;
	}

	private static boolean isOK(int dy, int dx) {
		if (dy >= 0 && dy < R && dx >= 0 && dx < C) {
			if (!visited[map[dy][dx] - 'A']) {
				return true;
			}
		}
		return false;
	}
}
