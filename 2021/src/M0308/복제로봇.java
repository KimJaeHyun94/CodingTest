package M0308;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class 복제로봇 {
	static int[][] dirs = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };
	static PriorityQueue<Edge> pq;
	static char[][] map;
	static int N, M;
	static ArrayList<Node> keys;
	static int parents[];
	static int keymap[][];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new char[N][N];
		keys = new ArrayList<>();
		keymap = new int[N][N];
		int idx = 0;
		pq = new PriorityQueue<>();
		for (int i = 0; i < N; i++) {
			String line = br.readLine();
			for (int j = 0; j < N; j++) {
				map[i][j] = line.charAt(j);

				if (map[i][j] != '0' && map[i][j] != '1') {
					keys.add(new Node(i, j));
					keymap[i][j] = idx++;
				}
			}
		}

		for (Node key : keys) {
			if (!BFS(key)) {
				System.out.println(-1);
				System.exit(0);
			}
		}
		parents = new int[M + 1];
		for (int i = 1; i <= M; i++) {
			parents[i] = i;
		}
		int cnt = 0;
		int ans = 0;

		while (!pq.isEmpty()) {
			Edge cur = pq.poll();
			int s = findSet(cur.s);
			int e = findSet(cur.e);

			if (s == e)
				continue;
			union(s, e);
			ans += cur.weight;

			if (cnt == M)
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

	private static int findSet(int e) {
		if (e == parents[e]) {
			return e;
		} else {
			parents[e] = findSet(parents[e]);
			return parents[e];
		}
	}

	private static boolean BFS(Node key) {
		Queue<Robot> q = new LinkedList<>();
		q.add(new Robot(key.r, key.c, 0));

		int cnt = 0;
		boolean visited[][] = new boolean[N][N];
		visited[key.r][key.c] = true;
		while (!q.isEmpty()) {
			Robot cur = q.poll();
			int r = cur.r;
			int c = cur.c;
			int w = cur.w; // 거리

			for (int d = 0; d < dirs.length; d++) {
				int dr = r + dirs[d][0];
				int dc = c + dirs[d][1];

				if (isOK(dr, dc) && !visited[dr][dc] && map[dr][dc] != '1') {
					visited[dr][dc] = true;
					q.add(new Robot(dr, dc, w + 1));
					if (map[dr][dc] != '0') {
						pq.add(new Edge(keymap[key.r][key.c], keymap[dr][dc], w + 1));
						cnt++;
					}
				}
			}
		}
		if (cnt == M)
			return true;
		return false;
	}

	private static boolean isOK(int dr, int dc) {
		return dr >= 0 && dr < N && dc >= 0 && dc < N;
	}

	static class Robot {
		int r;
		int c;
		int w;

		public Robot(int r, int c, int w) {
			this.r = r;
			this.c = c;
			this.w = w;
		}
	}

	static class Edge implements Comparable<Edge> {
		int s, e, weight;

		Edge(int s, int e, int weight) {
			this.s = s;
			this.e = e;
			this.weight = weight;
		}

		public int compareTo(Edge e) {
			return weight - e.weight;
		}
	}

	static class Node {
		int r, c;

		public Node(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
}
