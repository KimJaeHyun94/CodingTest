package M0416;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Gaaaaaaaaaarden_최적화 {
	static int N, M, G, R;
	static int map[][];
	static int dirs[][] = { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 } };
	static ArrayList<Node> list = new ArrayList<>();
	static int ans = Integer.MIN_VALUE;
	static int num[][];
	static boolean flower[][];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		G = Integer.parseInt(st.nextToken()); // 초록 배양액
		R = Integer.parseInt(st.nextToken()); // 빨강 배양액

		map = new int[N][M];
		num = new int[N][M];

		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < M; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
				if (map[r][c] == 2) {
					list.add(new Node(r, c));
				}
			}
		}
		DFS(0, 0);
		System.out.println(ans);
	}

	private static void DFS(int cnt, int idx) {
		if (cnt == G) {
			DFS2(0, 0);
			return;
		}

		for (int i = idx; i < list.size(); i++) {
			int r = list.get(i).r;
			int c = list.get(i).c;

			if (num[r][c] == 0) {
				num[r][c] = 1;
				DFS(cnt + 1, i + 1);
				num[r][c] = 0;
			}
		}

	}

	private static void DFS2(int cnt, int idx) {
		if (cnt == R) {
			BFS();
			return;
		}
		for (int i = idx; i < list.size(); i++) {
			int r = list.get(i).r;
			int c = list.get(i).c;

			if (num[r][c] == 0) {
				num[r][c] = 2;
				DFS2(cnt + 1, i + 1);
				num[r][c] = 0;
			}
		}

	}

	private static void BFS() {
		int cnt = 0;
		int time[][] = new int[N][M];
		int colornum[][] = new int[N][M];
		boolean visited[][] = new boolean[N][M];

		flower = new boolean[N][M];
		Queue<Soil> q = new LinkedList<>();
		int copymap[][] = Copy();

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				colornum[i][j] = num[i][j];

				if (colornum[i][j] >= 1) {
					visited[i][j] = true;
					q.add(new Soil(i, j, colornum[i][j]));
				}
			}
		}

		while (!q.isEmpty()) {
			Soil cur = q.poll();

			if (flower[cur.r][cur.c]) {
				continue;
			}

			for (int d = 0; d < dirs.length; d++) {
				int dr = cur.r + dirs[d][0];
				int dc = cur.c + dirs[d][1];

				if (isOK(dr, dc) && (copymap[dr][dc] == 1 || copymap[dr][dc] == 2) && !flower[dr][dc]) {
					if (!visited[dr][dc]) {
						colornum[dr][dc] = cur.color;
						time[dr][dc] = time[cur.r][cur.c] + 1;
						visited[dr][dc] = true;
						q.add(new Soil(dr, dc, cur.color));
					} else {
						if (time[dr][dc] == time[cur.r][cur.c] + 1 && colornum[dr][dc] + colornum[cur.r][cur.c] == 3) {
							cnt++;
							flower[dr][dc] = true;
						}
					}
				}
			}
		}
		ans = Math.max(ans, cnt);
	}

	private static boolean isOK(int dr, int dc) {
		return dr >= 0 && dr < N && dc >= 0 && dc < M;
	}

	private static int[][] Copy() {
		int copy[][] = new int[N][M];
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				copy[r][c] = map[r][c];
			}
		}
		return copy;
	}

	static class Soil {
		int r;
		int c;
		int color;

		public Soil(int r, int c, int color) {

			this.r = r;
			this.c = c;
			this.color = color;
		}

	}

	static class Node {
		int r;
		int c;

		public Node(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
}
