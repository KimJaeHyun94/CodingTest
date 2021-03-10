package M0309;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 벽부수고이동하기3 {
	static int map[][];
	static int N, K, M;
	static int[][] dirs = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };

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
		boolean visited[][][] = new boolean[N][M][12]; // 좌표, 벽
		Queue<Node> q = new LinkedList<>();
		q.add(new Node(0, 0, 0, true, 1));
		visited[0][0][0] = true;

		while (!q.isEmpty()) {
			Node cur = q.poll();
			int r = cur.r;
			int c = cur.c;
			int k = cur.k;
			int dep = cur.d;
			boolean day = cur.day;

			if (r == N - 1 && c == M - 1) {
				return cur.d;
			}
			for (int d = 0; d < dirs.length; d++) {
				int dr = r + dirs[d][0];
				int dc = c + dirs[d][1];

				if (isOK(dr, dc) && !visited[dr][dc][k]) {
					if (k < K && map[dr][dc] == 1 && !visited[dr][dc][k + 1]) {   //벽일때
						if (day) {   //낮이면 부수고
							q.add(new Node(dr, dc, k + 1, !day, dep + 1));
							visited[dr][dc][k + 1] = true;
						} else {  //밤이면 가만히
							q.add(new Node(r, c, k, !day, dep + 1));
						}
					} else if (map[dr][dc] == 0) {   //부술 수 있다면 낮이건 밤이건 부수기
						q.add(new Node(dr, dc, k, !day, dep + 1));
						visited[dr][dc][k] = true;
					}
				}
			}
		}
		return -1;
	}

	private static boolean isOK(int dr, int dc) {
		return dr >= 0 && dr < N && dc >= 0 && dc < M;
	}

	static class Node {
		int r; // 좌표
		int c;
		int k; // 벽 부수기
		boolean day; // 낮, 밤
		int d; // 길이

		public Node(int r, int c, int k, boolean day, int d) {
			this.r = r;
			this.c = c;
			this.k = k;
			this.d = d;
			this.day = day;
		}
	}
}
