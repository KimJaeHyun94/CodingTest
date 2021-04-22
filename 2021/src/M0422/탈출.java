package M0422;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 탈출 {
	static int R, C;
	static char map[][];
	static Queue<Point> Forest;
	static int ans;
	static int dirs[][] = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		map = new char[R][C];
		ans = -1;
		Forest = new LinkedList<>();

		Point Hedgehog = null; // 고슴도치의 시작 위치

		for (int i = 0; i < R; i++) {
			String line = br.readLine();
			for (int j = 0; j < C; j++) {
				map[i][j] = line.charAt(j);
				if (map[i][j] == 'S') {
					Hedgehog = new Point(i, j, 0, false); // 고슴도치의 시작 위치를 기억해둔다.
				} else if (map[i][j] == '*') {
					Forest.add(new Point(i, j, 0, true)); // 홍수를 미리 체크해둔다
				}
			}
		}

		BFS(Hedgehog);
		System.out.println("KAKTUS");
	}

	private static void BFS(Point hedgehog) {
		Forest.add(hedgehog);

		while (!Forest.isEmpty()) {
			Point cur = Forest.poll();

			int r = cur.r;
			int c = cur.c;
			int dep = cur.d;
			boolean water = cur.water;

			for (int d = 0; d < dirs.length; d++) {
				int nr = r + dirs[d][0];
				int nc = c + dirs[d][1];

				if (isOK(nr, nc)) {
					if (water) {
						if (map[nr][nc] == '.' || map[nr][nc] == 'S') {
							map[nr][nc] = '*';
							Forest.add(new Point(nr, nc, dep + 1, true));
						}
					} else {

						if (map[nr][nc] == 'D') {
							System.out.println(dep + 1);
							System.exit(0);
						} else if (map[nr][nc] == '.') {
							map[nr][nc] = 'S';
							Forest.add(new Point(nr, nc, dep + 1, false));
						}
					}

				}
			}
		}
		return;
	}

	private static boolean isOK(int nr, int nc) {
		return nr >= 0 && nr < R && nc >= 0 && nc < C;
	}

	static class Point {
		int r; // 행
		int c; // 열
		int d; // 거리
		boolean water; // 홍수 여부

		public Point(int r, int c, int d, boolean water) {

			this.r = r;
			this.c = c;
			this.d = d;
			this.water = water;
		}

	}
}
