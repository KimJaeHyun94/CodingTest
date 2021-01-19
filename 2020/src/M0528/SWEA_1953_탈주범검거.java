package M0528;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWEA_1953_탈주범검거 {
	static int N, M, R, C, L;
	static int map[][];
	static int ans;
	static boolean visited[][];
	static int[][] dirs = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			sb.append("#" + tc + " ");
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			L = Integer.parseInt(st.nextToken());

			map = new int[N][M];
			visited = new boolean[N][M];

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < M; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			ans = 1;
			bfs();
			sb.append(ans + "\n");
		}
		System.out.print(sb);
	}

	private static void bfs() {
		Queue<pos> queue = new LinkedList<>();
		queue.add(new pos(R, C, L - 1));
		visited[R][C] = true;

		while (!queue.isEmpty()) {
			pos temp = queue.poll();
			int r = temp.r;
			int c = temp.c;
			int time = temp.time;
			int flag = map[r][c];

			if (flag == 1) {
				for (int d = 0; d < 4; d++) {
					int nr = r + dirs[d][0];
					int nc = c + dirs[d][1];

					if (isOK(nr, nc)) {
						if (d == 0 && (map[nr][nc] == 2 || map[nr][nc] == 4 || map[nr][nc] == 5))
							continue;
						else if (d == 1 && (map[nr][nc] == 3 || map[nr][nc] == 5 || map[nr][nc] == 6))
							continue;
						else if (d == 2 && (map[nr][nc] == 2 || map[nr][nc] == 6 || map[nr][nc] == 7))
							continue;
						else if (d == 3 && (map[nr][nc] == 3 || map[nr][nc] == 4 || map[nr][nc] == 7))
							continue;

						if (!visited[nr][nc] && time > 0 && map[nr][nc] != 0) {
							visited[nr][nc] = true;
							queue.offer(new pos(nr, nc, time - 1));
							ans++;
						}
					}
				}
			} else if (flag == 2) {
				for (int d = 1; d < 4; d += 2) {
					int nr = r + dirs[d][0];
					int nc = c + dirs[d][1];

					if (isOK(nr, nc)) {

						if (d == 1 && (map[nr][nc] == 3 || map[nr][nc] == 5 || map[nr][nc] == 6))
							continue;
						else if (d == 3 && (map[nr][nc] == 3 || map[nr][nc] == 4 || map[nr][nc] == 7))
							continue;

						if (!visited[nr][nc] && time > 0 && map[nr][nc] != 0) {
							visited[nr][nc] = true;
							queue.offer(new pos(nr, nc, time - 1));
							ans++;
						}
					}
				}
			} else if (flag == 3) {
				for (int d = 0; d < 4; d += 2) {
					int nr = r + dirs[d][0];
					int nc = c + dirs[d][1];

					if (isOK(nr, nc)) {

						if (d == 0 && (map[nr][nc] == 2 || map[nr][nc] == 4 || map[nr][nc] == 5))
							continue;
						else if (d == 2 && (map[nr][nc] == 2 || map[nr][nc] == 6 || map[nr][nc] == 7))
							continue;

						if (!visited[nr][nc] && time > 0 && map[nr][nc] != 0) {
							visited[nr][nc] = true;
							queue.offer(new pos(nr, nc, time - 1));
							ans++;
						}
					}
				}
			} else if (flag == 4) {
				for (int d = 0; d < 4; d += 3) {
					int nr = r + dirs[d][0];
					int nc = c + dirs[d][1];

					if (isOK(nr, nc)) {
						if (d == 0 && (map[nr][nc] == 2 || map[nr][nc] == 4 || map[nr][nc] == 5))
							continue;
						else if (d == 3 && (map[nr][nc] == 3 || map[nr][nc] == 4 || map[nr][nc] == 7))
							continue;

						if (!visited[nr][nc] && time > 0 && map[nr][nc] != 0) {
							visited[nr][nc] = true;
							queue.offer(new pos(nr, nc, time - 1));
							ans++;
						}
					}
				}
			} else if (flag == 5) {
				for (int d = 0; d < 2; d++) {
					int nr = r + dirs[d][0];
					int nc = c + dirs[d][1];

					if (isOK(nr, nc)) {
						if (d == 0 && (map[nr][nc] == 2 || map[nr][nc] == 4 || map[nr][nc] == 5))
							continue;
						else if (d == 1 && (map[nr][nc] == 3 || map[nr][nc] == 5 || map[nr][nc] == 6))
							continue;
						if (!visited[nr][nc] && time > 0 && map[nr][nc] != 0) {
							visited[nr][nc] = true;
							queue.offer(new pos(nr, nc, time - 1));
							ans++;
						}
					}
				}
			} else if (flag == 6) {
				for (int d = 1; d < 3; d++) {
					int nr = r + dirs[d][0];
					int nc = c + dirs[d][1];

					if (isOK(nr, nc)) {
						if (d == 1 && (map[nr][nc] == 3 || map[nr][nc] == 5 || map[nr][nc] == 6))
							continue;
						else if (d == 2 && (map[nr][nc] == 2 || map[nr][nc] == 6 || map[nr][nc] == 7))
							continue;

						if (!visited[nr][nc] && time > 0 && map[nr][nc] != 0) {
							visited[nr][nc] = true;
							queue.offer(new pos(nr, nc, time - 1));
							ans++;
						}
					}
				}
			} else if (flag == 7) {
				for (int d = 2; d < 4; d++) {
					int nr = r + dirs[d][0];
					int nc = c + dirs[d][1];

					if (isOK(nr, nc)) {

						if (d == 2 && (map[nr][nc] == 2 || map[nr][nc] == 6 || map[nr][nc] == 7))
							continue;
						else if (d == 3 && (map[nr][nc] == 3 || map[nr][nc] == 4 || map[nr][nc] == 7))
							continue;

						if (!visited[nr][nc] && time > 0 && map[nr][nc] != 0) {
							visited[nr][nc] = true;
							queue.offer(new pos(nr, nc, time - 1));
							ans++;
						}
					}
				}
			}
		}
	}

	private static boolean isOK(int nr, int nc) {
		return nr >= 0 && nr < N && nc >= 0 && nc < M;
	}

	static class pos {
		int r;
		int c;
		int time;

		public pos(int r, int c, int time) {
			this.r = r;
			this.c = c;
			this.time = time;
		}

	}

}
