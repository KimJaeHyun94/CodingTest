import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ1261_알고스팟 {
	private static int dir[][] = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
	private static int arr[][];
	private static boolean visited[][];
	private static int N;
	private static int M;
	private static int dist[][];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		arr = new int[N][M];

		for (int i = 0; i < N; i++) {
			String input = br.readLine();
			for (int j = 0; j < M; j++) {
				arr[i][j] = input.charAt(j) - '0';
			}
			
		}

		visited = new boolean[N][M];
		dist = new int[N][M];
		BFS();
		System.out.println(dist[N - 1][M - 1]);

	}

	private static void BFS() {
		Deque<Node> queue = new LinkedList<>();
		queue.addLast(new Node(0, 0));
		visited[0][0] = true;

		while (!queue.isEmpty()) {
			Node n = queue.pollLast();
			int x = n.x;
			int y = n.y;

			for (int i = 0; i < dir.length; i++) {
				int dx = x + dir[i][0];
				int dy = y + dir[i][1];

				if (isOk(dx, dy)) {
					if (arr[dx][dy] == 0) {
						dist[dx][dy] = dist[x][y];
						queue.addLast(new Node(dx, dy));
					} else {
						dist[dx][dy] = dist[x][y] + 1;
						queue.addFirst(new Node(dx, dy));
					}
					visited[dx][dy] = true;
				}
			}

		}
	}

	private static boolean isOk(int dx, int dy) {
		if (dx >= 0 && dx < N && dy >= 0 && dy < M) {
			if (!visited[dx][dy])
				return true;
		}
		return false;
	}

	static class Node {
		int x;
		int y;

		Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}
