package M0409;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;


public class hwgraph01_서울_13반_김재현 {
	static int[] parents;
	static int[] rank;
	static PriorityQueue<Edge> pq = new PriorityQueue<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int V = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());
		parents = new int[V];
		rank = new int[V];

		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int a=Integer.parseInt(st.nextToken())-1;
			int b = Integer.parseInt(st.nextToken())-1;
			int c = Integer.parseInt(st.nextToken());
			pq.add(new Edge(a,b,c));
		}
		
		for (int i = 0; i < V; i++) {
			makeSet(i);
		}
		
		int result = 0;
		int cnt = 0;
		while (!pq.isEmpty()) {
			Edge front = pq.poll();
			int i = findSet(front.i);
			int j = findSet(front.j);
			if (i == j)
				continue;
			result += front.cost;
			cnt++;
			if (cnt == V - 1)
				break;
			union(i, j);
		}
		System.out.println(result);
	}

	static void makeSet(int x) {
		parents[x] = x;
	}

	static int findSet(int x) {
		if (x == parents[x]) {
			return x;
		} else {
			parents[x] = findSet(parents[x]);
			return parents[x];
		}
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

	static class Edge implements Comparable<Edge> {
		int i;
		int j;
		double cost;

		public Edge(int i, int j, double cost) {
			this.i = i;
			this.j = j;
			this.cost = cost;
		}

		@Override
		public int compareTo(Edge o) {
			return Double.compare(cost, o.cost);
		}
	}
}