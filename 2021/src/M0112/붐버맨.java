package M0112;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 붐버맨 {
	static int[][] dirs = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };
	static boolean[][] visited;
	static int R, C;
	static int time;
	static int bomb[][];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());

		visited = new boolean[R][C];
		bomb = new int[R][C];

		for (int i = 0; i < R; i++) {
			String str = br.readLine();
			for (int j = 0; j < C; j++) {
				if (str.charAt(j) == 'O') {
					bomb[i][j] = 0;
				} else {
					bomb[i][j] = -1;
				}
			}
		}

		if (N == 1) {
			print();
		} else if (N % 2 == 0) { // 짝수랑 1은 거름
			for (int i = 0; i < R; i++) {
				for (int j = 0; j < C; j++) {
					System.out.print('O');
				}
				System.out.println();
			}
		} else { // 3부터 홀수들
			time = 2;
			while (true) {
				if (time > N)
					break;
				if (time % 2 == 0) {
					setting();
				} else {
					visited = new boolean[R][C];
					sol(time);
					back();
				}
				time++;
			}
			print();
		}
	}

	private static void back() {
		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				if (visited[r][c])
					bomb[r][c] = -1;
			}
		}
	}

	private static void setting() {
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (bomb[i][j] == -1) { // 거꾸로 세팅
					bomb[i][j] = time;
				}
			}
		}
	}

	private static void sol(int time) {
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (bomb[i][j] == time - 3) {
					BFS(i, j);
				}
			}
		}
	}

	private static void print() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (bomb[i][j] == -1) {
					sb.append('.');
				} else {
					sb.append('O');
				}
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}

	private static void BFS(int i, int j) {
		Queue<int[]> q = new LinkedList<>();
		q.add(new int[] { i, j });
		visited[i][j] = true;

		while (!q.isEmpty()) {
			int[] temp = q.poll();

			int r = temp[0];
			int c = temp[1];
			for (int d = 0; d < dirs.length; d++) {
				int dr = r + dirs[d][0];
				int dc = c + dirs[d][1];

				if (isOK(dr, dc)) {
					visited[dr][dc] = true;
				}
			}
		}
	}

	private static boolean isOK(int dr, int dc) {
		return dr >= 0 && dr < R && dc >= 0 && dc < C;
	}

}
