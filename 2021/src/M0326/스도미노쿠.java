package M0326;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 스도미노쿠 {
	static int[][] sdoku;
	static boolean[][] Row;
	static boolean[][] Col;
	static boolean[][][] Square;
	static boolean[][] miro;
	static int N;
	static boolean flag;
	static StringBuilder sb;
	static int[][] dirs = { { 0, 1 }, { 1, 0 } };

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		sb = new StringBuilder();
		int tc = 0;
		while (true) {
			tc++;
			N = Integer.parseInt(br.readLine());
			if (N == 0)
				break;
			Reset();

			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());

				int num = Integer.parseInt(st.nextToken());

				String LU = st.nextToken();
				int r = LU.charAt(0) - 'A';
				int c = LU.charAt(1) - '0' - 1;
				Row[r][num] = true;
				Col[c][num] = true;
				sdoku[r][c] = num;
				Square[r / 3][c / 3][num] = true;

				int num2 = Integer.parseInt(st.nextToken());
				String LV = st.nextToken();
				int r2 = LV.charAt(0) - 'A';
				int c2 = LV.charAt(1) - '0' - 1;
				Row[r2][num2] = true;
				Col[c2][num2] = true;
				sdoku[r2][c2] = num2;
				Square[r2 / 3][c2 / 3][num2] = true;

				miro[num][num2] = true;
				miro[num2][num] = true; // 해당 타일을 사용
			}
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= 9; i++) {
				String line = st.nextToken();
				int r = line.charAt(0) - 'A';
				int c = line.charAt(1) - '0' - 1;

				Row[r][i] = true;
				Col[c][i] = true;
				sdoku[r][c] = i;
				Square[r / 3][c / 3][i] = true;
			}
			sb.append("Puzzle ").append(tc).append("\n");
			DFS(0, 0);
		}
		System.out.println(sb);
	}

	private static void DFS(int r, int c) {
		if (flag)
			return;
		
		if (r == 8 && c == 8) {
			flag = true;
			for (int i = 0; i < 9; i++) {
				for (int j = 0; j < 9; j++) {
					sb.append(sdoku[i][j]);
				}
				sb.append("\n");
			}
			return;
		}
		if (c >= 9) {
			c = 0;
			r += 1;
		}
		if (sdoku[r][c] == 0) { // 숫자를 못 채워넣었으면
			for (int d = 0; d <2; d++) { // 가로와 세로가 가능한지
				int dr = r + dirs[d][0];
				int dc = c + dirs[d][1];

				if (!isOK(dr, dc) || sdoku[dr][dc]!=0)
					continue;

				for (int i = 1; i < 9; i++) {
					for (int j = i + 1; j <= 9; j++) {
						if (miro[i][j])
							continue;

						miro[i][j] = true;
						miro[j][i] = true; // 타일을 사용한다.

						if (isPossible(r, c, i, j, dr, dc)) {
							Sol(r, c, i, j, dr, dc);
						}

						if (isPossible(r, c, j, i, dr, dc)) {
							Sol(r, c, j, i, dr, dc);
						}

						miro[i][j] = false;
						miro[j][i] = false;
					}
				}
			}
		} else
			DFS(r, c + 1);

		return;
	}

	private static void Sol(int r, int c, int i, int j, int dr, int dc) {
		Row[r][i] = Row[dr][j] = true;
		Col[c][i] = Col[dc][j] = true;
		Square[r / 3][c / 3][i] = Square[dr / 3][dc / 3][j] = true;

		sdoku[r][c] = i;
		sdoku[dr][dc] = j;
		DFS(r, c + 1);

		Row[r][i] = Row[dr][j] = false;
		Col[c][i] = Col[dc][j] = false;
		Square[r / 3][c / 3][i] = Square[dr / 3][dc / 3][j] = false;

		sdoku[r][c] = 0;
		sdoku[dr][dc] = 0;
	}

	private static boolean isPossible(int r, int c, int i, int j, int dr, int dc) {
		if (!Row[r][i] && !Col[c][i] && !Square[r / 3][c / 3][i]) {
			if (!Row[dr][j] && !Col[dc][j] && !Square[dr / 3][dc / 3][j]) {
				return true;
			}
		}
		return false;
	}

	private static boolean isOK(int dr, int dc) {
		return dr >= 0 && dr < 9 && dc >= 0 && dc < 9;
	}

	private static void Reset() {
		sdoku = new int[9][9]; // 위치 표현
		Row = new boolean[9][10]; // 열의 개수/숫자 사용
		Col = new boolean[9][10]; // 행의 개수/숫자사용
		Square = new boolean[3][3][10]; // 네모 개수/ / 숫자 사용
		miro = new boolean[10][10]; // 숫자 사용
		flag = false;
	}
}
