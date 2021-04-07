package M0401;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 미로탈출하기 {
	static int[][] dirs = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
	static int R, C;
	static char[][] map;
	static int[][] memo;
	static boolean[][] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new char[R][C];
		visited = new boolean[R][C];
		memo = new int[R][C];
		for (int i = 0; i < R; i++) {
			String str = br.readLine();
			for (int j = 0; j < C; j++) {
				map[i][j] = str.charAt(j);
			}
		}

		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (!visited[i][j]) {
					DFS(i, j);

				}
			}
		}
		int ans = 0;
		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				if (memo[r][c] == 1) {
					ans++;
				}
			}
		}
		System.out.println(ans);

	}

	private static int DFS(int r, int c) {
		visited[r][c] = true;
		int dir = FindDir(map[r][c]);

		int dr = r + dirs[dir][0];
		int dc = c + dirs[dir][1];

		if (!isOK(dr, dc))
			return memo[r][c] = 1;
		else if (memo[dr][dc] != 0)
			return memo[r][c] = memo[dr][dc];

		memo[r][c] = -1;
		return memo[r][c] = DFS(dr, dc);
	}

	private static boolean isOK(int dr, int dc) {
		return dr >= 0 && dr < R && dc >= 0 && dc < C;
	}

	private static int FindDir(char d) {
		switch (d) {
		case 'U':
			return 0;
		case 'D':
			return 1;
		case 'L':
			return 2;
		case 'R':
			return 3;
		}
		return -1;
	}
}
