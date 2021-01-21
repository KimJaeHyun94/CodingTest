package M0121;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 최단경로 {
	static int V, E;
	static private int INF = 987654321;
	static private List<Edge>[] list;
	static int D[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken()); // 정점의 개수
		E = Integer.parseInt(st.nextToken()); // 간성의 개수
		list = new ArrayList[V + 1];
		for (int i = 1; i <= V; i++) {
			list[i] = new ArrayList<>();
		}
		int start = Integer.parseInt(br.readLine());
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			list[u].add(new Edge(v, w));
		}
		Dijkstra(start);
		for (int i = 1; i < D.length; i++) {
			if (D[i] == INF) {
				System.out.println("INF");
			} else {
				System.out.println(D[i]);
			}
		}
	}

	private static void Dijkstra(int start) {
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		boolean visited[] = new boolean[V + 1];
		D = new int[V + 1];
		Arrays.fill(D, INF);
		D[start] = 0;
		
		pq.add(new Edge(start, 0));
		while (!pq.isEmpty()) {
			Edge edge = pq.poll();

			if (D[edge.v] < edge.weight) {
				continue;
			}
			for (Edge next : list[edge.v]) {
				// visited도지 않았으면서, D[next.v]가 D[edge.v] + next.weight 보다 더 크다면 갱신
				if (!visited[next.v] && D[next.v] > D[edge.v] + next.weight) {
					D[next.v] = D[edge.v] + next.weight;
					// decrease key
					pq.add(new Edge(next.v, D[next.v]));
				}
			}
			visited[edge.v] = true;
		}
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
