package M0323;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 색종이붙이기 {
	static int map[][];
	static int paper[] = { 5, 5, 5, 5, 5 };
	static int min = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		map = new int[10][10];

		for (int r = 0; r < 10; r++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int c = 0; c < 10; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		DFS(0, 0, 0);
		if (min == Integer.MAX_VALUE) {
			System.out.println(-1);
		} else {
			System.out.println(min);
		}

	}

	private static void DFS(int r, int c, int cnt) {

		if (r == 10) { // 마지막까지 다 탐색했다면
			min = Math.min(cnt, min);
			return;
		}

		if (c > 9) {
			DFS(r + 1, 0, cnt);
			return;
		}

		if (cnt >= min) {
			return;
		}
		if (map[r][c] == 1) {
			for (int s = 5; s >= 1; s--) { // 큰 거부터 붙여봅니다.
				if (paper[s - 1] > 0 && check(r, c, s)) {
					if (check(r, c, s)) { // 모두 다 1이라면(붙일 수 있다면)
						attach(r, c, s);
						paper[s - 1]--;
						DFS(r, c + 1, cnt + 1);
						cut(r, c, s);
						paper[s - 1]++;
					}
				}
			}
		} else {
			DFS(r, c + 1, cnt);
		}
	}

	private static void attach(int r, int c, int i) {
		for (int j = 0; j < i; j++) {
			for (int k = 0; k < i; k++) {
				map[r + j][c + k] = 0;
			}
		}

	}

	private static void cut(int r, int c, int i) {
		for (int j = 0; j < i; j++) {
			for (int k = 0; k < i; k++) {
				map[r + j][c + k] = 1;
			}
		}
	}

	private static boolean check(int r, int c, int i) {
		if (r + i > 10 || c + i > 10)
			return false;
		for (int j = 0; j < i; j++) {
			for (int k = 0; k < i; k++) {
				if (map[r + j][c + k] == 0) {
					return false;
				}
			}
		}
		return true;
	}
}
