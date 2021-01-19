package M1201;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 캐슬디펜스 {
	static int[][] dirs = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };
	static int N, M, D;
	static int map[][];
	static int copy[][];
	static int[] bower;
	static ArrayList<Sol> list;
	static int ans;
	static boolean killed[][];
	static boolean visited[][];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());

		map = new int[N + 1][M];
		bower = new int[3];

		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < M; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		combination(0, 0);
		System.out.println(ans);
	}

	private static void combination(int start, int r) {
		if (r == 3) {
			ans = Math.max(ans, sol());
			return;
		}
		for (int i = start; i < M; i++) {
			bower[r] = i;
			combination(i + 1, r + 1);
		}
	}

	private static int sol() {
		int cnt = 0;
		copy = new int[N + 1][M];

		deepcopy();

		for (int i = 0; i < N; i++) {
			list = new ArrayList<>();
			killed = new boolean[N][M];

			for (int j = 0; j < bower.length; j++) {
				bfs(N, bower[j]);
			}

			for (Sol sol : list) {
				int r = sol.x;
				int c = sol.y;
				if (!killed[r][c]) {
					killed[r][c] = true;
					copy[r][c] = 0;
					cnt++;
				}
			}
			move();

		}

		return cnt;
	}

	private static void move() {
		for (int i = N - 2; i >= 0; i--) {
			for (int j = 0; j < M; j++) {
				copy[i + 1][j] = copy[i][j];
			}
		}
		for (int i = 0; i < M; i++) {
			copy[0][i] = 0;
		}
	}

	private static void bfs(int br, int bc) {
		Queue<Node> q = new LinkedList<>();
		q.add(new Node(br, bc, 0));
		visited = new boolean[N + 1][M];
		visited[br][bc] = true;

		ArrayList<Sol> enemies = new ArrayList<>();
		int enemy = Integer.MAX_VALUE - 1;

		while (!q.isEmpty()) {
			Node temp = q.poll();
			int r = temp.r;
			int c = temp.c;
			int dep = temp.d;

			if (dep > D)
				break;
			if (dep == enemy + 1)
				break;

			for (int d = 0; d < dirs.length; d++) {
				int dr = r + dirs[d][0];
				int dc = c + dirs[d][1];

				if (isOK(dr, dc)) {
					if (copy[dr][dc] == 1) {
						if (dep + 1 <= D && enemy == Integer.MAX_VALUE - 1 || dep + 1 == enemy) {
							enemy = dep + 1;
							enemies.add(new Sol(dr, dc));
						}
					}
					q.offer(new Node(dr, dc, dep + 1));
					visited[dr][dc] = true;
				}

			}
		}
		if (!enemies.isEmpty()) {
			Collections.sort(enemies, new Comparator<Sol>() {

				@Override
				public int compare(Sol n1, Sol n2) {
					int c1 = n1.y;
					int c2 = n2.y;

					return Integer.compare(c1, c2);
				}
			});
			Sol en = enemies.get(0);
			list.add(new Sol(en.x, en.y));
		}
	}

	private static boolean isOK(int dr, int dc) {
		return dr >= 0 && dr <= N && dc >= 0 && dc < M;
	}

	private static void deepcopy() {
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				copy[r][c] = map[r][c];
			}
		}

	}

	static class Sol {
		int x;
		int y;

		public Sol(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	static class Node {
		int r;
		int c;
		int d;

		public Node(int r, int c, int d) {
			this.r = r;
			this.c = c;
			this.d = d;
		}

	}
}
