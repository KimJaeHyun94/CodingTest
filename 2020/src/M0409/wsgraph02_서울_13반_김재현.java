package M0409;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class wsgraph02_서울_13반_김재현 {
	static PriorityQueue<Edge> pq = new PriorityQueue<>();
	static int V;
	static List<Edge> path;
	static List<Edge>[] graph;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());
		graph = new List[V];
		for (int i = 0; i < graph.length; i++) {
			graph[i] = new ArrayList<>();
		}
		path = new ArrayList<>();
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken()) - 1;
			int b = Integer.parseInt(st.nextToken()) - 1;
			int c = Integer.parseInt(st.nextToken());
			graph[a].add(new Edge(b, c));
			graph[b].add(new Edge(a, c));
		}
		System.out.println((int)prim());

	}

	private static double prim() {
		boolean[] visited = new boolean[V];
		double result = 0;
		pq.add(new Edge(0,0));
		while(!pq.isEmpty()) {
			Edge temp = pq.poll();
			
			int v = temp.v;
			if(visited[v]) continue;
			
			visited[v] = true;
			result+=temp.cost;
			for (int i = 0; i < graph[v].size(); i++) {
				if(!visited[graph[v].get(i).v])
				{
					pq.add(graph[v].get(i));
				}
			}
		}
		return result;
	}

	static class Edge implements Comparable<Edge> {
		int v;
		double cost;

		public Edge(int v, double cost) {
			super();
			this.v = v;
			this.cost = cost;
		}

		@Override
		public int compareTo(Edge o) {
			return Double.compare(cost, o.cost);
		}
	}
}