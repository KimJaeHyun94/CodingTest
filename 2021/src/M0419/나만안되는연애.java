package M0419;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

public class 나만안되는연애 {
	static int N, M;
	static int parents[];
	static int sex[];
	static ArrayList<Node> list;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		sex = new int[N + 1];
		parents = new int[N + 1];

		for (int i = 1; i <= N; i++) {
			parents[i] = i;
		}
		list = new ArrayList<>();
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			int ch = st.nextToken().charAt(0);
			if (ch == 'M') {
				sex[i] = 0;
			} else
				sex[i] = 1;
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());

			list.add(new Node(s, e, c));
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
			if (sex[cur.s] == sex[cur.e])
				continue;
			union(s, e);
			ans += cur.c;
			cnt++;
			if (cnt == N - 1)
				break;
		}
		if (cnt == N - 1)
			System.out.println(ans);
		else
			System.out.println(-1);
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

	private static void union(int i, int j) {
		int si = findSet(i);
		int sj = findSet(j);

		if (si > sj) {
			parents[si] = sj;
		} else {
			parents[sj] = si;
		}
	}

	private static int findSet(int x) {
		if (x == parents[x])
			return x;
		else
			return parents[x] = findSet(parents[x]);
	}
}
