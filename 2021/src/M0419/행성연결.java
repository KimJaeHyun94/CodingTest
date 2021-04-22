package M0419;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

public class 행성연결 {
	static int N;
	static int parents[];
	static ArrayList<Node> list;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		parents = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			parents[i] = i;
		}
		
		list = new ArrayList<>();
		for (int r = 1; r <= N; r++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int c = 1; c <= N; c++) {
				int n = Integer.parseInt(st.nextToken());
				if (r == c)
					continue;
				else if(r<c)
					list.add(new Node(r, c, n));
			}
		}

		Collections.sort(list, new Comparator<Node>() {

			@Override
			public int compare(Node o1, Node o2) {
				return o1.c - o2.c;
			}

		});

		long ans = 0;
		int cnt = 0;
		for (Node cur : list) {
			int s = findSet(cur.s);
			int e = findSet(cur.e);
			if (s == e)
				continue;
			union(s,e);
			ans += cur.c;
			cnt++;
			
			if (cnt == N - 1)
				break;
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
