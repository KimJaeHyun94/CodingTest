package M0416;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 직사각형탈출 {
	static int H, W;
	static int map[][];
	static int h, w, sr, sc, er, ec;
	static int dirs[][] = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		H = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());

		map = new int[H + 1][W + 1];

		for (int i = 1; i <= H; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= W; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		st = new StringTokenizer(br.readLine());
		h = Integer.parseInt(st.nextToken());
		w = Integer.parseInt(st.nextToken());
		sr = Integer.parseInt(st.nextToken());
		sc = Integer.parseInt(st.nextToken());
		er = Integer.parseInt(st.nextToken());
		ec = Integer.parseInt(st.nextToken());

		System.out.println(BFS());
	}

	private static int BFS() {
		Queue<Node> q = new LinkedList<>();
		q.add(new Node(sr, sc, 0));
		boolean visited[][] = new boolean[H + 1][W + 1];
		visited[sr][sc] = true;

		while (!q.isEmpty()) {
			Node cur = q.poll();

			if (cur.r == er && cur.c == ec) {
				return cur.d;
			}

			for (int d = 0; d < 4; d++) {
				int dr = cur.r + dirs[d][0];
				int dc = cur.c + dirs[d][1];

				if (dr < 1 || dr > H || dc < 1 || dc > W || visited[dr][dc])
					continue;

				if (!checkZero(dr, dc, d))
					continue;
				visited[dr][dc] = true;
				q.add(new Node(dr, dc, cur.d + 1));
			}
		}
		return -1;
	}

	private static boolean checkZero(int dr, int dc, int d) {
		switch (d) {

		case 0:
			for (int i = 0; i < w; i++) {
				if (map[dr][dc + i] == 1) {
					return false;
				}
			}
		case 1:
			if (dr + h - 1 > H)
				return false;
			int nr = dr + h - 1;
			for (int i = 0; i < w; i++) {
				if (map[nr][dc + i] == 1) {
					return false;
				}
			}
		case 2:
			for (int i = 0; i < h; i++) {
				if (map[dr + i][dc] == 1) {
					return false;
				}
			}
		case 3:
			if (dc + w - 1 > W)
				return false;
			int nc = dc + w - 1;
			
			for (int i = 0; i < h; i++) {
				if (map[dr + i][nc] == 1) {
					return false;
				}
			}
		}
		return true;
	}

	static class Node {
		int r;
		int c;
		int d;

		public Node(int r, int c, int d) {
			this.r = r;
			this.c = c;
			this.d = d;
		}
	}
}
