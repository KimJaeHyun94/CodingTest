package M0227;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ11123양한마리양두마리 {
	static boolean[][] visited;
	static int H;
	static int W;
	static char map[][];
	static int cnt;
	private static int dir[][] = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
		for (int i = 0; i < T; i++) {
			st = new StringTokenizer(br.readLine());
			H = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			map = new char[H][W];
			visited = new boolean[H][W];
			for (int j = 0; j < H; j++) {
				String str = br.readLine();
				cnt = 0;
				for (int k = 0; k < W; k++) {
					map[j][k] = str.charAt(k);
				}
			}
			for (int j = 0; j < H; j++) {
				for (int k = 0; k < W; k++) {
					if (map[j][k] == '#' && !visited[j][k]) {
						DFS(j, k);
						cnt++;
					}
				}
			}
			System.out.println(cnt);
		}

	}

	private static void DFS(int y, int x) {
		visited[y][x] = true;

		for (int i = 0; i < dir.length; i++) {
			int dy = y + dir[i][0];
			int dx = x + dir[i][1];

			if (isOK(dy, dx)) {
				DFS(dy, dx);
			}
		}

	}

	private static boolean isOK(int dy, int dx) {
		if (dy >= 0 && dy < H && dx >= 0 && dx < W) {
			if (!visited[dy][dx] && map[dy][dx] == '#')
				return true;
		}
		return false;
	}
}
