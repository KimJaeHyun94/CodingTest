package M0316;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 사다리조작시간개선 {
	static int N, M, H;
	static int cnt = 0;
	static int ladder[][];
	static boolean flag;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());

		ladder = new int[H][N];
		flag = false;
		if (M == 0) {
			System.out.println(0);
			System.exit(0);
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken()) - 1;
			int c = Integer.parseInt(st.nextToken()) - 1;
			ladder[r][c] = 1; // 사다리 설치
			ladder[r][c + 1] = -1;
		}
		if (searchOdd() > 3) {
			System.out.println(-1);
			System.exit(0);
			return;
		} else {
			for (int i = 0; i <= 3; i++) {
				cnt = i; // 사다리 갯수

				if (DFS(0, 0, 0)) {
					System.out.println(i);
					System.exit(0);
				}
			}
		}
		System.out.println(-1);
	}

	private static boolean DFS(int c, int x, int y) {

		if (c == cnt) {
			if (Sol()) {
				return true;
			}
			return false;
		}

		for (int h = x; h < H; h++) {
			for (int n = y; n < N - 1; n++) {
				if (ladder[h][n] == 0 && ladder[h][n + 1] == 0) {
					ladder[h][n] = 1;
					ladder[h][n + 1] = -1;
					if (DFS(c + 1, h, n + 2))
						return true;
					ladder[h][n] = 0;
					ladder[h][n + 1] = 0;
				}
			}
			y = 0;
		}
		return false;
	}

	private static boolean Sol() {
		for (int c = 0; c < N; c++) {
			int end = c;
			for (int r = 0; r < H; r++) {
				if (ladder[r][end] == 1) {
					end++;
				} else if (ladder[r][end] == -1) {
					end--;
				}
			}
			if (end != c) {
				return false;
			}
		}
		return true;
	}

	private static int searchOdd() {
		int oddNum = 0;
		for (int j = 0; j < N - 1; j++) {
			int num = 0;
			for (int i = 0; i < H; i++)
				if (ladder[i][j] == 1)
					num++;
			if (num % 2 == 1)
				oddNum++;
		}
		return oddNum;
	}
}
