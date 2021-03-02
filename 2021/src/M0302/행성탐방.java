package M0302;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 행성탐방 {
	static int N;
	static ArrayList<planet> list;
	static PriorityQueue<Node> pq;
	static int parents[];

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		list = new ArrayList<>();
		pq = new PriorityQueue<>();
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int z = Integer.parseInt(st.nextToken());
			list.add(new planet(i, x, y, z));
		}

		Collections.sort(list, new Comparator<planet>() { // x좌표 기준 정렬
			@Override
			public int compare(planet a0, planet a1) {
				return a0.x - a1.x;
			}
		});

		for (int i = 0; i < N - 1; i++) {
			int sum = Math.abs(list.get(i).x - list.get(i + 1).x);
			pq.add(new Node(list.get(i).num, list.get(i + 1).num, sum));
		}

		Collections.sort(list, new Comparator<planet>() { // y좌표 기준 정렬
			@Override
			public int compare(planet a0, planet a1) {
				return a0.y - a1.y;
			}
		});

		for (int i = 0; i < N - 1; i++) {
			int sum = Math.abs(list.get(i).y - list.get(i + 1).y);
			pq.add(new Node(list.get(i).num, list.get(i + 1).num, sum));
		}

		Collections.sort(list, new Comparator<planet>() { // z좌표 기준 정렬
			@Override
			public int compare(planet a0, planet a1) {
				return a0.z - a1.z;
			}
		});

		for (int i = 0; i < N - 1; i++) {
			int sum = Math.abs(list.get(i).z - list.get(i + 1).z);
			pq.add(new Node(list.get(i).num, list.get(i + 1).num, sum));
		}

		parents = new int[N];
		for (int i = 0; i < N; i++) {
			parents[i] = i;
		}
		int ans = 0, cnt = 0;

		while (!pq.isEmpty()) {
			Node temp = pq.poll();

			int s = findSet(temp.s);
			int e = findSet(temp.e);

			if (s == e)
				continue;
			union(s,e);
			ans += temp.c;
			cnt++;
			if (cnt == N - 1) {
				break;
			}
		}

		System.out.println(ans);
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

	static class planet {
		int num, x, y, z;

		public planet(int num, int x, int y, int z) {
			this.num = num;
			this.x = x;
			this.y = y;
			this.z = z;
		}

	}

	static class Node implements Comparable<Node> {
		int s; // 출발 지점
		int e; // 도착 지점
		int c; // 오르막길 내리막길

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
