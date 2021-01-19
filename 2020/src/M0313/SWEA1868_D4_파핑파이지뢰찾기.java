package M0313;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SWEA1868_D4_파핑파이지뢰찾기 {
	static int N;
	static char map[][];
	static int cnt;
	static int[][] dirs = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 }, { 1, 1 }, { -1, -1 }, { 1, -1 }, { -1, 1 } };
	static boolean visited[][];

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			map = new char[N][N];
			visited = new boolean[N][N];
			cnt=0;
			for (int i = 0; i < N; i++) {
				map[i] = br.readLine().toCharArray();
				for (int j = 0; j < N; j++) {
					if (map[i][j] == '.')
						map[i][j] = '0';
				}
			}
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (map[i][j] == '*')
						number(i, j);
				}
			}
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (!visited[i][j] && map[i][j] == '0') {
						dfs(i, j);
						cnt++;
					}
				}
			}
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (map[i][j] != '*' && !visited[i][j])
						cnt++;
				}
			}
			System.out.println("#" + tc + " " + cnt);
		}
	}

	private static void dfs(int x, int y) {
		visited[x][y] = true;
		for (int i = 0; i < dirs.length; i++) {
			int dx = x + dirs[i][0];
			int dy = y + dirs[i][1];
			if (isIn(dx, dy)) {
				if (map[dx][dy] == '*')
					continue;
				if (!visited[dx][dy]) {
					if (map[dx][dy] == '0')
						dfs(dx, dy);
					else {
						visited[dx][dy]=true;
					}
				}
			}
		}
	}

	private static void number(int x, int y) {
		for (int i = 0; i < dirs.length; i++) {
			int dx = x + dirs[i][0];
			int dy = y + dirs[i][1];
			if (isIn(dx, dy) && map[dx][dy] != '*') {
				map[dx][dy]++;
			}
		}
	}

	private static boolean isIn(int dx, int dy) {
		return dx >= 0 && dx < N && dy >= 0 && dy < N;
	}
}
