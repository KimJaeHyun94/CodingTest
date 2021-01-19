package M1224;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 특정한최단경로 {

	static private int INF = 200000000;
	static int V, E;
	static int D[];
	static List<Edge>[] list;
	static boolean[] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken()); // 정점의 개수
		E = Integer.parseInt(st.nextToken()); // 간성의 개수

		list = new ArrayList[V + 1];
		D = new int[V + 1];
		visited = new boolean[V + 1];
		Arrays.fill(D, INF);

		for (int i = 0; i <= V; i++) {
			list[i] = new ArrayList<>();
		}
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());

			list[start].add(new Edge(end, w));
			list[end].add(new Edge(start, w));

		}

		st = new StringTokenizer(br.readLine());
		int v1 = Integer.parseInt(st.nextToken());
		int v2 = Integer.parseInt(st.nextToken());

		int res1 = 0;
		res1 += dijkstra(1, v1);
		res1 += dijkstra(v1, v2);
		res1 += dijkstra(v2, V);

		int res2 = 0;
		res2 += dijkstra(1, v2);
		res2 += dijkstra(v2, v1);
		res2 += dijkstra(v1, V);

		int ans = (res1 >= INF && res2 >= INF) ? -1 : Math.min(res1, res2);

		System.out.println(ans);

	}

	public static int dijkstra(int start, int end) {
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		visited = new boolean[V + 1];

		D = new int[V + 1];
		Arrays.fill(D, INF);
		D[start] = 0;
		pq.add(new Edge(start, 0));

		while (!pq.isEmpty()) {
			Edge edge = pq.poll();

			if (!visited[edge.v]) {
				visited[edge.v] = true;

				for (Edge next : list[edge.v]) {
					// visited도지 않았으면서, D[next.v]가 D[edge.v] + next.weight 보다 더 크다면 갱신
					if (!visited[next.v] && D[next.v] > D[edge.v] + next.weight) {
						D[next.v] = D[edge.v] + next.weight;
						// decrease key
						pq.add(new Edge(next.v, D[next.v]));
					}
				}
			}
		}
		return D[end];
	}

	static class Edge implements Comparable<Edge> {
		int v, weight;

		public Edge(int v, int weight) {
			super();
			this.v = v;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			// TODO Auto-generated method stub
			return Integer.compare(this.weight, o.weight);
		}

		@Override
		public String toString() {
			return "Edge [weight=" + weight + "]";
		}
	}

}
