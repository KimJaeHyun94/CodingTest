package M0424;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 별자리만들기 {
	static int N;
	static Star star[];
	static int parents[];

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		star = new Star[N + 1];
		parents = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			parents[i] = i;
		}
		for (int i = 1; i <= N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			double x = Double.parseDouble(st.nextToken());
			double y = Double.parseDouble(st.nextToken());

			star[i] = new Star(x, y);
		}
		PriorityQueue<Node> pq = new PriorityQueue();

		for (int i = 1; i < N; i++) {
			for (int j = i + 1; j <= N; j++) {
				double dist = Math.sqrt(Math.pow((star[i].x - star[j].x), 2) + Math.pow((star[i].y - star[j].y), 2));
				pq.add(new Node(i, j, dist));
			}
		}

		double cost = 0;
		while (!pq.isEmpty()) {
			Node cur = pq.poll();
			int s = findSet(cur.s);
			int e = findSet(cur.e);

			if (s == e)
				continue;
			union(s, e);
			cost += cur.d;

		}

		System.out.println(cost);
	}

	private static void union(int s, int e) {
		int ps = findSet(s);
		int pe = findSet(e);

		if (ps != pe) {
			if (ps > pe) {
				parents[ps] = pe;
			} else {
				parents[pe] = ps;
			}
		}

	}

	private static int findSet(int s) {
		if (s == parents[s])
			return s;
		else
			return findSet(parents[s]);
	}

	static class Node implements Comparable<Node> {
		int s;
		int e;
		double d;

		public Node(int s, int e, double d) {
			super();
			this.s = s;
			this.e = e;
			this.d = d;
		}

		@Override
		public int compareTo(Node o) {
			return Double.compare(this.d, o.d);
		}

	}

	static class Star {
		double x;
		double y;

		public Star(double x, double y) {
			this.x = x;
			this.y = y;
		}
	}

}
