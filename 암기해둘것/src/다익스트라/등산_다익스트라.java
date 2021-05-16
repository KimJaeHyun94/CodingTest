package 다익스트라;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;


public class 등산_다익스트라 {
	static int INF = 987654321;
	static int N, M, T, D;
	static int dirs[][] = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
	static int map[][];
	static int MAX;
	static int dist[][], rdist[][];
	static List<Edge> graph[], rgraph[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		MAX = N * M;

		dist = new int[N][M];
		rdist = new int[N][M];

		graph = new List[MAX];
		rgraph = new List[MAX];

		for (int i = 0; i < MAX; i++) {
			graph[i] = new ArrayList<>();
			rgraph[i] = new ArrayList<>();
		}
		for (int i = 0; i < N; i++) {
			String line = br.readLine();
			for (int j = 0; j < M; j++) {
				char ch = line.charAt(j);
				if (ch <= 'Z' && ch >= 'A') {
					map[i][j] = ch - 'A';
				} else {
					map[i][j] = ch - 'a' + 26;
				}
			}
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				for (int d = 0; d < dirs.length; d++) {
					int di = i + dirs[d][0];
					int dj = j + dirs[d][1];

					if (!isOK(di, dj))
						continue;

					int here = i * M + j;
					int next = di * M + dj;

					int diff = map[i][j] - map[di][dj];

					if (Math.abs(diff) > T)
						continue;

					if (diff > 0) {
						graph[here].add(new Edge(next, diff * diff));
					} else {
						graph[here].add(new Edge(next, 1));
					}

					if (diff < 0) {
						rgraph[here].add(new Edge(next, diff * diff));
					} else
						rgraph[here].add(new Edge(next, 1));

				}
			}
		}

		dijkstra(graph, dist);
		dijkstra(rgraph, rdist);

		int ans = map[0][0];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (dist[i][j] + rdist[i][j] <= D) {
					ans = Math.max(ans, map[i][j]);
				}
			}
		}
		System.out.println(ans);
	}

	private static void dijkstra(List<Edge>[] graph, int[][] dist) {
		for (int i = 0; i < N; i++) {
			Arrays.fill(dist[i], INF);
		}

		dist[0][0] = 0;
		PriorityQueue<Edge> pq = new PriorityQueue();
		pq.add(new Edge(0, 0));

		while (!pq.isEmpty()) {
			Edge cur = pq.poll();
			
			int row = cur.v/M;
			int col = cur.v%M;
			
			for (Edge next : graph[cur.v]) {
				int nrow = next.v / M;
				int ncol = next.v % M;
				
				if (dist[nrow][ncol] > dist[row][col] + next.w) {
					dist[nrow][ncol] = dist[row][col] + next.w;
					pq.add(new Edge(next.v, dist[nrow][ncol]));
				}
			}

		}
	}

	private static boolean isOK(int di, int dj) {
		return di >= 0 && di < N && dj >= 0 && dj < M;
	}

	static class Edge implements Comparable<Edge> {
		int v, w;

		public Edge(int v, int w) {
			this.v = v;
			this.w = w;
		}

		@Override
		public int compareTo(Edge o) {
			return Integer.compare(this.w, o.w);
		}
	}
}
