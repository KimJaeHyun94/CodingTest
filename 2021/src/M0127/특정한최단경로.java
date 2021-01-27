package M0127;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 특정한최단경로 {
	static private int INF = 987654321;
	static int V, E;
	static int D[];
	static List<Edge>[] list;
	static boolean[] visited;
	static int ans;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken()); // 정점의 개수
		E = Integer.parseInt(st.nextToken()); // 간성의 개수

		list = new ArrayList[V + 1];
		D = new int[V + 1];
		visited = new boolean[V + 1];
		Arrays.fill(D, INF);

		for (int i = 1; i <= V; i++) {
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

		// 1->v1->v2->V //v1, v2순서대로 거쳐서 가는 경우  ->r1
		int r1=0;
		r1 += dijkstra(1, v1);
		r1 += dijkstra(v1, v2);
		r1+=  dijkstra(v2, V);
	
		 
		// 1->v2->v1->V //v2, v1순서대로 거쳐서 가는 경우  -> r2
		int r2=0;
		r2 += dijkstra(1, v2);
		r2 += dijkstra(v2, v1);
		r2+=  dijkstra(v1, V);
		
		if(r1>=INF && r2>=INF) ans = -1;
		else ans = Math.min(r1, r2);
		
		System.out.println(ans);
	}

	public static int dijkstra(int start, int end) {
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		visited = new boolean[V + 1];

		D = new int[V + 1];
		Arrays.fill(D, INF);
		D[start] = 0; // 처음 시작을 0으로 시작
		pq.add(new Edge(start, 0)); // 시작점, 무게는 0

		while (!pq.isEmpty()) {
			Edge edge = pq.poll();

			if (!visited[edge.v]) {
				visited[edge.v] = true;

				for (Edge next : list[edge.v]) { // 그 정점에 모든 다른 정점에서
					if (!visited[next.v] && D[next.v] > D[edge.v] + next.weight) {
						D[next.v] = D[edge.v] + next.weight;
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
	}
}
