package M0402;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 파티 {
	static int N, M, X;
	static List<Edge>[] graph;
	static int INF = 987654321;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());
		graph = new List[N + 1];

		for (int i = 0; i <= N; i++) {
			graph[i] = new ArrayList<>();
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());

			graph[a].add(new Edge(b, v));
		}
		int max = Integer.MIN_VALUE;

		for (int i = 1; i <= N; i++) {
			if (i == X)
				continue;
			max = Math.max(dijkstra(i, X)+dijkstra(X,i), max);
		}
		System.out.println(max);
	}

	private static int dijkstra(int start, int end) {
		int D[] = new int[N + 1];
		Arrays.fill(D, INF);
		boolean visited[] = new boolean[N + 1];

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
						pq.add(new Edge(next.v, D[next.v]));
					}
				}
			}
		}

		return D[end];
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
