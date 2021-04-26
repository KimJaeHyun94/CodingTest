package M0425;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class 도로 {
	static int N, M;
	static PriorityQueue<Node> pq;
	static int parents[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		pq = new PriorityQueue();
		parents = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			parents[i] = i;
		}

		int sum = 0;
		for (int i = 1; i <= N; i++) {
			String line = br.readLine();
			int n = -1;
			for (int j = 1; j <= N; j++) {
				char ch = line.charAt(j - 1);
				if (ch >= 'a' && ch <= 'z') {
					n = ch - 'a' + 1;
				} else if (ch >= 'A' && ch <= 'Z') {
					n = ch - 'A' + 27;
				} else
					n = 0;

				sum += n; // 랜선의 모든 값을 다 더해준다.

				if (i != j && n!=0)
					pq.add(new Node(i, j, n));

			}
		}
		int cnt = 0;
		int ans = 0;
		while (!pq.isEmpty()) {
			Node cur = pq.poll();
			int s = findSet(cur.s);
			int e = findSet(cur.e);
			if (s == e)
				continue;
			union(s, e);
			cnt++;
			ans += cur.c;
		}
		if (cnt == N - 1) {
			System.out.println(sum - ans);
		} else
			System.out.println(-1);
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
