package M0326;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 비숍 {
	static int dirs[][] = { { -1, -1 }, { -1, 1 } };
	static int N;
	static int chess[][];
	static boolean colors[][];
	static boolean visited[][];
	static int white = Integer.MIN_VALUE;
	static int black = Integer.MIN_VALUE;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		chess = new int[N + 1][N + 1];
		colors = new boolean[N + 1][N + 1];
		visited = new boolean[N + 1][N + 1];

		for (int r = 1; r <= N; r++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int c = 1; c <= N; c++) {
				chess[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		DFS(1, 1, false, 0); // 검정색일때
		DFS(1, 2, true, 0); // 하얀색일때
		System.out.println(black+white);
	}

	private static void DFS(int r, int c, boolean color, int cnt) {
		if (!color) {
			black = Math.max(black, cnt);
		} else {
			white = Math.max(white, cnt);
		}

		if (c > N) {
			r += 1;
			if (!color) {	//검정일 때는
				c = (r % 2 == 0) ? 2 : 1;
			} else {	    //하양일 때는
				c = (r % 2 == 0) ? 1 : 2;
			}
		}

		if (r > N) {
			return;
		}

		if (isOK(r, c)) {
			if (!visited[r][c]) {
				visited[r][c] = true;
				DFS(r, c + 2, color, cnt + 1);
				visited[r][c] = false;
			}
		}
		DFS(r, c + 2, color, cnt);
	}

	private static boolean isOK(int r, int c) {
		if (chess[r][c] == 0)
			return false;
	
		for (int d = 0; d < 2; d++) {
			int dr = r+dirs[d][0];
			int dc = c+dirs[d][1];
			for (int i = 1; i <= N; i++) {
				if (isRange(dr, dc)) {
					if (visited[dr][dc]) {
						return false;
					}
					dr += dirs[d][0];
					dc += dirs[d][1];
				}else
					break;
			}
		}
		return true;
	}

	private static boolean isRange(int dr, int dc) {
		return dr >= 0 && dr <= N && dc >= 0 && dc <= N;
	}
}
