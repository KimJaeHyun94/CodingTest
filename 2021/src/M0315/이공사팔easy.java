package M0315;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 이공사팔easy {
	static int N;
	static int map[][];
	static int MAX = Integer.MIN_VALUE;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		map = new int[N][N];
		for (int r = 0; r < N; r++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int c = 0; c < N; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		DFS(0);
		System.out.println(MAX);
	}

	private static void DFS(int cnt) {
		if (cnt == 5) {
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
					MAX = Math.max(MAX, map[r][c]);
				}
			}
			return;
		}
		int copymap[][] = new int[N][N];
		Copy(copymap, map);
		for (int d = 0; d < 4; d++) { // 상, 하, 좌, 우
			sol(d);
			DFS(cnt + 1);
			Copy(map, copymap);
		}
	}

	private static void sol(int d) {
		switch (d) {
		case 0:
			for (int c = 0; c < N; c++) {
				int idx = 0;
				int val = 0;
				for (int r = 0; r < N; r++) {
					if (map[r][c] != 0) {
						if (map[r][c] == val) {
							map[idx - 1][c] = val * 2;
							map[r][c] = 0;
							val = 0;
						} else {
							val = map[r][c];
							map[r][c] = 0;
							map[idx++][c] = val;
						}
					}
				}
			}
			break;
		case 1:
			for (int c = 0; c < N; c++) {
				int idx = N - 1;
				int val = 0;
				for (int r = N - 1; r >= 0; r--) {
					if (map[r][c] != 0) {
						if (map[r][c] == val) {
							map[idx + 1][c] = val * 2;
							map[r][c] = 0;
							val = 0;
						} else {
							val = map[r][c];
							map[r][c] = 0;
							map[idx--][c] = val;
						}

					}
				}
			}
			break;
		case 2:
			for (int r = 0; r < N; r++) {
				int idx = 0;
				int val = 0;
				for (int c = 0; c < N; c++) {
					if (map[r][c] != 0) {
						if (map[r][c] == val) {
							map[r][idx - 1] = val * 2;
							map[r][c] = 0;
							val = 0;
						} else {
							val = map[r][c];
							map[r][c] = 0;
							map[r][idx++] = val;
						}
					}
				}
			}
			break;
		case 3:
			for (int r = 0; r < N; r++) {
				int idx = N - 1;
				int val = 0;
				for (int c = N - 1; c >= 0; c--) {
					if (map[r][c] != 0) {
						if (map[r][c] == val) {
							map[r][idx + 1] = val * 2;
							map[r][c] = 0;
							val = 0;
						} else if (map[r][c] != 0) {
							val = map[r][c];
							map[r][c] = 0;
							map[r][idx--] = val;
						}
					}
				}
			}
			break;
		}
	}

	private static void Copy(int[][] copymap, int[][] map2) {
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				copymap[r][c] = map2[r][c];
			}
		}
	}
}
