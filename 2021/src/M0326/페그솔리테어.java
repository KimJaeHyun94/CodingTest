package M0326;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 페그솔리테어 {
	static char map[][];
	static int dirs[][] = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
	static int move;
	static int cnt;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int t = 0; t < TC; t++) {
			map = new char[5][9];
			move = Integer.MAX_VALUE;
			cnt = Integer.MAX_VALUE;
			for (int r = 0; r < 5; r++) {
				String line = br.readLine();
				for (int c = 0; c < 9; c++) {
					map[r][c] = line.charAt(c);
				}
			}
			DFS(0);
			if (t != TC - 1) {
				br.readLine();
			}
			sb.append(cnt).append(" ").append(move).append("\n");

		}
		System.out.println(sb);
	}

	private static void DFS(int depth) {
		for (int r = 0; r < 5; r++) {
			for (int c = 0; c < 9; c++) {
				if (map[r][c] == 'o') { // 핀이 있다면
					for (int d = 0; d < dirs.length; d++) {
						int dr = r + dirs[d][0];
						int dc = c + dirs[d][1];

						if (isOK(dr, dc) && map[dr][dc] == 'o') {
							int ddr = dr + dirs[d][0];
							int ddc = dc + dirs[d][1];

							if (isOK(ddr, ddc) && map[ddr][ddc] == '.') {// 넘길 수 있다면
								map[r][c] = '.';
								map[dr][dc] = '.';
								map[ddr][ddc] = 'o';
								DFS(depth + 1);
								map[r][c] = 'o';
								map[dr][dc] = 'o';
								map[ddr][ddc] = '.';
							}
						}
					}
				}
			}
		}

		if (cnt > check()) {
			cnt = check();
			move = depth;
		}
		return;
	}

	private static int check() {
		int num = 0;
		for (int r = 0; r < 5; r++) {
			for (int c = 0; c < 9; c++) {
				if (map[r][c] == 'o') {
					num++;
				}
			}
		}
		return num;
	}

	private static boolean isOK(int dr, int dc) {
		return dr >= 0 && dr < 5 && dc >= 0 && dc < 9;
	}

}
