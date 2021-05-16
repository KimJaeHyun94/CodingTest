package M0516;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 악덕영주혜유 {

	static int N, K;
	static int parents[];
	static PriorityQueue<Node> pq;
	static List<Point> graph[];
	static boolean visited[];
	static long max;
	static int maxnode;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		pq = new PriorityQueue<>();

		parents = new int[N + 1];
		graph = new List[N + 1];

		for (int i = 0; i <= N; i++) {
			parents[i] = i;
			graph[i] = new ArrayList<>();
		}

		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			int C = Integer.parseInt(st.nextToken());

			pq.add(new Node(A, B, C));
		}
		int sum = 0, cnt = 0;
		while (!pq.isEmpty()) {
			Node cur = pq.poll();
			int s = findSet(cur.s);
			int e = findSet(cur.e);

			if (s == e)
				continue;
			graph[cur.s].add(new Point(cur.e, cur.c));
			graph[cur.e].add(new Point(cur.s, cur.c));
			union(s, e);
			cnt++;
			sum += cur.c;
			if (cnt == N - 1)
				break;
		}

		System.out.println(sum);
		visited = new boolean[N];
		dfs(0, 0);
		visited = new boolean[N];
		dfs(maxnode, 0);
		System.out.println(max);
	}

	private static void dfs(int s, int c) {
		if (visited[s])
			return;

		visited[s] = true;
		if (c > max) { // 최대 걸리는 걸 찾는다.
			max = c;
			maxnode = s;
		}

		for (Point child : graph[s]) {
			int src = child.e;
			int cost = child.c;
			dfs(src, c+cost);

		}
	}

	private static void union(int s, int e) {
		int ps = findSet(s);
		int pe = findSet(e);

		if (ps != pe) {
			if (ps < pe) {
				parents[pe] = ps;
			} else {
				parents[ps] = pe;
			}
		}

	}

	private static int findSet(int s) {
		if (s == parents[s]) {
			return s;
		} else {
			parents[s] = findSet(parents[s]);
			return parents[s];
		}
	}

	static class Point {
		int e;
		int c;

		public Point(int e, int c) {
			this.e = e;
			this.c = c;
		}

	}

	static class Node implements Comparable<Node> {
		int s; // 출발 지점
		int e; // 도착 지점
		int c; // 가중치

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
