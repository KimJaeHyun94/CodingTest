package 다익스트라;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 알고스팟 {
	private static int dir[][] = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
	private static int arr[][];
	private static int N;
	private static int M;
	private static int dist[][];
	static int INF = 987654321;

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

		BFS();
		System.out.println(0);
	}

	private static void BFS() {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.add(new Node(0, 0, 0));
		dist = new int[N][M];
		for (int i = 0; i < N; i++) {
			Arrays.fill(dist[i], INF);
		}
		dist[0][0] = 0;

		while (!pq.isEmpty()) {
			Node n = pq.poll();
			int x = n.x;
			int y = n.y;

			if (x == N-1 && y == M-1) {
				System.out.println(n.cost);
				System.exit(0);
			}

			for (int i = 0; i < dir.length; i++) {
				int dx = x + dir[i][0];
				int dy = y + dir[i][1];

				if (isOk(dx, dy)) {
					if (dist[dx][dy] > dist[x][y] + arr[dx][dy]) {
						dist[dx][dy] = dist[x][y] + arr[dx][dy];
						pq.add(new Node(dx, dy, dist[dx][dy]));
					}
				}
			}

		}
	}

	private static boolean isOk(int dx, int dy) {
		return dx >= 0 && dx < N && dy >= 0 && dy < M;
	}

	static class Node implements Comparable<Node> {
		int x;
		int y;
		int cost;

		Node(int x, int y, int cost) {
			this.x = x;
			this.y = y;
			this.cost = cost;
		}

		@Override
		public int compareTo(Node o) {
			return this.cost - o.cost;
		}
	}
}