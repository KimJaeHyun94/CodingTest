package M0313;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class 거울설치 {
	static int dirs[][] = { { -1, 0 }, { 1, 0 }, { 0, 1 }, { 0, -1 } };
	static int N;
	static char map[][];
	static int sr = -1, sc = -1, er = -1, ec = -1;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new char[N][N];

		for (int r = 0; r < N; r++) {
			String str = br.readLine();
			for (int c = 0; c < N; c++) {
				map[r][c] = str.charAt(c);
				if (map[r][c] == '#') {
					if (sr == -1) {
						sr = r;
						sc = c;
					} else {
						er = r;
						ec = c;
					}
				}
			}
		}
		BFS();

	}

	private static void BFS() {
		PriorityQueue<Mirror> q = new PriorityQueue<>();
		boolean visited[][][] = new boolean[N][N][4];

		for (int d = 0; d < dirs.length; d++) {
			int dr = sr + dirs[d][0];
			int dc = sc + dirs[d][1];
			if (isOK(dr, dc) && map[dr][dc] != '*') {
				q.add(new Mirror(sr, sc, d, 0));
			}
		}

		while (!q.isEmpty()) {
			Mirror cur = q.poll();
			int r = cur.r;
			int c = cur.c;
			int d = cur.d;
			int cnt = cur.cnt;
			
			if(visited[r][c][d]) continue;
			visited[r][c][d] = true;
			if (r == er && c == ec) {
				System.out.println(cnt);
				System.exit(0);
			}
			int dr = r + dirs[d][0];
			int dc = c + dirs[d][1];

			if (!isOK(dr, dc) ||map[dr][dc] == '*')
				continue;
			if (map[dr][dc] == '!') { // 거울 설치
				q.add(new Mirror(dr, dc, turnDir(d), cnt + 1));
				q.add(new Mirror(dr, dc, turnDir2(d), cnt + 1));
			}
			q.add(new Mirror(dr, dc, d, cnt));
		}
		return;

	}

	private static int turnDir(int d) { // \방향 돌리기 위, 아래 , 오, 왼
		switch (d) {
		case 0:
			return 3;
		case 1:
			return 2;
		case 2:
			return 1;
		default:
			return 0;
		}
	}

	private static int turnDir2(int d) { // /방향 돌리기
		switch (d) {
		case 0:
			return 2;
		case 1:
			return 3;
		case 2:
			return 0;
		default:
			return 1;
		}
	}

	private static boolean isOK(int dr, int dc) {
		return dr >= 0 && dr < N && dc >= 0 && dc < N;
	}

	static class Mirror implements Comparable<Mirror> {
		int r;
		int c;
		int d;
		int cnt;

		public Mirror(int r, int c, int d, int cnt) {
			this.r = r;
			this.c = c;
			this.d = d;
			this.cnt = cnt;
		}

		@Override
		public int compareTo(Mirror o) {

			return this.cnt - o.cnt;
		}

	}
}
