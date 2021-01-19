package M0312;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ13459구슬탈출 {
	static int R, C;
	static char map[][];
	static int cnt;
	static boolean flag;
	static int dir[][] = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
	static int rx, ry, bx, by, hx, hy;
	static boolean visited[][][][];
	static Queue<Point> red, blue;

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
					red.add(new Point(r, c));
					rx = r;
					ry = c;
				} if (map[r][c] == 'B') {
					blue.add(new Point(r, c));
					bx = r;
					by = c;
				} if (map[r][c] == 'O') {
					hx = r;
					hy = c;
				}
			}
		}
		
		visited[rx][ry][bx][by] = true;

		while (!red.isEmpty()) {
			int qsize = red.size();

			while (qsize-- > 0) {
				Point redball = red.poll();
				Point blueball = blue.poll();
				rx = redball.r;
				ry = redball.c;
				bx = blueball.r;
				by = blueball.c;

				if (cnt > 10)
					break;
				if (rx == hx && ry == hy) {
					flag = true;
					break;
				}
				for (int d = 0; d < 4; d++) {
					int nrx = rx + dir[d][0];
					int nry = ry + dir[d][1];
					int nbx = bx + dir[d][0];
					int nby = by + dir[d][1];

					while (true) {
						if (map[nrx][nry] == '#') {
							nrx -= dir[d][0]; nry -= dir[d][1];
							break;
						}
						if (map[nrx][nry] == 'O') break;
						nrx += dir[d][0]; nry += dir[d][1];
					}

					while (true) {
						if (map[nbx][nby] == '#') {
							nbx -= dir[d][0]; nby -= dir[d][1];
							break;
						}
						if (map[nbx][nby] == 'O') break;
						nbx += dir[d][0]; nby += dir[d][1];
					}
					
					if (nbx == hx && nby == hy) continue;
					
					if (nrx == nbx && nry == nby) {
						switch (d) {
						case 0:
							if (rx > bx)
								nrx++;
							else
								nbx++;
							break;
						case 1:
							if (rx < bx)
								nrx--;
							else
								nbx--;
							break;
						case 2:
							if (ry > by)
								nry++;
							else
								nby++;
							break;
						case 3:
							if (ry < by)
								nry--;
							else
								nby--;
							break;
						}
					}
					if (visited[nrx][nry][nbx][nby]) continue;
					red.add(new Point(nrx, nry));
					blue.add(new Point(nbx, nby));
					visited[nrx][nry][nbx][nby] = true;
				}
			}
			if (flag)
				break;
			else
				cnt++;
		}
		if (flag)
			System.out.println(1);
		else
			System.out.println(0);
	}

	static class Point {
		int r;
		int c;

		public Point(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
}
