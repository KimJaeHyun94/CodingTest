package M0329;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Maaaaaaaaaze {

	static int[][] dirs = { { 1, 0, 0 }, { -1, 0, 0 }, { 0, 1, 0 }, { 0, -1, 0 }, { 0, 0, 1 }, { 0, 0, -1 } };
	static int[][][] map;
	static int[][][] copy;
	static int[][][] copy2;
	static int min = Integer.MAX_VALUE;
	static boolean visited[];
	static int[] order;
	static int[] turn;
	static int[][] start = { { 0, 0, 0 } };
	static int[][] end = { { 4, 4, 4 } };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		map = new int[5][5][5];
		copy = new int[5][5][5];
		copy2 = new int[5][5][5];
		visited = new boolean[5];
		order = new int[5];
		turn = new int[5];
		for (int h = 0; h < 5; ++h) {
			for (int r = 0; r < 5; ++r) {
				st = new StringTokenizer(br.readLine());
				for (int c = 0; c < 5; ++c) {
					map[h][r][c] = Integer.parseInt(st.nextToken());
				}
			}
		}
		DFS(0);
		if (min == Integer.MAX_VALUE) {
			System.out.println(-1);
		} else
			System.out.println(min);
	}

	private static void DFS(int idx) {
		if (idx == 5) {
			Copy();
			Rotate(0);
			return;
		}

		for (int i = 0; i < 5; i++) {
			if (!visited[i]) {
				visited[i] = true;
				order[idx] = i;
				DFS(idx + 1);
				visited[i] = false;
			}
		}
	}

	private static void Rotate(int depth) {
		if (depth == 5) {
			for (int i = 0; i < 5; i++) {
				RotateMap(i, turn[i]);
			}
			if (copy2[0][0][0] == 0 || copy2[4][4][4] == 0) {
				return;
			} else {
				min = Math.min(min, BFS());
			}
			return;
		}

		for (int i = 0; i < 4; i++) {
			turn[depth] = i;
			Rotate(depth + 1);
		}
	}

	private static int BFS() {
		Queue<Maze> q = new LinkedList<>();
		boolean visit[][][] = new boolean[5][5][5];

		q.add(new Maze(0, 0, 0, 0));
		visit[0][0][0] = true;

		while (!q.isEmpty()) {
			Maze cur = q.poll();
			int h = cur.h;
			int r = cur.r;
			int c = cur.c;

			if (cur.h == 4 && cur.r == 4 && cur.c == 4) {
				return cur.d;
			}

			for (int d = 0; d < dirs.length; d++) {
				int dh = h + dirs[d][0];
				int dr = r + dirs[d][1];
				int dc = c + dirs[d][2];

				if (isOK(dh, dr, dc) && !visit[dh][dr][dc] && copy2[dh][dr][dc] == 1) {
					q.add(new Maze(dh, dr, dc, cur.d + 1));
					visit[dh][dr][dc] = true;
				}
			}
		}
		return Integer.MAX_VALUE;
	}

	private static boolean isOK(int dh, int dr, int dc) {
		return dh >= 0 && dh < 5 && dc >= 0 && dc < 5 && dr >= 0 && dr < 5;
	}

	private static void RotateMap(int idx, int turn) {

		switch (turn) {
		case 0:
			for (int i = 0; i < 5; i++) {
				for (int j = 0; j < 5; j++) {
					copy2[idx][i][j] = copy[idx][i][j];
				}
			}
			return;
		case 1:
			for (int i = 0; i < 5; i++) {
				for (int j = 0; j < 5; j++) {
					copy2[idx][j][4 - i] = copy[idx][i][j];
				}
			}
			return;
		case 2:
			for (int i = 0; i < 5; i++) {
				for (int j = 0; j < 5; j++) {
					copy2[idx][4 - i][4 - j] = copy[idx][i][j];
				}
			}
			return;
		case 3:
			for (int i = 0; i < 5; i++) {
				for (int j = 0; j < 5; j++) {
					copy2[idx][4 - j][i] = copy[idx][i][j];
				}
			}
			return;
		}

	}

	private static void Copy() {
		for (int i = 0; i < 5; i++) {
			int idx = order[i];
			for (int x = 0; x < 5; x++) {
				for (int y = 0; y < 5; y++) {
					copy[i][x][y] = map[idx][x][y];
				}
			}
		}
	}

	static class Maze {
		int h;
		int r;
		int c;
		int d;

		public Maze(int h, int r, int c, int d) {
			this.h = h;
			this.r = r;
			this.c = c;
			this.d = d;
		}

	}
}
