package M0410;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class 네트워크연결 {
	static int N, M;
	static ArrayList<Node> list;
	static int parents[];

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		list = new ArrayList<>();
		for (int i = 0; i < M; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());

			list.add(new Node(s, e, c));
		}
		parents = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			parents[i] = i;
		}

		Collections.sort(list);

		int ans = 0;
		int cnt = 0;
		for (Node temp : list) {
			int s = findSet(temp.s);
			int e = findSet(temp.e);

			if (s == e)
				continue;
			else
				union(s, e);

			ans += temp.c;
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
			} else {
				parents[ps] = pe;
			}
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
