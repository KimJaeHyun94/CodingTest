package M1222;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class 스도쿠 {
	static int sdoku[][];
	static ArrayList<int[]> list;
	static boolean flag;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		sdoku = new int[9][9];
		list = new ArrayList<>();
		for (int i = 0; i < 9; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 9; j++) {
				sdoku[i][j] = Integer.parseInt(st.nextToken());
				if (sdoku[i][j] == 0)
					list.add(new int[] { i, j });
			}
		}

		DFS(0, 0);

	}

	private static void DFS(int r, int n) {
		if (n == list.size()) {
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < 9; i++) {
				for (int j = 0; j < 9; j++) {
					sb.append(sdoku[i][j] + " ");
				}
				sb.append("\n");
			}
			System.out.println(sb.toString());
			System.exit(0);

		}
		int a = list.get(r)[0];
		int b = list.get(r)[1];

		for (int i = 1; i <= 9; i++) {
			if (sdo(a, b, i)) {
				sdoku[a][b] = i;
				DFS(r + 1, n + 1);
				sdoku[a][b] = 0;
			}
		}
	}

	private static boolean sdo(int r, int c, int i) {

		for (int j = 0; j < 9; j++) {
			if (sdoku[r][j] == i) {
				return false;
			}
			if (sdoku[j][c] == i) {
				return false;
			}
		}

		int dr = r / 3 * 3;
		int dc = c / 3 * 3;

		for (int j = dr; j <= dr + 2; j++) {
			for (int k = dc; k <= dc + 2; k++) {
				if (sdoku[j][k] == i) {
					return false;
				}
			}
		}
		return true;
	}

}