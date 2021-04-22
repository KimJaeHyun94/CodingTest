package M0419;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

public class 전력난 {
	static int m, n;
	static int parents[];
	static ArrayList<Node> list;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringBuilder sb = new StringBuilder();
		while (true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			m = Integer.parseInt(st.nextToken());
			n = Integer.parseInt(st.nextToken());
			if (m == 0 && n == 0)
				break;
			parents = new int[m + 1];
			for (int i = 1; i <= m; i++) {
				parents[i] = i;
			}

			list = new ArrayList<>();
			int cost = 0;
			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				int s = Integer.parseInt(st.nextToken());
				int e = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());

				list.add(new Node(s, e, c));
				cost+=c;
			}
			Collections.sort(list, new Comparator<Node>() {

				@Override
				public int compare(Node o1, Node o2) {
					return o1.c - o2.c;
				}
			});
			int cnt = 0;
			int ans = 0;
			for (Node cur : list) {
				int s = findSet(cur.s);
				int e = findSet(cur.e);

				if (s == e)
					continue;
				union(s, e);
				ans += cur.c;
				cnt++;
				if (cnt == m - 1)
					break;
			}
			sb.append(cost-ans).append("\n");
		}
		System.out.println(sb);
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

	static class Node {
		int s; // 출발 지점
		int e; // 도착 지점
		int c; // 가중치

		public Node(int s, int e, int c) {
			this.s = s;
			this.e = e;
			this.c = c;
		}
	}
}
