package M0510;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 상어초등학교 {
	static int N;
	static int dirs[][] = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
	static int[] score = { 0, 1, 10, 100, 1000 };
	static int map[][];
	static int stu[][];

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		stu = new int[N * N + 1][4];

		for (int i = 0; i < N * N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int num = Integer.parseInt(st.nextToken());
			for (int j = 0; j < 4; j++) {
				stu[num][j] = Integer.parseInt(st.nextToken());
			}
			check(num);
		}

		int sum = 0;

		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				int num = map[r][c];
				int tmp = 0;

				for (int d = 0; d < dirs.length; d++) {
					int dr = r + dirs[d][0];
					int dc = c + dirs[d][1];

					if (!isOK(dr, dc))
						continue;

					for (int i = 0; i < 4; i++) {
						if (map[dr][dc] == stu[num][i]) {
							tmp++; // 주변에 친한 친구 있으면 증가
						}
					}
				}
				sum += score[tmp];
			}
		}
		System.out.println(sum);
	}

	private static void check(int num) {
		int x = -1, y = -1, a = -1;
		int cur = -1;				//아무 조건이 안될때 (N이 1일때) 최대값이 0이므로 오류가 난다! 
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				if (map[r][c] != 0)
					continue;
				a = calc(num, r, c);
				
				if (a > cur) {
					cur = a;
					x = r;
					y = c;
				}
			}
		}

		map[x][y] = num;
		return;
	}

	private static int calc(int num, int r, int c) {
		int empty = 0; // 비어있는 칸
		int favorite = 0;

		for (int d = 0; d < dirs.length; d++) {
			int dr = r + dirs[d][0];
			int dc = c + dirs[d][1];

			if (!isOK(dr, dc))
				continue;

			for (int i = 0; i < 4; i++) {
				if (map[dr][dc] == stu[num][i]) {
					favorite++; // 주변에 친한 친구 있으면 증가
				}
			}

			if (map[dr][dc] == 0)
				empty++; // 빈칸 증가
		}
		return favorite * 10 + empty; // 우선순위 부과위해 10을 곱해준다.
	}

	private static boolean isOK(int dr, int dc) {
		return dr >= 0 && dr < N && dc >= 0 && dc < N;
	}
}
