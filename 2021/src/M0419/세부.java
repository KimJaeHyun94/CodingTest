package M0419;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

public class 세부 {
	static int parents[];
	static int N, M;
	static ArrayList<Node> list;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		parents = new int[N + 1];
		list = new ArrayList<>();
		for (int i = 1; i <= N; i++) {
			parents[i] = i;
		}

		st = new StringTokenizer(br.readLine());
		int S = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());

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
				return o2.c - o1.c;
			}
		});

		boolean flag = false;
		for (Node child : list) {
			int s = findSet(child.s);
			int e = findSet(child.e);

			if (s != e) {
				union(s, e);
			}
			if (findSet(S) == findSet(E)) {
				System.out.println(child.c);
				flag = true;
				break;
			}
		}
		if (!flag)
			System.out.println(0);

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
