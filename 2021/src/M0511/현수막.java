package M0511;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 현수막 {
	static int N, M;
	static boolean visited[][];
	static int map[][];
	static int cnt;
	static int dir[][] = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 }, { -1, -1 }, { -1, 1 }, { 1, -1 }, { 1, 1 } };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		map = new int[M][N];
		visited = new boolean[M][N];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		for (int i = 0; i < M; i++) {
			for (int j = 0; j < N; j++) {
				if (!visited[i][j] && map[i][j] == 1) {
					DFS(i, j);
					cnt++;
				}
			}
		}

		System.out.println(cnt);

	}

	private static void DFS(int r, int c) {
		visited[r][c] = true;
		for (int d = 0; d < dir.length; d++) {
			int dr = r + dir[d][0];
			int dc = c + dir[d][1];

			if (isOK(dr, dc) && map[dr][dc] == 1 && !visited[dr][dc]) {
				DFS(dr, dc);
			}
		}

	}

	private static boolean isOK(int dr, int dc) {
		return dr >= 0 && dr < M && dc >= 0 && dc < N;
	}
}
