package M0420;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 안정적인네트워크 {
	static int N, M;
	static List<Integer> graph[];
	static int parents[];
	static ArrayList<Node> list;
	static PriorityQueue<Node> pq;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int cnt = 0;
		parents = new int[N + 1];
		list = new ArrayList<>();
		pq = new PriorityQueue<>();
		for (int p = 1; p <= N; p++) {
			parents[p] = p;
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			if (findSet(a) == findSet(b))
				continue;
			union(a, b);
			cnt++;
		}
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				int n = Integer.parseInt(st.nextToken());
				if (i > 1 && i < j) {
					pq.add(new Node(i, j, n));
				}

			}
		}

		if (cnt == N - 2) {
			System.out.println(0 + " " + list.size());
		} else {

			Collections.sort(list);
			ArrayList<Pair> ans = new ArrayList<>();
			int cost = 0;
			while(!pq.isEmpty()) {
				Node cur = pq.poll();
				int s = findSet(cur.s);
				int e = findSet(cur.e);
				if (s == e)
					continue;
				union(cur.s, cur.e);
				cost += cur.c;
				cnt++;

				ans.add(new Pair(cur.s, cur.e));
				if (cnt == N - 2)
					break;
			}

			System.out.println(cost + " " + ans.size());
			for (Pair pair : ans) {
				System.out.println(pair.r + " " + pair.c);
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

	static class Pair {
		int r;
		int c;

		public Pair(int r, int c) {
			this.r = r;
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
		public int compareTo(Node n1) {
			return this.c - n1.c;
		}
	}
}
