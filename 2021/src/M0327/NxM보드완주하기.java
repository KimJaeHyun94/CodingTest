package M0327;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class NxM보드완주하기 {
	static int dirs[][] = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
	static int N, M;
	static boolean visited[][];
	static int ans, cnt;

	static char map[][];
	static StringTokenizer st = null;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int idx = 1;
		st = null;
		StringBuilder sb = new StringBuilder();
		String str = null;
		while ((str = br.readLine()) != null) {
			st = new StringTokenizer(str);
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());

			ans = Integer.MAX_VALUE;
			cnt = 0;
			map = new char[N][M];
			visited = new boolean[N][M];

			for (int r = 0; r < N; r++) {
				String line = br.readLine();
				for (int c = 0; c < M; c++) {
					map[r][c] = line.charAt(c);
					if (map[r][c] == '.') {
						cnt++;
					}
				}
			}
			if (cnt == 0) {
				System.out.println("Case " + idx + ": -1");
				continue;
			}
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < M; c++) {
					if (map[r][c] == '.') {
						for (int d = 0; d < dirs.length; d++) {
							visited[r][c] = true;
							DFS(r, c, d, 1, 1);
							visited[r][c] = false;
						}
					}
				}
			}
			if (cnt == 1) {
				System.out.println("Case " + idx + ": 0");
			} else {
				if (ans == Integer.MAX_VALUE) {
					System.out.println("Case " + idx + ": -1");
				} else {
					System.out.println("Case " + idx + ": " + ans);
				}
			}
			idx++;
		}
	}

	private static void DFS(int r, int c, int d, int k, int l) {

		if (k == cnt) {
			ans = Math.min(ans, l);
			return;
		}
		int dr = r + dirs[d][0];
		int dc = c + dirs[d][1];

		if (isOK(dr, dc) && !visited[dr][dc] && map[dr][dc] == '.') {
			visited[dr][dc] = true;
			DFS(dr, dc, d, k + 1, l);
			visited[dr][dc] = false;
		} else {
			for (int dir = 0; dir < dirs.length; dir++) {
				int nr = r + dirs[dir][0];
				int nc = c + dirs[dir][1];

				if (isOK(nr, nc) && !visited[nr][nc] && map[nr][nc] == '.') {
					visited[nr][nc] = true;
					DFS(nr, nc, dir, k + 1, l + 1);
					visited[nr][nc] = false;
				}
			}
		}
	}

	private static boolean isOK(int dr, int dc) {
		return dr >= 0 && dr < N && dc >= 0 && dc < M;
	}
}
