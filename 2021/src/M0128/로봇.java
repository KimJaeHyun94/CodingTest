package M0128;

import java.util.*;
import java.io.*;

public class 로봇 {
	static int[][] dirs = { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 } };
	static int[][] map;
	static int R, C;
	static Robot person;
	static int ansr, ansc, ansd;
	static int ans;
	static boolean visited[][][];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new int[R][C];
		visited = new boolean[R][C][4];
		for (int i = 0; i < map.length; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < map[i].length; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		st = new StringTokenizer(br.readLine());
		person = new Robot(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1,
				Integer.parseInt(st.nextToken()) - 1, 0); // 출발 지점

		st = new StringTokenizer(br.readLine()); // 정답
		ansr = Integer.parseInt(st.nextToken()) - 1;
		ansc = Integer.parseInt(st.nextToken()) - 1;
		ansd = Integer.parseInt(st.nextToken()) - 1;

		BFS();
		System.out.println(ans);
	}

	private static void BFS() {
		Queue<Robot> q = new LinkedList<>();
		q.add(person);
		visited[person.r][person.c][person.dir] = true; // 첫 출발지점 체크

		while (!q.isEmpty()) {
			Robot temp = q.poll();
			int r = temp.r;
			int c = temp.c;
			int dir = temp.dir;
			int dep = temp.dep;

			if (r == ansr && c == ansc && dir == ansd) { // 도달하면
				ans = dep;
				return;
			}
			for (int k = 1; k <= 3; k++) { // 명령 1. Go k: k는 1, 2 또는 3일 수 있다.
				int dr = r + dirs[dir][0] * k;
				int dc = c + dirs[dir][1] * k;
				if (isOK(dr, dc) && map[dr][dc] == 0) {
					if (!visited[dr][dc][dir]) {
						q.add(new Robot(dr, dc, dir, dep + 1));
						visited[dr][dc][dir] = true;
					}
				} else
					break;
			}

			int left = turnLeft(dir); // 왼쪽으로 돌리기
			if (!visited[r][c][left]) {
				visited[r][c][left] = true;
				q.add(new Robot(r, c, left, dep + 1));
			}

			int right = turnRight(dir); // 오른쪽으로 돌리기
			if (!visited[r][c][right]) {
				visited[r][c][right] = true;
				q.add(new Robot(r, c, right, dep + 1));
			}
		}
	}

	private static int turnLeft(int dir) {
		if (dir == 0) {
			return 3;
		}
		if (dir == 1) {
			return 2;
		}
		if (dir == 2) {
			return 0;
		}
		if (dir == 3) {
			return 1;
		}
		return -1;
	}

	private static int turnRight(int dir) {
		if (dir == 0) {
			return 2;
		}
		if (dir == 1) {
			return 3;
		}
		if (dir == 2) {
			return 1;
		}
		if (dir == 3) {
			return 0;
		}
		return -1;
	}

	private static boolean isOK(int dr, int dc) {
		return dr >= 0 && dr < R && dc >= 0 && dc < C;
	}

	static class Robot {
		int r;
		int c;
		int dir;
		int dep;

		public Robot(int r, int c, int dir, int dep) {
			this.r = r;
			this.c = c;
			this.dir = dir;
			this.dep = dep;
		}
	}
}
