package M0403;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

public class 중량제한 {
	static int N, M;
	static int parents[];
	static ArrayList<Node> list;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		list = new ArrayList<>();

		parents = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			parents[i] = i;
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			int C = Integer.parseInt(st.nextToken());

			list.add(new Node(A, B, C));
		}
		st = new StringTokenizer(br.readLine());
		int start = Integer.parseInt(st.nextToken());
		int end = Integer.parseInt(st.nextToken());
		Collections.sort(list, new Comparator<Node>() {
			@Override
			public int compare(Node o1, Node o2) {
				return Integer.compare(o2.c, o1.c);
			}
		});

		int ans = 0;
		
		
		for (Node temp : list) {
			int s = temp.s;
			int e = temp.e;
			if (findSet(s) != findSet(e))
				union(findSet(s), findSet(e));

			if (findSet(start) == findSet(end)) {
				System.out.println(temp.c);
				break;
			}

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
