package M0317;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 마법사상어와파이어스톰 {
	static int N, Q, R;
	static int map[][];
	static int Route[];
	static int dirs[][] = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };;
	static int sum = 0;
	static int MAX = 0;
	static boolean visited[][];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		Q = Integer.parseInt(st.nextToken());

		R = 1 << N;

		map = new int[R][R];
		Route = new int[Q];
		visited = new boolean[R][R];
		for (int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < R; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < Q; i++) {
			int L = Integer.parseInt(st.nextToken());
			int n = 1 << L;

			for (int j = 0; j < R; j += n) {
				for (int k = 0; k < R; k += n) {
					rotate(j, k, n); // 회전
				}
			}
			melt();
		}
		IceMake();

		System.out.println(sum);
		System.out.println(MAX);
	}

	private static void IceMake() { // 가장 큰 얼음 찾기
		for (int r = 0; r < R; r++) {
			for (int c = 0; c < R; c++) {
				if (map[r][c] > 0) {
					if (!visited[r][c])
						MAX = Math.max(MAX, BFS(r, c));
				}
			}
		}
	}

	private static int BFS(int r, int c) {
		Queue<Ice> q = new LinkedList<>();
		q.add(new Ice(r, c, 0));
		visited[r][c] = true;
		int cnt = 1;

		while (!q.isEmpty()) {
			Ice cur = q.poll();
			sum += map[cur.r][cur.c];
			for (int d = 0; d < dirs.length; d++) {
				int dr = cur.r + dirs[d][0];
				int dc = cur.c + dirs[d][1];

				if (isOK(dr, dc) && map[dr][dc] > 0 && !visited[dr][dc]) {
					visited[dr][dc] = true;
					q.add(new Ice(dr, dc, cur.d + 1));
					cnt++;
				}
			}
		}
		return cnt;
	}

	private static void melt() {
		ArrayList<Node> melt = new ArrayList<>();
		for (int r = 0; r < R; r++) {
			for (int c = 0; c < R; c++) {
				if (map[r][c] > 0) { // 얼음이 있다면
					int cnt = 0;
					for (int d = 0; d < dirs.length; d++) {
						int dr = r + dirs[d][0];
						int dc = c + dirs[d][1];

						if (isOK(dr, dc) && map[dr][dc] > 0) {
							cnt++;
						}
					}
					if (cnt < 3) {
						melt.add(new Node(r, c));
					}
				}
			}
		}

		for (Node node : melt) {
			map[node.r][node.c]--; // 녹이기
		}
	}

	private static void rotate(int r, int c, int n) {
		int[][] arr = new int[n][n];

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				arr[i][j] = map[n - 1 - j + r][i + c];
			}
		}
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				map[r + i][c + j] = arr[i][j];
			}
		}
	}

	private static boolean isOK(int dr, int dc) {
		return dr >= 0 && dr < R && dc >= 0 && dc < R;
	}

	static class Ice {
		int r;
		int c;
		int d;

		public Ice(int r, int c, int d) {
			this.r = r;
			this.c = c;
			this.d = d;
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
