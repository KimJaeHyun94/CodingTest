package M0225;

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
	static int Mdir=0, Zdir=0;

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
		Mdir = BFS(startR, startC); // M에서 시작했을 때의 방향 체크
		Zdir = BFS(finalR, finalC); // Z에서 시작했을 때의 방향
		findShape();
		System.out.println((ansR + 1) + " " + (ansC + 1) + " " + ans);

	}

	private static void findShape() {
		int cnt = 0;
		boolean visited[] = new boolean[4];
		for (int d = 0; d < dirs.length; d++) {
			int dr = ansR + dirs[d][0];
			int dc = ansC + dirs[d][1];

			if (isOK(dr, dc) && map[dr][dc] != '.' && map[dr][dc] != 'Z' && map[dr][dc] != 'M') {
				cnt++;
			}
		}

		if (cnt == 4) { // 구해놓은 방향 말고도 모든 방향이 뚫여잇으므로 +밖에 답이 될 수 없다.
			ans = '+';
			return;
		} else {
			visited[Zdir] = true;
			visited[Mdir] = true;
			ans = check(visited); // 각각의 방향을 통해 모양을 얻어낸다.
		}
	}

	private static int BFS(int sr, int sc) {
		Queue<block> q = new LinkedList<>();
		for (int d = 0; d < dirs.length; d++) { // 처음에 M으로 시작한 곳에서 4군데를 다 접근한다.
			int dr = sr + dirs[d][0];
			int dc = sc + dirs[d][1];

			if (isOK(dr, dc) && map[dr][dc] != '.') { // 접근 가능한 곳이면
				int dir = turnDir(d, map[dr][dc]); // 방향 돌리기를 통해 방향을 정한다.
				if (dir != -1)
					q.add(new block(dr, dc, dir)); // 그 방향으로 이동한다.
			}
		}

		while (!q.isEmpty()) {
			block temp = q.poll();
			int r = temp.r;
			int c = temp.c;
			int dir = temp.dir; // 이 방향대로 이제 움직인다.

			int dr = r + dirs[dir][0];
			int dc = c + dirs[dir][1];

			if (isOK(dr, dc)) { // 이동 가능 하다면
				if (map[dr][dc] != '.') { // 끊어지지 않았다면
					int newDir = turnDir(dir, map[dr][dc]); // 그 모양에서의 방향 전환
					if (newDir != -1) // 그 방향이 정상적이라면
						q.add(new block(dr, dc, newDir));
				} else { // 만약에 끊어졋다면
					ansR = dr;
					ansC = dc; // 끊어진 위치를 저장해둔다.
					return op(dir); // 끊어진 곳에서 반대방향이 가스관의 방향이므로
				}
			}
		}
		return -1;
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
			if (d == 0 || d == 1)
				return d;
		case '-':
			if (d == 2 || d == 3)
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
