package M0301;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class 다리만들기2 {
	static int N, M;
	static int map[][];
	static int dirs[][] = { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 } };
	static boolean visited[][];
	static int cnt;
	static int parents[];
	static List<start> list;
	static LinkedList<Node> anslist;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		visited = new boolean[N][M];
		list = new ArrayList<>();
		anslist = new LinkedList<Node>();

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		cnt = 1; // 번호 마킹
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				if (map[r][c] != 0 && !visited[r][c]) {
					map[r][c] = cnt;
					DFS(r, c);
					cnt++;
				}
			}
		}

		for (int i = 0; i < list.size(); i++) {  //하나씩 출발
			build(i);
		}

		parents = new int[cnt];
		int cnt2 = 0;
		for (int i = 0; i < parents.length; i++) {
			parents[i] = i;
		}
		int size = anslist.size();
		int ans = 0;
		Collections.sort(anslist, new Comparator<Node>() {
			@Override
			public int compare(Node a0, Node a1) {
				return a0.v-a1.v;
			}
			
		});
		
		for (int i = 0; i < size; i++) {
			Node temp = anslist.get(i);

			int a = findSet(temp.s);
			int b = findSet(temp.e);

			if (a == b)
				continue;

			union(a, b);
			ans += temp.v;
			cnt2++;
			if (cnt2 == cnt - 2)
				break;
		}
		if (ans == 0 || cnt2 != cnt - 2)
			System.out.println(-1);
		else
			System.out.println(ans);

	}

	private static void build(int i) {
		Queue<int[]> q = new LinkedList<>();
		int ans = list.get(i).ans;
		int dir = list.get(i).dir;
		q.add(new int[] { list.get(i).r, list.get(i).c, 1 });

		while (!q.isEmpty()) {
			int[] temp = q.poll();
			int r = temp[0];
			int c = temp[1];
			int len = temp[2];

			int dr = r + dirs[dir][0];
			int dc = c + dirs[dir][1];

			if (isOK(dr, dc)) {
				if (map[dr][dc] == 0) {
					q.add(new int[] { dr, dc, len + 1 });
				} else if (map[dr][dc] == ans) {
					continue;
				} else {
					if (len > 1) { // 다리 길이가 1이면 올바르지 않다
						anslist.add(new Node(ans, map[dr][dc], len));  //각각 다리 넣어주기
						return;
					}
				}
			}
		}
	}

	private static void DFS(int r, int c) {
		visited[r][c] = true;

		for (int d = 0; d < dirs.length; d++) {
			int dr = r + dirs[d][0];
			int dc = c + dirs[d][1];

			if (isOK(dr, dc) && !visited[dr][dc]) {
				if (map[dr][dc] != 0) {
					map[dr][dc] = cnt;
					DFS(dr, dc);
				} else {
					list.add(new start(dr, dc, d, cnt)); // 각각의 섬에서 출발 지점을 선정
				}
			}
		}
	}

	private static boolean isOK(int dr, int dc) {
		return dr >= 0 && dr < N && dc >= 0 && dc < M;
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

	static void makeSet(int x) {
		parents[x] = x;
	}

	static class start {
		int r;
		int c;
		int dir;
		int ans;

		public start(int r, int c, int dir, int ans) {
			this.r = r;
			this.c = c;
			this.dir = dir;
			this.ans = ans;
		}
	}

	static class Node {
		int s; // 출발 지점
		int e; // 도착 지점
		int v; // 길이

		public Node(int s, int e, int v) {
			this.s = s;
			this.e = e;
			this.v = v;
		}

	}
}
