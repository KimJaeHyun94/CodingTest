package M1214;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 원판돌리기 {
	static int R, C, T;
	static int[][] dirs = { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 } };
	static int map[][];
	static int play[][];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());

		map = new int[R][C];
		play = new int[T][3];

		for (int r = 0; r < R; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < C; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}

		}
		for (int t = 0; t < T; t++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int k = Integer.parseInt(st.nextToken());
			rotate(x, d, k);
			if (!remove()) {
				sol();
			}
		}
		int ans = 0;
		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				if (map[r][c] != -1) {
					ans += map[r][c];
				}
			}
		}

		System.out.println(ans);
	}

	private static void sol() {
		int sum = 0;
		int cnt = 0;
		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				if (map[r][c] != -1) {
					sum += map[r][c];
					cnt++;
				}
			}
		}

		double avg = sum / (double)cnt;

		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				if (map[r][c] != -1) {
					if (map[r][c] > avg) {
						map[r][c]--;
					} else if (map[r][c] < avg) {
						map[r][c]++;
					}
				}
			}
		}
	}

	private static boolean remove() {
		boolean flag = false;
		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				if (map[r][c] == -1)
					continue;
				Queue<Node> q = new LinkedList<>();
				q.add(new Node(r, c, map[r][c]));

				while (!q.isEmpty()) {
					Node temp = q.poll();

					for (int d = 0; d < dirs.length; d++) {
						int dr = temp.r + dirs[d][0];
						int dc = temp.c + dirs[d][1];

						if (dr < 0 || dr >= R)
							continue;

						if (dc < 0)
							dc = C - 1;
						else if (dc >= C)
							dc = 0;

						if (map[dr][dc] == -1)
							continue;

						if (map[dr][dc] == temp.s) {
							q.add(new Node(dr, dc, temp.s));
							map[temp.r][temp.c] = -1;
							map[dr][dc] = -1;
							flag = true;
						}
					}
				}
			}
		}
		return flag;
	}

	private static void rotate(int x, int d, int k) {
		for (int r = 0; r < R; r++) {
			if ((r + 1) % x != 0) { // x의 배수만 돌아가기 때문에
				continue;
			}
			if (d == 0) {
				for (int i = 0; i < k; i++) {
					int temp = map[r][C - 1];
					for (int c = C - 1; c > 0; c--) {
						map[r][c] = map[r][c - 1];
					}
					map[r][0] = temp;
				}

			} else {
				for (int i = 0; i < k; i++) {
					int temp = map[r][0];
					for (int c = 0; c < C - 1; c++) {
						map[r][c] = map[r][c + 1];
					}
					map[r][C - 1] = temp;
				}
			}
		}
	}

	static class Node {
		int r;
		int c;
		int s;

		public Node(int r, int c, int s) {
			this.r = r;
			this.c = c;
			this.s = s;
		}

	}
}
