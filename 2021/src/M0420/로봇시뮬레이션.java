package M0420;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 로봇시뮬레이션 {
	static int R, C, N, M;
	static int[][] map;
	static int[][] dirs = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };
	static Node robot[];
	static int idx;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		C = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());

		map = new int[R + 1][C + 1];

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		robot = new Node[N + 1];

		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			int c = Integer.parseInt(st.nextToken());
			int r = R-Integer.parseInt(st.nextToken())+1;

			char dir = st.nextToken().charAt(0);
			int d = findDir(dir);

			robot[i] = new Node(r, c, d);
			map[r][c] = i;
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			char command = st.nextToken().charAt(0);
			int cnt = Integer.parseInt(st.nextToken());
			idx = i;
			if (command == 'L') {
				turnLeft(r, cnt);
			} else if (command == 'R') {
				turnRight(r, cnt);
			} else {
				Go(r, cnt);
			}
		}
		System.out.println("OK");
	}

	private static void Go(int ro, int cnt) {
		int dir = robot[ro].d;
		int r = robot[ro].r;
		int c = robot[ro].c;

		int dr = r;
		int dc = c;
		
		for (int i = 0; i < cnt; i++) {
			dr += dirs[dir][0];
			dc += dirs[dir][1];

			if (!isOK(dr, dc)) {
				System.out.println("Robot " + ro + " crashes into the wall");
				System.exit(0);
			} else if (map[dr][dc] > 0) {
				System.out.println("Robot " + ro + " crashes into robot " + map[dr][dc]);
				System.exit(0);
			}
		}
		map[r][c] = 0;
		map[dr][dc] = ro;
		robot[ro].r = dr;
		robot[ro].c = dc;
	}

	private static boolean isOK(int dr, int dc) {
		return dr >= 1 & dr <= R && dc >= 1 && dc <= C;
	}

	private static void turnLeft(int r, int cnt) {
		int dir = robot[r].d;

		for (int i = 0; i < cnt; i++) {
			dir = (dir+3)%4;
		}
		robot[r].d = dir;
	}

	private static void turnRight(int r, int cnt) {
		int dir = robot[r].d;

		for (int i = 0; i < cnt; i++) {
			dir = (dir+1)%4;
		}
		robot[r].d = dir;
	}

	private static int findDir(char dir) {
		switch (dir) {
		case 'N':
			return 0;
		case 'E':
			return 1;
		case 'S':
			return 2;
		case 'W':
			return 3;
		}
		return -1;
	}

	static class Node {
		int r;
		int c;
		int d;

		public Node(int r, int c, int d) {
			this.r = r;
			this.c = c;
			this.d = d;
		}
	}
}
