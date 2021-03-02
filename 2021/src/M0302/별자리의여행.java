package M0302;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 별자리의여행 {
	static int parents[];
	static PriorityQueue<Node> pq = new PriorityQueue<>();

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		ArrayList<star> list = new ArrayList<>();

		for (int i = 1; i <= N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			double x = Double.parseDouble(st.nextToken());
			double y = Double.parseDouble(st.nextToken());
			list.add(new star(i, x, y));
		}

		parents = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			parents[i] = i;
		}

		for (int i = 0; i < list.size() - 1; i++) {
			for (int j = i + 1; j < list.size(); j++) {
				star s1 = list.get(i);
				star s2 = list.get(j);

				Double distance = Math.sqrt((s1.x - s2.x) * (s1.x - s2.x) + (s1.y - s2.y) * (s1.y - s2.y));
				pq.add(new Node(list.get(i).idx, list.get(j).idx, distance));
			}
		}

		double ans = 0;

		while (!pq.isEmpty()) {
			Node temp = pq.poll();

			int s = findSet(temp.s);
			int e = findSet(temp.e);

			if (s == e)
				continue;
			union(s, e);
			ans += temp.c;

		}
		System.out.println(ans);

	}

	private static void union(int i, int j) {
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
		double c; // 별자리 간의 거리

		public Node(int s, int e, double c) {
			this.s = s;
			this.e = e;
			this.c = c;
		}

		@Override
		public int compareTo(Node o) {
			return Double.compare(this.c, o.c);
		}
	}

	static class star {
		int idx;
		double x;
		double y;

		public star(int idx, double x, double y) {
			this.idx = idx;
			this.x = x;
			this.y = y;
		}
	}

}
