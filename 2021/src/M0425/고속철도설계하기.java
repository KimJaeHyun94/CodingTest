package M0425;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 고속철도설계하기 {
	static int N;
	static PriorityQueue<Node> pq;
	static int parents[];

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		pq = new PriorityQueue();
		parents = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			parents[i] = i;
		}
		int cost = 0;
		for (int i = 1; i <= N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				int n = Integer.parseInt(st.nextToken());

				if (i == j)
					continue;
				if (i > j)
					continue;
				if (n < 0) {
					union(i, j);
					cost += Math.abs(n);
				} else if (n > 0) {
					pq.add(new Node(i, j, n));
				}
			}
		}

		ArrayList<Dot> list = new ArrayList<>();
		int cnt = 0;

		while (!pq.isEmpty()) {
			Node cur = pq.poll();
			int s = findSet(cur.s);
			int e = findSet(cur.e);
			if (s == e)
				continue;
			union(s, e);
			cnt++;
			cost += cur.c;
			list.add(new Dot(cur.s, cur.e));
			if (cnt == N - 1)
				break;
		}
		System.out.println(cost + " " + cnt);
		for (Dot dot : list) {
			System.out.println(dot.x + " " + dot.y);
		}
	}

	private static void union(int s, int e) {
		int ps = findSet(s);
		int pe = findSet(e);
		if (ps != pe) {
			if (ps > pe) {
				parents[ps] = pe;
			} else
				parents[pe] = ps;
		}

	}

	private static int findSet(int s) {
		if (s == parents[s])
			return s;
		else
			return findSet(parents[s]);
	}

	static class Dot {
		int x;
		int y;

		public Dot(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	static class Node implements Comparable<Node> {
		int s;
		int e;
		int c;

		public Node(int s, int e, int c) {
			super();
			this.s = s;
			this.e = e;
			this.c = c;
		}

		@Override
		public int compareTo(Node o) {
			return this.c - o.c;
		}

	}
}
