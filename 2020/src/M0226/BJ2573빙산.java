package M0226;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ2573빙산 {
	private static int N, M;
	private static int map[][];
	private static boolean visited[][];
	private static int[][] melt;
	private static int cnt;
	private static int dir[][] = { { -1, 0 }, { 1, 0 }, { 0, 1 }, { 0, -1 } };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		melt = new int[N][M];
		visited = new boolean[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		int year = 0;

		while (true) {
			cnt = 0;
			func();

			if (cnt == 0) {
				year = 0;
				break;
			} else if (cnt >= 2) {
				break;
			}
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					map[i][j] -= melt[i][j];
					visited[i][j] = false;
					melt[i][j] = 0;
				}
			}
			year++;
		}
		System.out.println(year);
	}

	private static void func() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] > 0 && !visited[i][j]) {
					DFS(i, j);
					cnt++;
				}
			}
		}
	}

	private static void DFS(int y, int x) {
		visited[y][x] = true;

		for (int i = 0; i < dir.length; i++) {
			int dy = y + dir[i][0];
			int dx = x + dir[i][1];

			if (isOK(dy, dx)) {
				if (map[dy][dx] == 0)
					melt[y][x]++;

				if (!visited[dy][dx] && map[dy][dx] != 0)
					DFS(dy, dx);
			}
		}
	}

	private static boolean isOK(int dy, int dx) {
		if (dy >= 0 && dy < N && dx >= 0 && dx < M) {
			return true;
		}
		return false;
	}
}
