package M0326;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BJ1600_말이되고픈원숭이 {
	static int K, W, H;
	static int[][] map;
	static boolean[][][] visit;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		K = sc.nextInt();
		W = sc.nextInt();
		H = sc.nextInt();

		map = new int[H][W];
		visit = new boolean[H][W][K+1];
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				map[i][j] = sc.nextInt();
			}
		}

		visit[0][0][0] = true;
		solution();
	}

	static int[] dx = { 1, -1, 0, 0 };
	static int[] dy = { 0, 0, 1, -1 };
	static int[] hx = { -2, -2, 2, 2, 1, -1, 1, -1 };
	static int[] hy = { 1, -1, 1, -1, 2, 2, -2, -2 };

	private static void solution() {
		Queue<monhorse> q = new LinkedList<monhorse>();
		q.add(new monhorse(0, 0, 0, K));

		while (!q.isEmpty()) {
			monhorse temp = q.poll();
			int x = temp.x;
			int y = temp.y;
			int cnt = temp.cnt;
			int k = temp.k;

			if (x == W - 1 && y == H - 1) {
				System.out.println(cnt);
				return;
			}

			if (!isOK(x,y))
				continue;
			if (map[y][x] == 1)
				continue;

			if (visit[y][x][k])
				continue;
			visit[y][x][k] = true;

			for (int i = 0; i < 4; i++) {
				int nextX = x + dx[i];
				int nextY = y + dy[i];
				q.add(new monhorse(nextX, nextY, cnt + 1, k));
			}

			if (k == 0)
				continue;
			for (int i = 0; i < 8; i++) {
				int nextX = x + hx[i];
				int nextY = y + hy[i];

				q.add(new monhorse(nextX, nextY, cnt + 1, k - 1));
			}
		}
		System.out.println("-1");
		return;

	}

	private static boolean isOK(int x, int y) {
		return x>=0&&x<W&&y>=0&&y<H;
	}

	static class monhorse {
		int x;
		int y;
		int cnt;
		int k;

		monhorse(int x, int y, int cnt, int k) {
			this.x = x;
			this.y = y;
			this.cnt = cnt;
			this.k = k;
		}
	}
}
