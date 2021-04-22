package M0417;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class 불켜기 {
	static int N, M;
	static int map[][];
	static int dirs[][] = { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 } };
	static List<Node> graph[][];
	static int cnt = 1;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		graph = new List[N][N];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				graph[i][j] = new ArrayList<>();
			}

		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken()) - 1;
			int c = Integer.parseInt(st.nextToken()) - 1;
			int r1 = Integer.parseInt(st.nextToken()) - 1;
			int c1 = Integer.parseInt(st.nextToken()) - 1;

			graph[r][c].add(new Node(r1, c1));

		}

		BFS();
		System.out.println(cnt);
	}

	private static void BFS() {
		boolean visited[][] = new boolean[N][N];
		boolean light[][] = new boolean[N][N];
		boolean move[][] = new boolean[N][N];

		Queue<Node> q = new LinkedList<>();
		q.add(new Node(0, 0));
		visited[0][0] = true;
		light[0][0] = true;

		while (!q.isEmpty()) {
			Node cur = q.poll();

			for (int d = 0; d < dirs.length; d++) { // 이동 가능한 곳 알아보기
				int dr = cur.r + dirs[d][0];
				int dc = cur.c + dirs[d][1];

				if (isOK(dr, dc) && !visited[dr][dc]) {
					move[dr][dc] = true;
				}

			}
			if (!graph[cur.r][cur.c].isEmpty()) { // 현재 위치에서 불 켤 수 있는곳 켜기
				for (Node child : graph[cur.r][cur.c]) {
					if (!light[child.r][child.c]) {
						light[child.r][child.c] = true;
						cnt++;

						if (move[child.r][child.c] && !visited[child.r][child.c]) { // 불을 켤수있는데 움직일 수 있다면 움직이기 ->
																					// (2,1)좌표
							visited[child.r][child.c] = true;
							q.add(new Node(child.r, child.c));
						}
					}
				}
			}

			for (int d = 0; d < dirs.length; d++) { // 이동할 수 있는 불켜진곳 이동하기
				int dr = cur.r + dirs[d][0];
				int dc = cur.c + dirs[d][1];

				if (isOK(dr, dc) && !visited[dr][dc] && light[dr][dc] && move[dr][dc]) {
					visited[dr][dc] = true;
					q.add(new Node(dr, dc));
				}

			}
		}
	}

	private static boolean isOK(int dr, int dc) {
		return dr >= 0 && dr < N && dc >= 0 && dc < N;
	}

	static class Node {
		int r;
		int c;

		public Node(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}

	}
}
