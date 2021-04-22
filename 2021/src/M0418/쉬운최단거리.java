package M0418;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 쉬운최단거리 {
	static int N, M;
	static int origin[][];
	static int ans[][];
	static int sr, sc;
	static int dirs[][] = { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 } };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		origin = new int[N][M];
		ans = new int[N][M];

		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < M; c++) {
				int num = Integer.parseInt(st.nextToken());
				origin[r][c] = num;
				if (num == 2) {
					sr = r;
					sc = c;
					origin[r][c] = 2;
				}
			}
		}

		BFS();
		StringBuilder sb = new StringBuilder();
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				sb.append(ans[r][c] + " ");
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}

	private static void BFS() {
		Queue<Node> q = new LinkedList<>();
		q.add(new Node(sr, sc, 0));
		boolean visited[][] = new boolean[N][M];
		visited[sr][sc] = true;

		while (!q.isEmpty()) {
			Node cur = q.poll();

			for (int d = 0; d < dirs.length; d++) {
				int dr = cur.r + dirs[d][0];
				int dc = cur.c + dirs[d][1];

				if (isOK(dr, dc) && !visited[dr][dc] && origin[dr][dc] == 1) {
					ans[dr][dc] = cur.d + 1;
					visited[dr][dc] = true;
					q.add(new Node(dr, dc, cur.d + 1));
				}
			}

		}

		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				if (ans[r][c] == 0 && origin[r][c]==1) {
					ans[r][c] = -1;
				}
			}
		}
	}

	private static boolean isOK(int dr, int dc) {
		return dr >= 0 && dr < N && dc >= 0 && dc < M;
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
