package M0409;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class wsgraph01_서울_13반_김재현 {
	static StringBuilder sb = new StringBuilder();
	static int T, N;
	static double E;
	static int[] parents;
	static int[] rank;
	static long[][] islands;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			sb.append("#").append(tc).append(" ");
			N = Integer.parseInt(br.readLine());
			islands = new long[N][2];
			parents = new int[N];
			rank = new int[N];

			StringTokenizer xLine = new StringTokenizer(br.readLine());
			StringTokenizer yLine = new StringTokenizer(br.readLine());

			for (int i = 0; i < N; i++) {
				islands[i][0] = Integer.parseInt(xLine.nextToken());
				islands[i][1] = Integer.parseInt(yLine.nextToken());
				parents[i] = i;
				rank[i] = 0;
			}
			E = Double.parseDouble(br.readLine());
			sb.append(Math.round(kruskal())).append("\n");
		}
		System.out.println(sb);
	}

	private static double kruskal() {
		double result = 0;
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (i == j)
					continue;
				long p = islands[i][0] - islands[j][0];
				long q = islands[i][1] - islands[j][1];
				double c = E * (p * p + q * q);
				pq.add(new Edge(i, j, c));
			}
		}
		int cnt = 0;
		while (!pq.isEmpty()) {
			Edge front = pq.poll();
			int i = findSet(front.i);
			int j = findSet(front.j);
			if (i == j)
				continue;
			result += front.cost;
			cnt++;
			if (cnt == N - 1)
				break;
			union(i, j);
		}
		return result;
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
