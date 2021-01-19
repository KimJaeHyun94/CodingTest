package M0303;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ17391무한부스터 {
	static int N, M;
	static int map[][];
	static int dir[][] = { { 1, 0 }, { 0, 1 } };
	static boolean visited[][];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		visited = new boolean[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		BFS();
	}

	private static void BFS() {
		Queue<Node> queue = new LinkedList<>();
		queue.add(new Node(0, 0, map[0][0], 0));
		while (!queue.isEmpty()) {
			Node temp = queue.poll();
			int y = temp.y;
			int x = temp.x;
			int w = temp.w;
			int d = temp.d;
			if (y == N - 1 && x == M - 1) {
				System.out.println(d);
				System.exit(0);
			}
			for (int i = 0; i < dir.length; i++) {
				for (int j = 1; j <= w; j++) {
					int dy = y + dir[i][0] * j;
					int dx = x + dir[i][1] * j;

					if (isOK(dy, dx)) {
						visited[dy][dx] = true;
						queue.add(new Node(dy, dx, map[dy][dx], d + 1));
					}
				}
			}
		}
	}

	private static boolean isOK(int dy, int dx) {
		if (dy >= 0 && dy < N && dx >= 0 && dx < M) {
			if (!visited[dy][dx])
				return true;
		}
		return false;
	}

	static class Node {
		int y;
		int x;
		int w;
		int d;

		public Node(int y, int x, int w, int d) {
			super();
			this.y = y;
			this.x = x;
			this.w = w;
			this.d = d;
		}
	}
}
