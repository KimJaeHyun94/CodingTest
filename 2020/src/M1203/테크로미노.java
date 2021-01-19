package M1203;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 테크로미노 {
	static int[][] dirs = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };
	static int N, M;
	static int map[][];
	static int MAX;
	static boolean visited[][];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		visited = new boolean[N][M];
		MAX = 0;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				DFS(i, j, 0, 0);
				FuckShape(i, j);
			}
		}
		System.out.println(MAX);
	}

	private static void FuckShape(int r, int c) {
		// 'ㅗ'모양
		if (c - 1 >= 0 && c + 1 < M && r + 1 < N) {
			MAX = Math.max(map[r][c] + map[r][c + 1] + map[r][c - 1] + map[r + 1][c], MAX);
		}
		// 'ㅜ'모양
		if (c - 1 >= 0 && c + 1 < M && r - 1 >= 0) {
			MAX = Math.max(map[r][c] + map[r][c + 1] + map[r][c - 1] + map[r - 1][c], MAX);
		}
		// 'ㅏ'모양
		if (r - 1 >= 0 && r + 1 < N && c + 1 < M) {
			MAX = Math.max(map[r][c] + map[r - 1][c] + map[r + 1][c] + map[r][c + 1], MAX);
		}
		// 'ㅓ'모양
		if (r - 1 >= 0 && r + 1 < N && c - 1 >= 0) {
			MAX = Math.max(map[r][c] + map[r - 1][c] + map[r + 1][c] + map[r][c - 1], MAX);
		}
		return;
	}

	private static void DFS(int r, int c, int cnt, int sum) {
		if (cnt == 4) {
			MAX = Math.max(MAX, sum);
			return;
		}
		for (int d = 0; d < dirs.length; d++) {
			int dr = r + dirs[d][0];
			int dc = c + dirs[d][1];
			if (isOK(dr, dc)) {
				visited[dr][dc] = true;
				DFS(dr,dc,cnt+1,sum+map[dr][dc]);
				visited[dr][dc] = false;
			}
		}
	}

	private static boolean isOK(int dr, int dc) {
		return dr >= 0 && dr < N && dc >= 0 && dc < M && !visited[dr][dc];
	}

}
