package M0407;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 구슬탈출4 {
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
						int reddis = Math.abs(nrx-rx) +Math.abs(nry-ry);	//누가 더 많이 움직였는가?
						int bluedis = Math.abs(nbx-bx)+Math.abs(nby-by);
						
						if(reddis>bluedis) {
							nrx-=dir[d][0];
							nry-=dir[d][1];
						}
						else {
							nbx-=dir[d][0];
							nby-=dir[d][1];
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
			System.out.println(cnt);
		else
			System.out.println(-1);
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