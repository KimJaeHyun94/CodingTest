package M0411;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 마라톤틱택토 {
	static int N;
	static char map[][];
	static int dirs[][] = { { 0, 1 }, { 1, 1 }, { 1, 0 }, { 1, -1 } };

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new char[N][N];

		for (int r = 0; r < N; r++) {
			String line = br.readLine();
			for (int c = 0; c < N; c++) {
				map[r][c] = line.charAt(c);
			}
		}

		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				if (map[r][c] != '.') {
					char ch = map[r][c];
					
					for (int d = 0; d < 4; d++) {
						int cnt = 0;

						for (int i = 1; i <= 2; i++) {
							int dr = r + dirs[d][0] * i;
							int dc = c + dirs[d][1] * i;

							if (isOK(dr, dc) && map[dr][dc] == ch) {
								cnt++;
							}
						}
						if (cnt == 2) {
							System.out.println(ch);
							return;
						}
					}
				}
			}
		}
		System.out.println("ongoing");
	}

	private static boolean isOK(int dr, int dc) {
		return dr >= 0 && dr < N && dc >= 0 && dc < N;
	}
}
