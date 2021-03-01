package M0301;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class sol {
	static int N, M;
	static int map[][];
	static int dir[][] = { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 } };
	static boolean visited[][];
	static int cnt = 1;
	static int min = 0;
	static int cnt2 = 0;
	static int parents[];
	static int[] rank;
	static int result = 0;
	static PriorityQueue<Node> pq = new PriorityQueue<Node>();

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

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (!visited[i][j] && map[i][j] != 0) {
					DFS(i, j);
					cnt++;
				}
			}
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] != 0) {
					Sol(i, j, map[i][j]);
				}
			}
		}

		parents = new int[cnt];
		rank = new int[cnt];
		for (int i = 0; i < parents.length; i++) {
			parents[i] = i;
		}
		int size = pq.size();
		for (int i = 0; i < size; i++) {
			Node temp = pq.poll();

			int a = findSet(temp.s);
			int b = findSet(temp.e);

			if (a == b)
				continue;

			union(a, b);
			result += temp.v;
			cnt2++;

		}
		if (result == 0 || cnt2 != cnt - 2)
			System.out.println(-1);
		else
			System.out.println(result);
	}

	private static void DFS(int y, int x) {
		visited[y][x] = true;
		map[y][x] = cnt;
		for (int i = 0; i < dir.length; i++) {
			int dy = y + dir[i][0];
			int dx = x + dir[i][1];

			if (isOK(dy, dx) && !visited[dy][dx]) {
				{
					if (map[dy][dx] == 1)
						DFS(dy, dx);
				}
			}
		}
	}

	public static void Sol(int y, int x, int num) {
		int dy = y;
		int dx = x;
		int length = 0;

		for (int i = 0; i < 4; i++) {
			while (true) {
				dy = dy + dir[i][0];
				dx = dx + dir[i][1];

				if (isOK(dy,dx)) {
					if (map[dy][dx] == num) {
						length = 0;
						dy = y;
						dx = x;
						break;
					} else if (map[dy][dx] == 0) {
						length++;
					} else {
						// 1보다 크면 pq에 추가해준다.
						if (length > 1) {
							pq.add(new Node(num, map[dy][dx], length));
							System.out.println(dy+" "+dx+" "+length);
						}
						length = 0;
						dy = y;
						dx = x;
						break;
					}
				} else {
					length = 0;
					dy = y;
					dx = x;
					break;
				}
			}
		}
	}

	static void makeSet(int x) {
		parents[x] = x;
	}

	public static int findSet(int x) {
		if (x == parents[x]) {
			
			return x;
		}
		parents[x] = findSet(parents[x]);
		return parents[x];
	}


	static void union(int x, int y) {
		int px = findSet(x);
		int py = findSet(y);
		if (rank[px] > rank[py]) {
			parents[py] = px;
		} else {
			parents[px] = py;
			if (rank[px] == rank[py]) {
				rank[py]++;
			}
		}
	}

	private static boolean isOK(int dy, int dx) {
		return dy >= 0 && dy < N && dx >= 0 && dx < M;
	}

	static class Node implements Comparable<Node> {
		int s;
		int e;
		int v;

		public Node(int s, int e, int v) {
			this.s = s;
			this.e = e;
			this.v = v;
		}

		@Override
		public int compareTo(Node o) {
			return o.v >= this.v ? -1 : 1;
		}
	}
}