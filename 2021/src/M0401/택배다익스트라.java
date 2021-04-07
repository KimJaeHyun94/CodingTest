package M0401;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 택배다익스트라 {
	static List<Edge>[] graph;
	static int path[];
	static int N, M;
	static int INF = 987654321;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		graph = new List[N + 1];
		path = new int[N + 1];

		for (int i = 1; i < graph.length; i++) {
			graph[i] = new ArrayList<>();
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());

			graph[a].add(new Edge(b, v));
			graph[b].add(new Edge(a, v));
		}

		for (int i = 1; i <= N; i++) {
			dijkstra(i);

			for (int j = 1; j <= N; j++) {
				if (i == j)
					System.out.print("- ");
				else {
					if (path[j] == i) {
						System.out.print(j + " ");
					} else {
						int k = j;
						while (true) {
							if (path[k] == i) {
								System.out.print(k + " ");
								break;
							} else
								k = path[k];
						}
					}
				}
			}
			System.out.println();
		}
	}

	private static void dijkstra(int start) {
		boolean[] visited = new boolean[N + 1];
		int[] D = new int[N + 1];
		Arrays.fill(D, INF);
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		pq.add(new Edge(start, 0));
		D[start] = 0;

		while (!pq.isEmpty()) {
			Edge cur = pq.poll();

			if (!visited[cur.v]) {
				visited[cur.v] = true;

				for (Edge next : graph[cur.v]) {
					if (!visited[next.v] && D[next.v] > D[cur.v] + next.w) {
						D[next.v] = D[cur.v] + next.w;
						path[next.v] = cur.v;
						pq.add(new Edge(next.v, D[next.v]));
					}
				}
			}
		}
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
