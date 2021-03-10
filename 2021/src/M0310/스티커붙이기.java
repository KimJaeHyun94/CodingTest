package M0310;

import java.util.*;
import java.io.*;

public class 스티커붙이기 {
	static int[][] map;
	static int cnt;
	static int N, M, K;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		map = new int[N][M];

		for (int k = 0; k < K; k++) {
			st = new StringTokenizer(br.readLine());
			int R = Integer.parseInt(st.nextToken());
			int C = Integer.parseInt(st.nextToken());
			cnt = 0;
			int[][] sticker = new int[R][C];
			for (int r = 0; r < R; r++) {
				st = new StringTokenizer(br.readLine());
				for (int c = 0; c < C; c++) {
					sticker[r][c] = Integer.parseInt(st.nextToken());
				}
			}

			outer: while (cnt <= 3) {
				for (int r = 0; r < N; r++) {
					for (int c = 0; c < M; c++) {
						if (Attach(r, c, sticker)) {
							break outer;
						}
					}
				}
				cnt++; // 1번 회전
				sticker = rotate(sticker);
			}
		}

		int ans = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] == 1) {
					ans++;
				}
			}
		}
		System.out.println(ans);
	}

	private static boolean Attach(int r, int c, int[][] sticker) {
		int R = sticker.length;
		int C = sticker[0].length;
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (isOK(i + r, j + c)) {

					if (map[i + r][j + c] == 0)
						continue;
					else if (map[i + r][j + c] == 1 && sticker[i][j] == 0)
						continue;
					else
						return false;
				} else {
					return false;
				}
			}
		}
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (sticker[i][j] == 1) {
					map[i + r][j + c] = 1;
				}
			}
		}
		return true;
	}

	private static int[][] rotate(int[][] arr) {
		int n = arr.length;
		int m = arr[0].length;
		int[][] rotate = new int[m][n];

		for (int i = 0; i < rotate.length; i++) {
			for (int j = 0; j < rotate[i].length; j++) {
				rotate[i][j] = arr[n - 1 - j][i];
			}
		}
		return rotate;
	}

	private static boolean isOK(int i, int j) {
		return i >= 0 && i < N && j >= 0 && j < M;
	}
}
