package 다익스트라;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class 최단경로 {
	static List<Node>[] graph;
	static final int INF = 987654321;
	static int V, E;
	static StringBuilder sb;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());

		int start = Integer.parseInt(br.readLine());

		graph = new List[V + 1];
		for (int i = 1; i < graph.length; i++) {
			graph[i] = new ArrayList<>();
		}

		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());

			graph[a].add(new Node(b, w));
		}

		for (int i = 1; i < graph.length; i++) {
			Collections.sort(graph[i]);
		}

		dijkstra(start);
		System.out.println(sb);
	}

	private static void dijkstra(int start) {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		int[] dist = new int[V + 1];
		boolean[] visited = new boolean[V + 1];

		pq.add(new Node(start, 0));
		Arrays.fill(dist, INF);
		dist[start] = 0;

		while (!pq.isEmpty()) {
			int v = pq.poll().v;

			if (visited[v])
				continue;
			visited[v] = true;

			for (Node next : graph[v]) {
				int next_v = next.v;
				int next_w = next.w;

				if (dist[next_v] > dist[v] + next_w) {
					dist[next_v] = dist[v] + next_w;
					pq.add(new Node(next_v, dist[next_v]));
				}
			}
		}

		sb = new StringBuilder();
		for (int i = 1; i < dist.length; i++) {
			if (dist[i] != INF)
				sb.append(dist[i]).append("\n");
			else
				sb.append("INF").append("\n");
		}
	}

	static class Node implements Comparable<Node> {
		int v, w;

		public Node(int v, int w) {
			this.v = v;
			this.w = w;
		}

		@Override
		public String toString() {
			return "Node [v=" + v + ", w=" + w + "]";
		}

		@Override
		public int compareTo(Node o) {
			return Integer.compare(this.w, o.w);
		}
	}
}