package M0506;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 소가길을건너간이유7 {
	static int N, T;
	private static int arr[][];
	static int INF = 987654321;
	private static int dirs[][] = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
	static boolean visited[][][];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());

		arr = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		BFS();
	}

	private static void BFS() {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.add(new Node(0, 0, 0, 0));
		visited = new boolean[N][N][3];
		visited[0][0][0] = true;
		
		while (!pq.isEmpty()) {
			Node n = pq.poll();
			int x = n.x;
			int y = n.y;
			int dep = n.dep;

			if(x==N-1 && y==N-1) {
				System.out.println(n.cost);
				System.exit(0);
			}
			for (int i = 0; i < dirs.length; i++) {
				int dx = x + dirs[i][0];
				int dy = y + dirs[i][1];
				int ncost = n.cost;
				int ndep = dep+1;
				if (isOk(dx, dy) && !visited[dx][dy][ndep%3]) {
					if (ndep % 3 == 0) {
						ncost += arr[dx][dy];
					}
					visited[dx][dy][ndep%3] = true;
					pq.add(new Node(dx,dy,ndep,ncost+T));
				}
			}
		}
	}

	private static boolean isOk(int dx, int dy) {
		return dx >= 0 && dx < N && dy >= 0 && dy < N;
	}

	static class Node implements Comparable<Node> {
		int x;
		int y;
		int dep;
		int cost;

		Node(int x, int y, int dep, int cost) {
			this.x = x;
			this.y = y;
			this.dep = dep;
			this.cost = cost;
		}

		@Override
		public int compareTo(Node o) {
			return this.cost - o.cost;
		}
	}
}
