package M0416;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 미로탈출 {
	static int N, M;
	static int miro[][];
	static int sr, sc, er, ec;
	static int dirs[][] = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		miro = new int[N][M];
		st = new StringTokenizer(br.readLine());
		sr = Integer.parseInt(st.nextToken()) - 1;
		sc = Integer.parseInt(st.nextToken()) - 1;
		st = new StringTokenizer(br.readLine());
		er = Integer.parseInt(st.nextToken()) - 1;
		ec = Integer.parseInt(st.nextToken()) - 1;

		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < M; c++) {
				miro[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		BFS();
	}

	private static void BFS() {
		Queue<Explore> q = new LinkedList<>();
		q.add(new Explore(sr, sc, 0, 0));
		boolean visited[][][] = new boolean[N][M][2];
		visited[sr][sc][0] = true;

		while (!q.isEmpty()) {
			Explore cur = q.poll();

			if (cur.r == er && cur.c == ec) {
				System.out.println(cur.d);
				System.exit(0);
			}
			for (int d = 0; d < dirs.length; d++) {
				int dr = cur.r + dirs[d][0];
				int dc = cur.c + dirs[d][1];

				if (isOK(dr, dc)) {
					if (miro[dr][dc] == 1) {
						if (cur.broke == 0) {
							if (!visited[dr][dc][1])
								visited[dr][dc][1] = true;
							q.add(new Explore(dr, dc, cur.d + 1, 1));
						}
					} else {
						if (!visited[dr][dc][cur.broke]) {
							visited[dr][dc][cur.broke] = true;
							q.add(new Explore(dr, dc, cur.d + 1, cur.broke));
						}
					}
				}
			}
		}
		System.out.println(-1);
	}

	private static boolean isOK(int dr, int dc) {
		return dr >= 0 && dr < N && dc >= 0 && dc < M;
	}

	static class Explore {
		int r;
		int c;
		int d;
		int broke;

		public Explore(int r, int c, int d, int broke) {
			this.r = r;
			this.c = c;
			this.d = d;
			this.broke = broke;
		}

	}
}
