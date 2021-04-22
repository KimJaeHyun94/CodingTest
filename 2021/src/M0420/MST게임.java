package M0420;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class MST게임 {
	static int N, M, K;
	static int parents[];
	static int rank[];
	static List<Node> list;
	static List<Node> origin;
	static PriorityQueue<Node> pq = new PriorityQueue<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		origin = new ArrayList<Node>();
		list = new ArrayList<Node>();
		for (int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());

			origin.add(new Node(s, e, i));
		}

		Collections.sort(origin);

		int ans = 0;
		int cnt = 0;
		boolean flag = false;

		for (int i = 0; i < K; i++) {
			if (flag) {
				System.out.print("0 ");
				continue;
			}
			ans = 0;
			cnt = 0;

			parents = new int[N + 1];
			for (int p = 1; p <= N; p++) {
				parents[p] = p;
			}
			list = origin.subList(i, M);
			for (int j = 0; j < list.size(); j++) {
				int s = findSet(list.get(j).s);
				int e = findSet(list.get(j).e);

				if (s == e)
					continue;
				union(s, e);
				ans += list.get(j).c;
				cnt++;

				if (cnt == N - 1)
					break;
			}

			if (cnt == N - 1) {
				System.out.print(ans+" ");
			} else {
				System.out.print("0 ");
				flag = true;
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
