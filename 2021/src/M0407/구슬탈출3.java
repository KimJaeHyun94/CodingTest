package M0407;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 구슬탈출3 {
	static int R, C;
	static char map[][];
	static int cnt;
	static boolean flag;
	static int dirs[][] = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
	static int rx, ry, bx, by, hx, hy;
	static boolean visited[][][][];
	static Queue<Point> blue;
	static Queue<Node> red;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		map = new char[R][C];
		visited = new boolean[R][C][R][C];
		red = new LinkedList<>();
		blue = new LinkedList<>();
		for (int r = 0; r < R; r++) {
			map[r] = br.readLine().toCharArray();
			for (int c = 0; c < C; c++) {
				if (map[r][c] == 'R') {
					red.add(new Node(r, c, 0, ""));
					rx = r;
					ry = c;
				}
				if (map[r][c] == 'B') {
					blue.add(new Point(r, c));
					bx = r;
					by = c;
				}
				if (map[r][c] == 'O') {
					hx = r;
					hy = c;
				}
			}
		}
		BFS();
	}

	private static void BFS() {
		visited[rx][ry][bx][by] = true;

		while (!red.isEmpty()) {
			Node redball = red.poll();
			Point blueball = blue.poll();

			int rr = redball.r;
			int rc = redball.c;
			int br = blueball.r;
			int bc = blueball.c;
			int dep = redball.d;

			if (dep > 10) {
				System.out.println(-1);
				return;
			}
			if (rr == hx && rc == hy) {
				System.out.println(dep);
				System.out.println(redball.str);
				return;
			}

			for (int d = 0; d < dirs.length; d++) {
				int nrr = rr + dirs[d][0];
				int nrc = rc + dirs[d][1];

				int brr = br + dirs[d][0];
				int brc = bc + dirs[d][1];

				while (true) {
					if (map[nrr][nrc] == '#') { // 벽이 둘러싸고 있으므로 범위체크를 하지 않는다.
						nrr -= dirs[d][0];
						nrc -= dirs[d][1];
						break;
					} else if (map[nrr][nrc] == 'O')
						break;

					nrr += dirs[d][0];
					nrc += dirs[d][1];
				}

				while (true) {
					if (map[brr][brc] == '#') { // 벽이 둘러싸고 있으므로 범위체크를 하지 않는다.
						brr -= dirs[d][0];
						brc -= dirs[d][1];
						break;
					} else if (map[brr][brc] == 'O')
						break;

					brr += dirs[d][0];
					brc += dirs[d][1];
				}

				if (map[brr][brc] == 'O')
					continue; // 빨간공과 같이 빠지건 파란공 혼자 빠지건 무조건 넘긴다.

				if (nrr == brr && nrc == brc) {
					int reddis = Math.abs(nrr - rr) + Math.abs(nrc - rc);
					int bludis = Math.abs(brr - br) + Math.abs(brc - bc);

					if (reddis > bludis) {
						nrr -= dirs[d][0];
						nrc -= dirs[d][1];
					} else {
						brr -= dirs[d][0];
						brc -= dirs[d][1];
					}
				}

				if (visited[nrr][nrc][brr][brc])
					continue;
				switch (d) {
				case 0:
					red.add(new Node(nrr,nrc,dep+1,redball.str+'U'));
					break;
				case 1:
					red.add(new Node(nrr,nrc,dep+1,redball.str+'D'));
					break;
				case 2:
					red.add(new Node(nrr,nrc,dep+1,redball.str+'L'));
					break;
				case 3:
					red.add(new Node(nrr,nrc,dep+1,redball.str+'R'));
					break;
				}
				
				visited[nrr][nrc][brr][brc] = true;
				blue.add(new Point(brr,brc));
			}

		}
		System.out.println(-1);
		return;
	}

	static class Point {
		int r;
		int c;

		public Point(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}

	static class Node {
		int r;
		int c;
		int d;
		String str;

		public Node(int r, int c, int d, String str) {
			this.r = r;
			this.c = c;
			this.d = d;
			this.str = str;
		}
	}
}
