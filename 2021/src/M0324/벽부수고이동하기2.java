package M0324;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 벽부수고이동하기2 {
	static int N, M, K;
	static int map[][];
	static int[][] dirs = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		for (int i = 0; i < N; i++) {
			String line = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = line.charAt(j) - '0';
			}
		}

		System.out.println(BFS());
	}

	private static int BFS() {
		Queue<Block> q = new LinkedList<>();
		q.add(new Block(0, 0, 0, 1));
		boolean visited[][][] = new boolean[N][M][K + 1];
		visited[0][0][0] = true;

		while (!q.isEmpty()) {
			Block cur = q.poll();
			int r = cur.r;
			int c = cur.c;
			
			if (r == N - 1 && c == M - 1) {
				return cur.d;
			}

			for (int d = 0; d < dirs.length; d++) {
				int dr = r + dirs[d][0];
				int dc = c + dirs[d][1];

				if (isOK(dr, dc)) {
					if (map[dr][dc] == 1) {
						if (cur.k < K && !visited[dr][dc][cur.k + 1]) {
							visited[dr][dc][cur.k + 1] = true;
							q.add(new Block(dr, dc, cur.k+1, cur.d + 1));
						}
					} else if (map[dr][dc] == 0) {
						if (cur.k <= K && !visited[dr][dc][cur.k]) {
							visited[dr][dc][cur.k] = true;
							q.add(new Block(dr, dc, cur.k, cur.d+1));
						}
					}
				}
			}
		}
		return -1;
	}

	private static boolean isOK(int dr, int dc) {
		return dr >= 0 && dr < N && dc >= 0 && dc < M;
	}

	static class Block {
		int r;
		int c;
		int k;
		int d;

		public Block(int r, int c, int k, int d) {
			this.r = r;
			this.c = c;
			this.k = k;
			this.d = d;
		}
	}
}
