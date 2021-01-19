package M0515;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ1941_G3_소문난칠공주 {
	static char map[][];
	static char copy[][];
	static int dirs[][] = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
	static boolean check[];
	static boolean visited[][];
	static int ans;
	static int cnt;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		map = new char[5][5];

		for (int i = 0; i < 5; i++) {
			String str = br.readLine();
			for (int j = 0; j < 5; j++) {
				map[i][j] = str.charAt(j);
			}
		}
		for (int i = 0; i < 25; i++) {
			check = new boolean[5 * 5];
			visited = new boolean[5][5];
			comb(i, 1, 0);

		}
		System.out.println(ans);
	}

	private static void comb(int ti, int cnt, int s) {
		check[ti] = true;
		visited[ti / 5][ti % 5] = true;

		if (map[ti / 5][ti % 5] == 'S') {
			s++;
		}
		if (cnt == 7) {
			if (s >= 4) {
				sol();
			}
		} else {
			for (int i = ti + 1; i < 25; i++) {
				if (!check[i]) {
					comb(i, cnt + 1, s);
				}
			}
		}
		check[ti] = false;
		visited[ti / 5][ti % 5] = false;
	}

	private static void sol() {
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {

				if (visited[i][j]) {
					cnt = 1;
					DFS(i, j, new boolean[5][5]);
					return;
				}
			}
		}
	}

	private static void DFS(int y, int x, boolean visit[][]) {
		visit[y][x] = true;
		if (cnt == 7) {
			++ans;
		}
		for (int d = 0; d < dirs.length; d++) {
			int dy = y + dirs[d][0];
			int dx = x + dirs[d][1];

			if (dy >= 0 && dy < 5 && dx >= 0 && dx < 5) {
				if (visited[dy][dx] && !visit[dy][dx]) {
					++cnt;
					DFS(dy, dx, visit);
				}
			}

		}
	}
}
