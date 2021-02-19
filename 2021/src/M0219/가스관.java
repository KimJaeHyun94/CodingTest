package M0219;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 가스관 {
	static int dirs[][] = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } }; // 위, 아래, 왼쪽, 오른쪽
	static int R, C;
	static char map[][];
	static int startR, startC, finalR, finalC;
	static int ansR, ansC;
	static char ans;
	static int Mdir, Zdir;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		map = new char[R][C];

		for (int r = 0; r < R; r++) {
			String line = br.readLine();
			for (int c = 0; c < C; c++) {
				map[r][c] = line.charAt(c);
				if (map[r][c] == 'M') {
					startR = r;
					startC = c;
				} else if (map[r][c] == 'Z') {
					finalR = r;
					finalC = c;
				}
			}
		}
		BFS();
		System.out.println((ansR + 1) + " " + (ansC + 1) + " " + ans);
	}

	private static void BFS() {
		Queue<block> q = new LinkedList<>();
		for (int d = 0; d < dirs.length; d++) {
			int dr = startR + dirs[d][0];
			int dc = startC + dirs[d][1];

			if (isOK(dr, dc) && map[dr][dc] != '.') {
				int dir = turnDir(d, map[dr][dc]);
				if(dir!=-1)
				q.add(new block(dr, dc, dir));
			}
		}

		while (!q.isEmpty()) {
			block temp = q.poll();
			int r = temp.r;
			int c = temp.c;
			int dir = temp.dir;

			int dr = r + dirs[dir][0];
			int dc = c + dirs[dir][1];

			if (isOK(dr, dc)) {
				if (map[dr][dc] != '.') {
					int newdir = turnDir(dir, map[dr][dc]);
					if(newdir!=-1)
					q.add(new block(dr, dc, newdir));
				} else {
					ansR = dr;
					ansC = dc;
					Mdir = op(dir);
					BFS2();
					return;
				}
			}
		}
	}

	private static void BFS2() {
		Queue<block> q = new LinkedList<>();
		for (int d = 0; d < dirs.length; d++) {
			int dr = finalR + dirs[d][0];
			int dc = finalC + dirs[d][1];

			if (isOK(dr, dc) && map[dr][dc] != '.') {
				int dir = turnDir(d, map[dr][dc]);
				if(dir!=-1)
				q.add(new block(dr, dc, dir));
			}
		}

		while (!q.isEmpty()) {
			block temp = q.poll();
			int r = temp.r;
			int c = temp.c;
			int dir = temp.dir;

			int dr = r + dirs[dir][0];
			int dc = c + dirs[dir][1];

			if (isOK(dr, dc)) {
				if (map[dr][dc] != '.') {
					int newdir = turnDir(dir, map[dr][dc]);
					if(newdir!=-1)
					q.add(new block(dr, dc, newdir));
				} else {
					Zdir = op(dir);
					findshape();
					return;
				}
			}
		}
	}

	private static void findshape() {
		int cnt = 0;
		boolean v[] = new boolean[4];
		for (int d = 0; d < dirs.length; d++) {
			int dr = ansR + dirs[d][0];
			int dc = ansC + dirs[d][1];

			if (isOK(dr, dc) && map[dr][dc] != '.' && map[dr][dc] != 'Z' && map[dr][dc] != 'M') {
				cnt++;
			}
		}
		if (cnt == 4) {
			ans = '+';
		} else {
			v[Zdir] = true;
			v[Mdir] = true;
			ans = check(v);
		}
	}

	private static char check(boolean[] v) { // 위, 아래, 왼쪽, 오른쪽
		if (v[0] && v[1]) {
			return '|';
		} else if (v[2] && v[3]) {
			return '-';
		} else if (v[1] && v[3]) {
			return '1';
		} else if (v[0] && v[3]) {
			return '2';
		} else if (v[0] && v[2]) {
			return '3';
		} else {
			return '4';
		}

	}

	private static int op(int dir) {
		switch (dir) {
		case 0:
			return 1;
		case 1:
			return 0;
		case 2:
			return 3;
		case 3:
			return 2;
		}
		return -1;
	}

	private static int turnDir(int d, char ch) { // 위, 아래, 왼쪽, 오른쪽
		switch (ch) {
		case '|':
			if(d==0 || d==1)
			return d;
		case '-':
			if(d==2 || d==3)
			return d;
		case '+':
			return d;
		case '1':
			if (d == 2)
				return 1;
			else
				return 3;
		case '2':
			if (d == 1)
				return 3;
			else
				return 0;
		case '3':
			if (d == 1)
				return 2;
			else
				return 0;
		case '4':
			if (d == 3)
				return 1;
			else
				return 2;
		}
		return -1;
	}

	private static boolean isOK(int dr, int dc) {
		return dr >= 0 && dr < R && dc >= 0 && dc < C;
	}

	static class block {
		int r;
		int c;
		int dir;

		public block(int r, int c, int dir) {
			this.r = r;
			this.c = c;
			this.dir = dir;
		}
	}
}
