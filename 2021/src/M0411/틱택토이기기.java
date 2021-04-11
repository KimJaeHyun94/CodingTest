package M0411;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 틱택토이기기 {
	static char map[][];
	static int dirs[][] = { { 0, 1 }, { 1, 0 }, { 1, 1 }, { 1, -1 } };

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		StringBuilder sb = new StringBuilder();
		for (int tc = 1; tc <= T; tc++) {
			int checkr, checkc;

			map = new char[3][3];
			for (int r = 0; r < 3; r++) {
				String line = br.readLine();
				for (int c = 0; c < 3; c++) {
					map[r][c] = line.charAt(c);
				}
			}

			char start = br.readLine().charAt(0);
			for (int r = 0; r < 3; r++) {
				boolean flag = false;
				for (int c = 0; c < 3; c++) {
					if (map[r][c] == '-') {
						map[r][c] = start;
						if (check(start)) {
							flag = true;
							break;
						}

						map[r][c] = '-';
					}
				}
				if (flag)
					break;
			}

			sb.append("Case ").append(tc).append(":").append("\n");

			for (int r = 0; r < 3; r++) {
				for (int c = 0; c < 3; c++) {
					sb.append(map[r][c]);
				}
				sb.append("\n");
			}
		}
		System.out.println(sb);
	}

	private static boolean check(char start) {
		if (map[0][0] == start && map[1][1] == start && map[2][2] == start)
			return true;
		else if (map[0][2] == start && map[1][1] == start && map[2][0] == start)
			return true;

		for (int i = 0; i < 3; i++) {
			if (map[i][0] == start && map[i][1] == start && map[i][2] == start)
				return true;
			if (map[0][i] == start && map[1][i] == start && map[2][i] == start)
				return true;
		}
		return false;
	}
}