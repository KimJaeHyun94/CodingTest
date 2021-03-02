package M0302;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 도시분할 {
	static int N, M;
	static int parents[];
	static PriorityQueue<Node> pq = new PriorityQueue<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			pq.add(new Node(s, e, c));
		}

		parents = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			parents[i] = i;
		}
		int ans = 0, cnt = 0;
		int max = Integer.MIN_VALUE;
		while (!pq.isEmpty()) {
			Node temp = pq.poll();
			int s = findSet(temp.s);
			int e = findSet(temp.e);

			if (s == e)
				continue;
			union(s, e);
			ans += temp.c;
			max = Math.max(max, temp.c);
			cnt++;
			if (cnt == N - 1)
				break;

		}

		System.out.println(ans - max);   //가장 큰비용이 드는 간선 1개를 삭제
	}

	static void union(int i, int j) {
		int pi = findSet(i);
		int pj = findSet(j);

		if (pi != pj) {
			if (pi < pj) {
				parents[pj] = pi;
			} else {
				parents[pi] = pj;
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

	static class Node implements Comparable<Node> {
		int s; // 출발 지점
		int e; // 도착 지점
		int c; // 유지비

		public Node(int s, int e, int c) {
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
