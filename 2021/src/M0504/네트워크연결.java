package M0504;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 네트워크연결 {
	static int N, M;
	static PriorityQueue<Node> pq;
	static int parents[];

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());

		parents = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			parents[i] = i;
		}

		pq = new PriorityQueue<>();

		for (int i = 0; i < M; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			pq.add(new Node(s, e, v));
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
			ans += cur.v;
			cnt++;
			if (cnt == N - 1)
				break;
			
		}
		System.out.println(ans);
	}

	private static void union(int s, int e) {
		int ps = findSet(s);
		int pe = findSet(e);

		if (ps != pe) {
			if (ps < pe) {
				parents[pe] = ps;
			} else
				parents[ps] = pe;
		}

	}

	private static int findSet(int s) {
		if (parents[s] == s) {
			return s;
		} else {
			parents[s] = findSet(parents[s]);
			return parents[s];
		}
	}

	static class Node implements Comparable<Node> {
		int s;
		int e;
		int v;

		public Node(int s, int e, int v) {
			this.s = s;
			this.e = e;
			this.v = v;
		}

		@Override
		public int compareTo(Node o) {
			return this.v - o.v;
		}

	}
}
