package M0329;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 공주님을구해라 {
	static int N, M, T;
	static int map[][];
	static int dirs[][] = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());

		map = new int[N][M];

		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < M; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		BFS();
		System.out.println("Fail");

	}

	private static void BFS() {
		Queue<Princess> q = new LinkedList<>();
		boolean visited[][][] = new boolean[N][M][2];
		q.add(new Princess(0, 0, false, 0));
		visited[0][0][0] = true;

		while (!q.isEmpty()) {
			Princess cur = q.poll();
			if (cur.t > T)
				continue;

			for (int d = 0; d < dirs.length; d++) {
				int dr = cur.r + dirs[d][0];
				int dc = cur.c + dirs[d][1];
				if (dr == N - 1 && dc == M - 1) {
					System.out.println(cur.t + 1);
					System.exit(0);
					return;
				}
				if (isOK(dr, dc)) {
					if (!cur.gram) {
						if (map[dr][dc] == 0 && !visited[dr][dc][0]) {
							visited[dr][dc][0] = true;
							q.add(new Princess(dr, dc, false, cur.t + 1));
						} else if (map[dr][dc] == 2 && !visited[dr][dc][0]) {
							visited[dr][dc][0] = true;
							q.add(new Princess(dr, dc, true, cur.t + 1));
						}

					} else {
						if (!visited[dr][dc][1]) {
							visited[dr][dc][1] = true;
							q.add(new Princess(dr, dc, true, cur.t + 1));
						}
					}
				}
			}
		}
	}

	private static boolean isOK(int dr, int dc) {
		return dr >= 0 && dr < N && dc >= 0 && dc < M;
	}

	static class Princess {
		int r;
		int c;
		boolean gram;
		int t;

		public Princess(int r, int c, boolean gram, int t) {
			this.r = r;
			this.c = c;
			this.gram = gram;
			this.t = t;
		}

	}
}
