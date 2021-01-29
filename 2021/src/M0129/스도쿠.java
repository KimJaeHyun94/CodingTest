package M0129;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class 스도쿠 {
	static int sdoku[][];
	static ArrayList<int[]> list;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		sdoku = new int[9][9];
		list = new ArrayList<>();

		for (int i = 0; i < 9; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 9; j++) {
				sdoku[i][j] = Integer.parseInt(st.nextToken());

				if (sdoku[i][j] == 0) {
					list.add(new int[] { i, j }); // 0인것들을 넣어준다
				}
			}
		}

		DFS(0, 0);

	}

	private static void DFS(int idx, int n) {
		if (n == list.size()) {
			End();
		}

		int r = list.get(idx)[0];
		int c = list.get(idx)[1]; // 저장해놓았던 0인 위치들을 불러온다.

		for (int i = 1; i <= 9; i++) { // 1부터 9까지 그 자리에 넣어준다
			if (sol(r, c, i)) {
				sdoku[r][c] = i;
				DFS(idx + 1, n + 1);
				sdoku[r][c] = 0;
			}
		}
	}

	private static void End() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				sb.append(sdoku[i][j] + " ");
			}
			sb.append("\n");
		}
		System.out.println(sb);
		System.exit(0);
	}

	private static boolean sol(int r, int c, int s) {
		for (int i = 0; i < 9; i++) { // 세로에 같은 숫자가 있으면 false
			if (sdoku[r][i] == s)
				return false;
		}

		for (int i = 0; i < 9; i++) { // 가로에 같은 숫자가 있으면 false
			if (sdoku[i][c] == s)
				return false;
		}

		int dr = r / 3 * 3; // 상자 안의 범위
		int dc = c / 3 * 3;

		for (int i = dr; i < dr + 3; i++) {
			for (int j = dc; j < dc + 3; j++) {
				if (sdoku[i][j] == s) {
					return false;
				}
			}
		}

		return true;
	}
}
