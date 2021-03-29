package M0328;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 틱택토 {
	static char map[][];
	static int x, o, remain;
	static boolean flag;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		while (true) {
			String line = br.readLine();
			if (line.equals("end")) {
				break;
			}
			x = 0;
			o = 0;
			remain = 0;
			int idx = 0;
			map = new char[3][3];
			flag = true;
			for (int r = 0; r < 3; r++) {
				for (int c = 0; c < 3; c++) {
					map[r][c] = line.charAt(idx++);
					if (map[r][c] == 'O')
						o++;
					else if (map[r][c] == 'X')
						x++;
					else
						remain++;
				}
			}

			if (remain == 0) { // 남아있는게 없을때
				if (x - 1 != o) {
					sb.append("invalid").append("\n");
					continue;
				}
				int result = check('O');
				if (result > 0) {
					sb.append("invalid").append("\n");
					continue;
				}
			} else if (remain % 2 == 0 && remain != 0) { // X가 O보다 1개 더 많아야 하며 X가 이겨야 한다
				if (x - 1 != o) {
					sb.append("invalid").append("\n");
					continue;
				}
				int result = check('O');
				if (result > 0) {
					sb.append("invalid").append("\n");
					continue;
				}
				result = check('X');
				if (result == 0) {
					sb.append("invalid").append("\n");
					continue;
				}
			} else { // X와 O의 개수가 같아야 하며 O가 이겨야 한다
				if (x != o) {
					sb.append("invalid").append("\n");
					continue;
				}
				int result = check('X');
				if (result > 0) {
					sb.append("invalid").append("\n");
					continue;
				}
				result = check('O');
				if (result == 0) {
					sb.append("invalid").append("\n");
					continue;
				}
			}
			sb.append("valid").append("\n");
		}
		System.out.println(sb);
	}

	private static int check(char c) {
		int cnt = 0;
		for (int i = 0; i < 3; i++) {
			if (map[i][0] == map[i][1] && map[i][1] == map[i][2]) {
				if (map[i][0] == c)
					cnt++;
			}
			if (map[0][i] == map[1][i] && map[1][i] == map[2][i]) {
				if (map[0][i] == c)
					cnt++;
			}
		}
		if (map[0][0] == map[1][1] && map[1][1] == map[2][2]) {
			if (map[1][1] == c)
				cnt++;
		}
		if (map[0][2] == map[1][1] && map[1][1] == map[2][0]) {
			if (map[1][1] == c)
				cnt++;
		}
		return cnt;
	}
}
