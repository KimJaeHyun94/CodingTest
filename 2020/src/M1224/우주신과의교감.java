package M1224;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class 우주신과의교감 {
	static int[] parents;
	static int[] rank;
	static List<Edge> list;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int V = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());
		list = new ArrayList<>();
		parents = new int[V+1];
		rank = new int[V+1];

		for (int i = 1; i <= V; i++) {
			makeSet(i);
		}
		double point[][] = new double[V+1
		                              ][2];

		for (int i = 1; i <= V; i++) {
			st = new StringTokenizer(br.readLine());
			point[i][0] = Double.parseDouble(st.nextToken());
			point[i][1] = Double.parseDouble(st.nextToken());
		}

		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			union(a, b);
		}
		for (int i = 1; i <= V; i++) {
			for (int j = i + 1; j <= V; j++) {
				double weight = dist(point[i], point[j]);

				list.add(new Edge(i, j, weight));
			}
		}
		Collections.sort(list);
		double result=0;
		int cnt = 0;
		for (int i = 0; i < list.size(); i++) {
			Edge edge = list.get(i);
			int a = findSet(edge.a);
			int b = findSet(edge.b);
			if(a==b)
				continue;
			union(a,b);
			
			result+=edge.w;
			cnt++;
			if(cnt==V-1)
				break;
		}
		System.out.println(String.format("%.2f", result));
	}

	private static double dist(double[] a, double[] b) {

		return Math.sqrt(Math.pow(a[0] - b[0], 2) + Math.pow(a[1] - b[1], 2));
	}

	static void makeSet(int x) {
		parents[x] = x;
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

	static int findSet(int x) {
		if (x == parents[x]) {
			return x;
		} else {
			parents[x] = findSet(parents[x]);
			return parents[x];
		}
	}

	static class Edge implements Comparable<Edge> {
		int a, b;
		double w;

		public Edge(int a, int b, double w) {
			this.a = a;
			this.b = b;
			this.w = w;
		}

		@Override
		public String toString() {
			return "Edge [a=" + a + ", b=" + b + ", w=" + w + "]";
		}

		@Override
		public int compareTo(Edge o) {
			return Double.compare(this.w, o.w);
		}
	}

}
