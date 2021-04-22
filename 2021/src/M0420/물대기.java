package M0420;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class 물대기 {
	static int N;
	static int W[];
	static int parents[];
	static List<Node> list;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		W = new int[N + 1];
		list = new ArrayList<Node>();
		parents = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			list.add(new Node(i, 0, Integer.parseInt(br.readLine())));
			parents[i] = i;
		}
		for (int i = 1; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				int n = Integer.parseInt(st.nextToken());
				if (i == j)
					continue;
				list.add(new Node(i, j, n));
			}
		}

		Collections.sort(list);

		int ans = 0;
		for (Node cur : list) {
			int s = findSet(cur.s);
			int e = findSet(cur.e);

			if (s == e)
				continue;
			union(s, e);

			ans += cur.c;

		}
		System.out.println(ans);
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
		int s; // 출발 지점
		int e; // 도착 지점
		int c; // 가중치

		public Node(int s, int e, int c) {
			this.s = s;
			this.e = e;
			this.c = c;
		}

		@Override
		public int compareTo(Node n1) {
			return this.c - n1.c;
		}
	}
}
