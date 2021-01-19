package M0303;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ14502연구소 {
	static int N, M;
	static int map[][];
	static int copy[][];
	static int copy2[][];
	static int dir[][] = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };
	static boolean visited[][];
	static int max = 0;
	static int cnt = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		copy = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] == 0) {
					
					visited = new boolean[N][M];
					copycopy(copy,map);
					copy[i][j] = 1;
					structwall(1);
					copy[i][j] = 0;
				}
			}
		}
		System.out.println(max);
	}

	static void structwall(int r) {
		if (r == 3) {
			copy2 = new int[N][M];
			copycopy(copy2,copy);
			cnt = 0;
			visited = new boolean[N][M];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (copy[i][j] == 2) {
						BFS(i, j);
					}
				}
			}
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if(copy2[i][j]==0)
						cnt++;
				}
			}
			max=Math.max(max, cnt);
			return;
		}
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (copy[i][j] == 0) {
					copy[i][j] = 1;
					structwall(r + 1);
					copy[i][j] = 0;
				}
			}
		}
	}

	private static void copycopy(int[][] news, int[][] origin) {
		for (int j2 = 0; j2 < N; j2++) {
			for (int k = 0; k < M; k++) {
				news [j2][k] = origin[j2][k];
			}
		}
	}

	private static void BFS(int a, int b) {
		Queue<Node> q = new LinkedList<>();
		q.add(new Node(a, b));

		visited[a][b] = true;

		while (!q.isEmpty()) {
			Node temp = q.poll();
			int y = temp.y;
			int x = temp.x;

			for (int i = 0; i < dir.length; i++) {
				int dy = y + dir[i][0];
				int dx = x + dir[i][1];

				if (isOK(dy, dx)) {
					visited[dy][dx] = true;
					copy2[dy][dx] = 2;
					q.add(new Node(dy, dx));
				}
			}
		}
	}

	private static boolean isOK(int dy, int dx) {
		if (dy >= 0 && dy < N && dx >= 0 && dx < M) {
			if (!visited[dy][dx] && copy2[dy][dx] == 0)
				return true;
		}
		return false;
	}

	static class Node {
		int y;
		int x;

		public Node(int y, int x) {
			super();
			this.y = y;
			this.x = x;
		}
	}
}
