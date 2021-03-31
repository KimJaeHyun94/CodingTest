package M0331;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 소코반 {
	static int dirs[][] = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
	static int R, C;
	static char map[][];
	static char Route[];
	static int sr, sc;
	static int object;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int idx = 1;
		while (true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());

			if (R == 0 && C == 0)
				break;

			map = new char[R][C];
			object = 0;

			for (int r = 0; r < R; r++) {
				String line = br.readLine();
				for (int c = 0; c < C; c++) {
					map[r][c] = line.charAt(c);
					if (map[r][c] == 'w' || map[r][c] == 'W') {
						sr = r;
						sc = c;
					} else if (map[r][c] == 'b' || map[r][c]=='B') {
						object++;
					}
				}
			}

			String line = br.readLine();
			Route = line.toCharArray();

			if (Solution()) {
				sb.append("Game ").append(idx++).append(": complete").append("\n");
				for (int r = 0; r < R; r++) {
					for (int c = 0; c < C; c++) {
						sb.append(map[r][c]);
					}
					sb.append("\n");
				}
			} else {
				sb.append("Game ").append(idx++).append(": incomplete").append("\n");
				for (int r = 0; r < R; r++) {
					for (int c = 0; c < C; c++) {
						sb.append(map[r][c]);
					}
					sb.append("\n");
				}
			}

		}
		System.out.println(sb);
	}

	private static boolean Solution() {
		int r = sr;
		int c = sc;

		for (int i = 0; i < Route.length; i++) {

			int dir = FindDir(Route[i]);
			if (check())
				return true;

			int dr = r + dirs[dir][0];
			int dc = c + dirs[dir][1];

			if (map[dr][dc] == '#') {
				continue; // 벽이라면 못간다.
			} else if (map[dr][dc] == '.') {
				map[dr][dc] = 'w';
				if (map[r][c] == 'w') {
					map[r][c] = '.';
				} else {
					map[r][c] = '+';
				}
			} else if (map[dr][dc] == '+') {
				if (map[r][c] == 'w') {
					map[r][c] = '.';
				} else {
					map[r][c] = '+';
				}
				map[dr][dc] = 'W';
			} else if (map[dr][dc] == 'b') { // 박스가 이동할 칸에 다른 박스나 벽이 있는 경우에는 키를 눌러도 캐릭터는 이동하지 않는다.
				int ddr = dr + dirs[dir][0];
				int ddc = dc + dirs[dir][1];

				if (map[ddr][ddc] == '#' || map[ddr][ddc] == 'B' || map[ddr][ddc] == 'b') {
					continue;
				} else {
					if (map[ddr][ddc] == '+') {
						if (map[r][c] == 'w') {
							map[r][c] = '.';
						} else {
							map[r][c] = '+';
						}
						map[dr][dc] = 'w';
						map[ddr][ddc] = 'B';
					} else if (map[ddr][ddc] == '.') {
						if (map[r][c] == 'w') {
							map[r][c] = '.';
						} else {
							map[r][c] = '+';
						}
						map[dr][dc] = 'w';
						map[ddr][ddc] = 'b';
					}
				}

			} else if (map[dr][dc] == 'B') {
				int ddr = dr + dirs[dir][0];
				int ddc = dc + dirs[dir][1];

				if (map[ddr][ddc] == '#' || map[ddr][ddc] == 'B' || map[ddr][ddc] == 'b') {
					continue;
				} else {
					if (map[ddr][ddc] == '+') {
						if (map[r][c] == 'w') {
							map[r][c] = '.';
						} else {
							map[r][c] = '+';
						}
						map[dr][dc] = 'W';
						map[ddr][ddc] = 'B';
					} else if (map[ddr][ddc] == '.') {
						if (map[r][c] == 'w') {
							map[r][c] = '.';
						} else {
							map[r][c] = '+';
						}
						map[dr][dc] = 'W';
						map[ddr][ddc] = 'b';
					}
				}
			}
			r = dr;
			c = dc;
		}

		if (!check())
			return false;
		else
			return true;
	}

	private static boolean check() {
		int cnt = 0;
		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				if (map[r][c] == 'B') {
					cnt++;
				}
			}
		}
		if (cnt == object) {
			return true;
		} else
			return false;
	}

	private static int FindDir(char d) {
		switch (d) {
		case 'U':
			return 0;
		case 'D':
			return 1;
		case 'L':
			return 2;
		case 'R':
			return 3;
		}
		return -1;
	}

}
