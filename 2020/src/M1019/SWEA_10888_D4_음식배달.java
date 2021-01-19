package M1019;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWEA_10888_D4_음식배달 {
	static int N;
	static int arr[][];
	static int[][] dirs = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };
	static ArrayList<int[]> restaurants;
	static int ans;
	static boolean[][] visited, selected;

	static String src = "3\r\n" + "5\r\n" + "0 0 0 0 0\r\n" + "0 1 1 1 0\r\n" + "0 1 10 1 0\r\n" + "0 1 1 1 0  \r\n"
			+ "0 0 0 0 0\r\n" + "8\r\n" + "0 0 0 0 0 0 0 0\r\n" + "0 1 1 1 0 0 0 0\r\n" + "0 1 10 1 0 0 0 0\r\n"
			+ "0 1 1 1 0 0 0 0\r\n" + "0 0 0 0 1 1 1 0\r\n" + "0 0 0 0 1 10 1 0\r\n" + "0 0 0 0 1 1 1 0\r\n"
			+ "0 0 0 0 0 0 0 0\r\n" + "8\r\n" + "0 0 0 0 0 0 0 0\r\n" + "0 1 1 1 0 0 0 0\r\n" + "0 1 20 1 0 0 0 0\r\n"
			+ "0 1 1 1 0 0 0 0\r\n" + "0 0 0 0 1 1 1 0\r\n" + "0 0 0 0 1 30 1 0\r\n" + "0 0 0 0 1 1 1 0\r\n"
			+ "0 0 0 0 0 0 0 0\r\n" + "";

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new StringReader(src));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			sb.append("#" + tc + " ");
			N = Integer.parseInt(br.readLine());
			ans = Integer.MAX_VALUE;
			arr = new int[N][N];
			restaurants = new ArrayList<>();
			visited = new boolean[N][N];
			selected = new boolean[N][N];

			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
					if (arr[i][j] >= 2) {
						restaurants.add(new int[] { i, j });
					}
				}
			}
			sol(0);
			sb.append(ans).append("\n");
		}
		System.out.println(sb);
	}

	static void sol(int cnt) {
		if (cnt == restaurants.size()) {
			bfs();
			return;
		}
		int[] elem = restaurants.get(cnt);
		int r = elem[0];
		int c = elem[1];
		selected[r][c] = true;
		sol(cnt + 1);
		selected[r][c] = false;
		sol(cnt + 1);
	}

	static void bfs() {
		int sum = 0;
		boolean[][] count = new boolean[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (arr[i][j] == 1) {
					Queue<Points> q = new LinkedList<>();
					visited = new boolean[N][N];
					q.add(new Points(i, j, 0));
					visited[i][j] = true;

					while (!q.isEmpty()) {
						Points temp = q.poll();
						int r = temp.r;
						int c = temp.c;
						int d = temp.d;

						if (selected[r][c]) {
							sum += d;
							if (!count[r][c])
								count[r][c] = true;
							break;
						}

						for (int dir = 0; dir < dirs.length; dir++) {
							int nr = r + dirs[dir][0];
							int nc = c + dirs[dir][1];

							if (isIn(nr, nc)) {
								q.offer(new Points(nr, nc, d + 1));
								visited[nr][nc] = true;
							}
						}
					}
				}
			}
		}
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (count[i][j]) {
					sum += arr[i][j];
				}
			}
		}
		if (sum != 0) {
			ans = Math.min(ans, sum);
		}
	}

	private static boolean isIn(int nr, int nc) {
		return nr >= 0 && nr < N && nc >= 0 && nc < N && !visited[nr][nc];
	}

	static class Points {
		int r;
		int c;
		int d;

		public Points(int r, int c, int d) {
			this.r = r;
			this.c = c;
			this.d = d;
		}

	}
}
