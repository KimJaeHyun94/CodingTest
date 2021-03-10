package M0309;

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
	static ArrayList<Planet> list;
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
			list.add(new Planet(i, x, y, z));
		}

		Collections.sort(list, new Comparator<Planet>() { // x 좌표
			@Override
			public int compare(Planet p1, Planet p2) {
				if (p1.x == p2.x) {
					return p1.num - p2.num;
				}
				return p1.x - p2.x;
			}
		});

		for (int i = 0; i < list.size() - 1; i++) {
			Planet p1 = list.get(i);
			Planet p2 = list.get(i + 1);

			int distance = Math.abs(list.get(i).x - list.get(i + 1).x);
			pq.add(new Node(p1.num, p2.num, distance));
		}

		Collections.sort(list, new Comparator<Planet>() { // y 좌표
			@Override
			public int compare(Planet p1, Planet p2) {
				if (p1.y == p2.y) {
					return p1.num - p2.num;
				}
				return p1.y - p2.y;
			}
		});

		for (int i = 0; i < list.size() - 1; i++) {
			Planet p1 = list.get(i);
			Planet p2 = list.get(i + 1);

			int distance = Math.abs(list.get(i).y - list.get(i + 1).y);
			pq.add(new Node(p1.num, p2.num, distance));
		}

		Collections.sort(list, new Comparator<Planet>() { // z 좌표
			@Override
			public int compare(Planet p1, Planet p2) {
				if (p1.z == p2.z) {
					return p1.num - p2.num;
				}
				return p1.z - p2.z;
			}
		});

		for (int i = 0; i < list.size() - 1; i++) {
			Planet p1 = list.get(i);
			Planet p2 = list.get(i + 1);

			int distance = Math.abs(list.get(i).z - list.get(i + 1).z);
			pq.add(new Node(p1.num, p2.num, distance));
		}

		parents = new int[N];
		for (int i = 0; i < N; i++) {
			parents[i] = i;
		}
		int cnt = 0;
		int sum = 0;
		while (!pq.isEmpty()) {
			Node cur = pq.poll();
			int s = findSet(cur.s);
			int e = findSet(cur.e);

			if (s == e)
				continue;
			union(s, e);
			sum += cur.c;
			cnt++;
			if (cnt == N - 1)
				break;
		}
		System.out.println(sum);
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

	static class Planet {
		int num, x, y, z;

		public Planet(int num, int x, int y, int z) {
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
