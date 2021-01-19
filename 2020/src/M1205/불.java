package M1205;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 불 {
	static int R, C, T;
	static char map[][];
	static Queue<Fire> Miro;
	static boolean visited[][];
	static int ans;
	static int dirs[][] = { { 0, -1 }, { 1, 0 }, { 0, 1 }, { -1, 0 } };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		T = Integer.parseInt(st.nextToken());
		for (int tc = 0; tc < T; tc++) {
			st = new StringTokenizer(br.readLine());
			C = Integer.parseInt(st.nextToken());
			R = Integer.parseInt(st.nextToken());
			ans = -1;
			map = new char[R][C];
			visited = new boolean[R][C];
			Fire player = null;
			Miro = new LinkedList<>();

			for (int r = 0; r < R; r++) {
				String line = br.readLine();
				for (int c = 0; c < C; c++) {
					map[r][c] = line.charAt(c);
					if (map[r][c] == '*') {
						Miro.add(new Fire(r, c, 0, true));
					} else if (map[r][c] == '@') {
						player = new Fire(r, c, 0, false);
					}
				}
			}

			BFS(player);
			if (ans == -1) {
				System.out.println("IMPOSSIBLE");
			} else {
				System.out.println(ans);
			}
		}
	}

	private static void BFS(Fire player) {
		Miro.add(player);
		visited[player.r][player.c] = true;

		while (!Miro.isEmpty()) {
			Fire temp = Miro.poll();

			boolean fire = temp.fire;
			if (map[temp.r][temp.c] == '@') {
				if (temp.r == 0 || temp.r == R - 1 || temp.c == 0 || temp.c == C - 1) {
					ans = temp.d + 1;
					return;
				}
			}

			for (int d = 0; d < dirs.length; d++) {
				int dr = temp.r + dirs[d][0];
				int dc = temp.c + dirs[d][1];

				if (isOK(dr, dc)) {
					if (fire) {
						if (map[dr][dc] == '.' || map[dr][dc] == '@') {
							map[dr][dc] = '*';
							Miro.add(new Fire(dr, dc, temp.d + 1, true));
							visited[dr][dc] = true;
						}

					} else {
						if (map[dr][dc] == '.') {
							map[dr][dc] = '@';
							Miro.add(new Fire(dr, dc, temp.d + 1, false));
							visited[dr][dc] = true;
						}
					}
				}
			}
		}
	}

	private static boolean isOK(int dr, int dc) {
		return dr >= 0 && dr < R && dc >= 0 && dc < C && !visited[dr][dc];
	}

	static class Fire {
		int r;
		int c;
		int d;
		boolean fire;

		public Fire(int r, int c, int d, boolean fire) {
			this.r = r;
			this.c = c;
			this.d = d;
			this.fire = fire;
		}

	}

}
