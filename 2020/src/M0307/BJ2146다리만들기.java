package M0307;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ2146다리만들기 {
	static int N;
	static int map[][];
	static int dir[][] = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };
	static boolean visited[][];
	static int cnt = 1;
	static int min = Integer.MAX_VALUE;
	static int cnt2;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		visited = new boolean[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (!visited[i][j] && map[i][j] == 1) {
					DFS(i, j);
					cnt++;
				}
			}
		}

		for (int k = 1; k <= cnt; k++) {

			cnt2 = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (map[i][j] == k) {
						visited = new boolean[N][N];
						cnt2=BFS(i, j, k);
						min = Math.min(min, cnt2);
					}
				}
			}
		}
		System.out.println(min);
	}

	private static int BFS(int a, int b, int k) {
		Queue<Node> q = new LinkedList<>();
		q.add(new Node(a, b, 0));
		visited[a][b] = true;

		while (!q.isEmpty()) {
			Node temp = q.poll();
			int y = temp.y;
			int x = temp.x;
			int d = temp.d;
			for (int i = 0; i < dir.length; i++) {
				int dy = y + dir[i][0];
				int dx = x + dir[i][1];

				if (isOK(dy, dx)) {
					if (map[dy][dx] == 0 || map[dy][dx] == k) {
						q.add(new Node(dy, dx, d + 1));
						visited[dy][dx] = true;
						cnt2++;
					} else if (map[dy][dx] != 0 && map[dy][dx] != cnt)
						return d;
				}
			}
		}
		return cnt2;
	}

	private static void DFS(int y, int x) {
		visited[y][x] = true;
		map[y][x] = cnt;
		for (int i = 0; i < dir.length; i++) {
			int dy = y + dir[i][0];
			int dx = x + dir[i][1];

			if (isOK(dy, dx)) {
				{
					if (map[dy][dx] == 1)
						DFS(dy, dx);
				}
			}
		}
	}

	private static boolean isOK(int dy, int dx) {
		if (dy >= 0 && dy < N && dx >= 0 && dx < N) {
			if (!visited[dy][dx])
				return true;
		}
		return false;
	}

	static class Node {
		int y;
		int x;
		int d;

		public Node(int y, int x, int d) {
			super();
			this.y = y;
			this.x = x;
			this.d = d;
		}
	}
}
