package M0425;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Moocast {
	static int N;
	static PriorityQueue<Node> pq;
	static int parents[];

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		pq = new PriorityQueue();
		Dot arr[] = new Dot[N + 1];
		parents = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			parents[i] = i;
		}

		for (int i = 1; i <= N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			arr[i] = new Dot(x, y);
		}

		for (int i = 1; i <= N - 1; i++) {
			for (int j = i + 1; j <= N; j++) {
				pq.add(new Node(i, j, distance(arr[i], arr[j])));
			}
		}

		int cost = 0;
		int cnt = 0;
		while (!pq.isEmpty()) {
			Node cur = pq.poll();
			int s = findSet(cur.s);
			int e = findSet(cur.e);

			if (s == e)
				continue;
			union(s, e);
			cost = Math.max(cost, cur.c);
			cnt++;
			if(cnt==N-1)
				break;
		}

		System.out.println(cost);

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

	private static int distance(Dot dot, Dot dot2) {
		return (int) (Math.pow(dot.x - dot2.x, 2) + Math.pow(dot.y - dot2.y, 2));
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
