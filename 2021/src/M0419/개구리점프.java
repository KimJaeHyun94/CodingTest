package M0419;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class 개구리점프 {
	static int N, Q;
	static int parents[];
	static ArrayList<Node> list;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		Q = Integer.parseInt(st.nextToken());
		parents = new int[N + 1];
		list = new ArrayList<>();

		for (int i = 1; i <= N; i++) {
			parents[i] = i;
		}

		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			int x1 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());

			list.add(new Node(i, x1, x2, y));
		}

		Collections.sort(list);

		int left = list.get(0).x1;

		for (int i = 0; i < N - 1; i++) {
			if (left <= list.get(i + 1).x2) {
				union(list.get(i).num, list.get(i + 1).num);
			}
			left = Math.min(list.get(i + 1).x1, left);
		}
		for (int i = 0; i < Q; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());

			if (findSet(s) != findSet(e)) {
				System.out.println(0);
			} else {
				System.out.println(1);
			}
		}

	}

	private static void union(int i, int j) {
		int si = findSet(i);
		int sj = findSet(j);

		if (si > sj) {
			parents[si] = sj;
		} else
			parents[sj] = si;
	}

	private static int findSet(int x) {
		if (x == parents[x])
			return x;
		else
			return parents[x] = findSet(parents[x]);
	}

	static class Node implements Comparable<Node> {
		int num;
		int x1;
		int x2;
		int y;

		public Node(int num, int x1, int x2, int y) {
			this.num = num;
			this.x1 = x1;
			this.x2 = x2;
			this.y = y;
		}

		@Override
		public int compareTo(Node o) {
			if (this.x2 == o.x2) {
				return this.x1 - o.x1;
			}
			return o.x2 - this.x2;
		}

	}
}
