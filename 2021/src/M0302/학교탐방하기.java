package M0302;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 학교탐방하기 {
	static ArrayList<Node> list;

	static int N, M;
	static int parents[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		list = new ArrayList<>();
		for (int i = 0; i <= M; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());

			list.add(new Node(s, e, c));
		}

		int MAX = 0;
		
		parents = new int[N + 1];
		for (int i = 0; i <= N; i++) {
			parents[i] = i;
		}
		
		Collections.sort(list, (e1, e2) -> e1.c - e2.c);
		
		for (Node temp : list) {
			int s = findSet(temp.s);
			int e = findSet(temp.e);
			if (s == e)
				continue;
			union(s, e);
			if (temp.c == 0) {
				MAX++;
			}
		}
		
		parents = new int[N + 1];
		for (int i = 0; i <= N; i++) {
			parents[i] = i;
		}
		
		Collections.sort(list, (e1, e2) -> e2.c - e1.c);
		
		int MAX2 = 0;
		for (Node temp : list) {
			int s = findSet(temp.s);
			int e = findSet(temp.e);
			if (s == e)
				continue;
			union(s, e);
			if (temp.c == 0) { //오르막길 개수
				MAX2++;
			}
		}
		
		System.out.println(MAX*MAX - MAX2*MAX2);  //피로도 차이
	}

	private static void union(int i, int j) {
		int pi = findSet(i);
		int pj = findSet(j);

		if (pi != pj) {
			if (pi < pj) {
				parents[pj] = pi;
			} else {
				parents[pi] = pj;
			}
		}
	}

	static int findSet(int x) {
		if (x == parents[x]) {
			return x;
		} else {
			parents[x] = findSet(parents[x]);
			return parents[x];
		}
	}

	static class Node {
		int s; // 출발 지점
		int e; // 도착 지점
		int c; // 오르막길 내리막길

		public Node(int s, int e, int c) {
			this.s = s;
			this.e = e;
			this.c = c;
		}
	}
}
