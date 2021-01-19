package M1209;

import java.util.LinkedList;
import java.util.Queue;

class 블록이동하기 {
	private int n;
	private int[][] board;
	static int dirs[][] = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };
	private final int[] rdx = { -1, 1, 1, -1 };
	private final int[] rdy = { 1, 1, -1, -1 };

	public int solution(int[][] board) {
		this.board = board;
		this.n = board.length;
		boolean[][][] visit = new boolean[n][n][4];
		return bfs(visit);
	}

	private int bfs(boolean[][][] visit) {
		Queue<Robot> queue = new LinkedList<>();
		queue.add(new Robot(0, 0, 0, 1, 0, 0));

		visit[0][0][0] = true;
		int x, y, dir, time, ox, oy;
		int nx, ny, nox, noy, ndir;
		int rx, ry;
		while (!queue.isEmpty()) {
			Robot robot = queue.poll();
			x = robot.x;
			y = robot.y;
			dir = robot.dir;
			time = robot.time;
			ox = robot.ox;
			oy = robot.oy;
			if ((x == n - 1 && y == n - 1) || (ox == n - 1 && oy == n - 1)) {
				return time;
			}
			for (int i = 0; i < 4; i++) {
				nx = x + dirs[i][0];
				ny = y + dirs[i][1];
				nox = ox + dirs[i][0];
				noy = oy + dirs[i][1];
				if (!isValid(nx, ny) || !isValid(nox, noy))
					continue;
				if (board[nx][ny] == 1 || board[nox][noy] == 1)
					continue;
				if (visit[nx][ny][dir])
					continue;
				visit[nx][ny][dir] = true;
				queue.add(new Robot(nx, ny, nx + dirs[dir][0], ny + dirs[dir][1], dir, time + 1));
			}

			for (int i = 1; i < 4; i += 2) {
				ndir = (dir + i) % 4;
				nox = x + dirs[ndir][0];
				noy = y + dirs[ndir][1];
				int tempDir = (i == 1) ? ndir : dir;
				rx = x + rdx[tempDir];
				ry = y + rdy[tempDir];
				if (!isValid(nox, noy) || !isValid(rx, ry))
					continue;
				if (board[nox][noy] == 1 || board[rx][ry] == 1)
					continue;
				if (visit[x][y][ndir])
					continue;
				visit[x][y][ndir] = true;
				queue.add(new Robot(x, y, x + dirs[ndir][0], y + dirs[ndir][1], ndir, time + 1));
			}
			dir = (dir + 2) % 4;
			for (int i = 1; i < 4; i += 2) {
				ndir = (dir + i) % 4;
				nx = ox + dirs[ndir][0];
				ny = oy + dirs[ndir][1];
				int tempDir = (i == 1) ? ndir : dir;
				rx = ox + rdx[tempDir];
				ry = oy + rdy[tempDir];
				ndir = (ndir + 2) % 4;
				if (!isValid(nx, ny) || !isValid(rx, ry))
					continue;
				if (board[nx][ny] == 1 || board[rx][ry] == 1)
					continue;
				if (visit[nx][ny][ndir])
					continue;
				visit[nx][ny][ndir] = true;
				queue.add(new Robot(nx, ny, nx + dirs[ndir][0], ny + dirs[ndir][1], ndir, time + 1));
			}
		}
		return -1;
	}

	private boolean isValid(int x, int y) {
		return x >= 0 && y >= 0 && x < n && y < n;
	}

	private class Robot {
		int x, y, ox, oy;
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
