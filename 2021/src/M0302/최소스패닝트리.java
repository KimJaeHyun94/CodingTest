package M0302;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class 최소스패닝트리 {
	static int parents[];
	static int V, E;
	static ArrayList<Node> list;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());

		list = new ArrayList<>();

		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			int C = Integer.parseInt(st.nextToken());

			list.add(new Node(A, B, C));
		}
		parents = new int[V + 1];
		for (int i = 1; i <= V; i++) {
			parents[i] = i;
		}

		int cnt = 0;
		int ans = 0;

		Collections.sort(list, new Comparator<Node>() {
			@Override
			public int compare(Node o1, Node o2) {
				return Double.compare(o1.c, o2.c);
			}
		});

		for (Node temp : list) {
			int s = findSet(temp.s);
			int e = findSet(temp.e);
			if (s == e)
				continue;
			union(s, e);
			ans += temp.c;
			cnt++;
			if (cnt == V - 1)
				break;
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

	static class Node {
		int s; // 출발 지점
		int e; // 도착 지점
		double c; // 가중치

		public Node(int s, int e, double c) {
			this.s = s;
			this.e = e;
			this.c = c;
		}
	}
}
