package M1201;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 탈출 {
	static int R, C;
	static char map[][];
	static boolean visited[][];
	static Queue<Point> Forest;
	static int ans;
	static int dir[][] = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		map = new char[R][C];
		visited = new boolean[R][C];
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

		if (ans == -1) {
			System.out.println("KAKTUS");
		} else {
			System.out.println(ans);
		}
	}

	private static void BFS(Point hedgehog) {
		Forest.add(hedgehog);
		visited[hedgehog.r][hedgehog.c] = true; // 시작 위치 체크

		while (!Forest.isEmpty()) {
			Point temp = Forest.poll();
			int r = temp.r;
			int c = temp.c;
			int dep = temp.d;
			boolean water = temp.water;

			for (int d = 0; d < dir.length; d++) {
				int nr = r + dir[d][0];
				int nc = c + dir[d][1];
				if (isOK(nr, nc)) {
					if (water) {
						if (map[nr][nc] == '.' || map[nr][nc] == 'S') {
							map[nr][nc] = '*'; // 인접해있으면 물이 넘친다
							Forest.offer(new Point(nr, nc, dep + 1, true));
							visited[nr][nc] = true;
						}
					} else {
						if (map[nr][nc] == 'D') { // 도착한다면
							ans = dep + 1;
							return;
						} else if (map[nr][nc] == '.') {
							map[nr][nc] = 'S'; // 빈 곳으로 이동
							Forest.offer(new Point(nr, nc, dep + 1, false));
							visited[nr][nc] = true;
						}
					}
				}
			}
		}
		return;
	}

	private static boolean isOK(int nr, int nc) {
		return nr >= 0 && nr < R && nc >= 0 && nc < C && !visited[nr][nc];
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
