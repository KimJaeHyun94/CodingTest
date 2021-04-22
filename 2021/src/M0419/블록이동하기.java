package M0419;

import java.util.LinkedList;
import java.util.Queue;

public class 블록이동하기 {
	private static int n;
	private static int N;
	static int[][] board;
	private static int dirs[][] = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };
	private static int[] rdx = { -1, 1, 1, -1 }; // 대각선
	private static int[] rdy = { 1, 1, -1, -1 };

	public static void main(String[] args) {
		System.out.println(solution(new int[][] { { 0, 0, 0, 1, 1 }, { 0, 0, 0, 1, 0 }, { 0, 1, 0, 1, 1 },
				{ 1, 1, 0, 0, 1 }, { 0, 0, 0, 0, 0 } }));
	}

	public static int solution(int[][] board2) {
		board = board2;
		N = board.length;
		boolean[][][] visit = new boolean[N][N][4];
		return bfs(visit);

	}

	private static int bfs(boolean[][][] visit) {
		Queue<Robot> q = new LinkedList<>();
		q.add(new Robot(0, 0, 0, 1, 0, 0)); // 초기 첫 출발
		visit[0][0][0] = true;

		while (!q.isEmpty()) {
			Robot temp = q.poll();
			int x = temp.x, y = temp.y, dir = temp.dir, time = temp.time, ox = temp.ox, oy = temp.oy;
			
			if (x == N - 1 && y == N - 1 || ox == N - 1 && oy == N - 1) {
				return time;
			}
			for (int d = 0; d < dirs.length; d++) { // 회전 없이
				int dx = x + dirs[d][0];
				int dy = y + dirs[d][1];
				int dox = ox + dirs[d][0];
				int doy = oy + dirs[d][1];

				if (isOK(dx, dy) && isOK(dox, doy)) {
					if (board[dx][dy] != 1 && board[dox][doy] != 1 && !visit[dx][dy][dir]) {
						visit[dx][dy][dir] = true;
						q.add(new Robot(dx, dy, dx + dirs[dir][0], dy + dirs[dir][1], dir, time + 1));
					}
				}
			}
			for (int d = 1; d < dirs.length; d += 2) { // x,y축 기준으로 회전
				int ndir = (dir + d) % 4;
				int dx = x + dirs[ndir][0];
				int dy = y + dirs[ndir][1];

				int tempDir = (d == 1) ? ndir : dir;
				int nx = x + rdx[tempDir];
				int ny = y + rdy[tempDir];

				if (isOK(dx, dy) && isOK(nx, ny)) {
					if (board[dx][dy] != 1 && board[nx][ny] != 1 && !visit[x][y][ndir]) {
						visit[x][y][ndir] = true;
						q.add(new Robot(x, y, x + dirs[ndir][0], y + dirs[ndir][1], ndir, time + 1));
					}
				}
			}
			dir = (dir + 2) % 4; // 방향 반대로
			for (int d = 1; d < 4; d += 2) { // ox, oy 기준으로 회전
				int ndir = (dir + d) % 4;
				int dox = ox + dirs[ndir][0];
				int doy = oy + dirs[ndir][1];

				int tempDir = (d == 1) ? ndir : dir;
				int nox = ox + rdx[tempDir];
				int noy = oy + rdy[tempDir];
				ndir = (ndir + 2) % 4;

				if (isOK(dox, doy) && isOK(nox, noy)) {
					if (board[nox][noy] != 1 && board[dox][doy] != 1 && !visit[dox][doy][ndir]) {
						visit[dox][doy][ndir] = true;
						q.add(new Robot(dox, doy, dox + dirs[ndir][0], doy + dirs[ndir][1], ndir, time + 1));
					}
				}
			}
		}
		return -1;
	}

	private static boolean isOK(int x, int y) {
		return x >= 0 && y >= 0 && x < N && y < N;
	}

	private static class Robot { // 로봇 클래스
		int x, y, ox, oy; // 좌표(x,y), 앞에있는 놈 좌표(ox, oy)
		int dir;
		int time;

		Robot(int x, int y, int ox, int oy, int dir, int time) {
			this.x = x;
			this.y = y;
			this.dir = dir;
			this.time = time;
			this.ox = ox;
			this.oy = oy;
		}

	}
}
