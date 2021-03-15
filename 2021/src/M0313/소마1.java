package M0313;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 소마1 {
	static int M, N;
	static int map[][];
	static int sr, sc, er, ec, jr, jc;
	static int dirs[][] = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
	static boolean flag;

	public static void main(String args[]) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		for (int tc = 0; tc < T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			M = Integer.parseInt(st.nextToken());
			N = Integer.parseInt(st.nextToken());

			map = new int[M][N];
			sr = 0;
			sc = 0;
			er = 0;
			ec = 0;
			jr = 0;
			jc = 0;

			flag = false;
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if (map[i][j] == 3) {
						sr = i;
						sc = j;
					} else if (map[i][j] == 2) {
						er = i;
						ec = j;
					}
				}
			}

			if (BFS(sr,sc) && BFS(jr,jc)) {
				System.out.println(1);
			} else
				System.out.println(0);
		}

	}

	private static boolean BFS(int rr, int cc) {
		Queue<Node> q = new LinkedList<>();
		q.add(new Node(rr, cc));
		boolean visited[][] = new boolean[M][N];
		while (!q.isEmpty()) {
			Node cur = q.poll();
			if (cur.r == er && cur.c == ec) {
				return true;
			}

			for (int d = 0; d < dirs.length; d++) {
				int dr = cur.r + dirs[d][0];
				int dc = cur.c + dirs[d][1];

				if (isOK(dr, dc) && !visited[dr][dc] && map[dr][dc] != 1) {
					visited[dr][dc] = true;
					q.add(new Node(dr, dc));
				}
			}
		}
		return false;
	}

	private static boolean isOK(int dr, int dc) {
		return dr >= 0 && dr < M && dc >= 0 && dc < N;
	}

	static class Node {
		int r;
		int c;

		public Node(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
}
