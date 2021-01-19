package M0115;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class TwoDots {
	static int N, M;
	static char map[][];
	static boolean visited[][];
	static int[][] dirs = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new char[N][M];
		visited = new boolean[N][M];
		
		for (int i = 0; i < N; i++) {
			String line = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = line.charAt(j);
			}
		}
		
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				if (!visited[r][c]) {
					visited[r][c] = true;
					DFS(r, c, -10);
				}
			}
		}
		System.out.println("No");
	}

	private static void DFS(int r, int c, int dir) {
		for (int d = 0; d < dirs.length; d++) {
			int dr = r + dirs[d][0];
			int dc = c + dirs[d][1];

			if (isOK(dr, dc) && (dir+d)%4!=1 && map[dr][dc] == map[r][c]) {
				if(visited[dr][dc]) {
					end();
				}
				visited[dr][dc] = true;
				DFS(dr, dc, d);
			}
		}
	}

	private static boolean isOK(int dr, int dc) {
		return dr >= 0 && dr < N && dc >= 0 && dc < M;
	}

	private static void end() {
		System.out.println("Yes");
		System.exit(0);
	}
}
