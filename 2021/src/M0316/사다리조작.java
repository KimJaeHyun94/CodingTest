package M0316;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 사다리조작 {
	static int N, M, H;
	static int cnt = 0;
	static boolean ladder[][];
	static boolean flag;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());

		ladder = new boolean[H][N];
		flag = false;
		if (M == 0) {
			System.out.println(0);
			System.exit(0);
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken()) - 1;
			int c = Integer.parseInt(st.nextToken()) - 1;
			ladder[r][c] = true; // 사다리 설치
		}
		if (searchOdd() > 3) {
			System.out.println(-1);
			System.exit(0);
			return;
		} else {
			for (int i = 0; i <= 3; i++) {
				cnt = i; // 사다리 갯수
				DFS(0, 0, 0);
				if (flag) {
					System.out.println(i);
					System.exit(0);
				}
			}
		}
		System.out.println(-1);
	}

	private static void DFS(int c, int x, int y) {
		if (flag) {
			return;
		}
		if (c == cnt) {
			Sol();
			return;
		}

		for (int h = x; h < H; h++) {
			for (int n = y; n < N; n++) {
				if (!ladder[h][n]) {
					ladder[h][n] = true;
					DFS(c + 1, h, n + 1);
					ladder[h][n] = false;
				}
				y = 0;
			}
		}
	}

	private static void Sol() {
		for (int c = 0; c < N; c++) {
			int end = c;
			for (int r = 0; r < H; r++) {
				if (end < N - 1 && ladder[r][end]) {
					end++;
				} else if (end > 0 && ladder[r][end - 1]) {
					end--;
				}
			}
			if (end != c) {
				return;
			}
		}
		flag = true;
		return;
	}

	private static int searchOdd() {
		int oddNum = 0;
		for (int j = 0; j < N - 1; j++) {
			int num = 0;
			for (int i = 0; i < H; i++)
				if (ladder[i][j])
					num++;
			if (num % 2 == 1)
				oddNum++;
		}
		return oddNum;
	}
}