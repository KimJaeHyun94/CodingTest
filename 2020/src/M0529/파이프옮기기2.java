package M0529;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 파이프옮기기2 {
	static int N;
	static int map[][];
	static int dirs[][] = { { 0, 1 }, { 1, 0 }, { 1, 1 } };
	static int cnt;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		DFS(0, 1, 0);
		System.out.println(cnt);
	}

	private static void DFS(int x, int y, int dir) {
		if (x == N - 1 && y == N - 1) {
			cnt++;
			return;
		}
		for (int i = 0; i < dirs.length; i++) {
			if ((i == 0 && dir == 1) || (i == 1 && dir == 0)) {
				continue;
			}
			int dx = x + dirs[i][0];
			int dy = y + dirs[i][1];

			if (isOK(dx, dy) && map[dx][dy] == 0) {
				if (i == 2) {
					if (map[x + 1][y] == 0 && map[x][y + 1] == 0) {
						DFS(dx, dy, i);
					} else
						continue;
				} else
					DFS(dx, dy, i);
			}
		}
	}

	private static boolean isOK(int dx, int dy) {
		return dx < N && dy < N;
	}

}