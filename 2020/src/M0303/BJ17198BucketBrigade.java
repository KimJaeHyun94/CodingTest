package M0303;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ17198BucketBrigade {
	static char map[][] = new char[10][10];
	static int starty, startx;
	static int dir[][] = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
	static boolean visited[][] = new boolean[10][10];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		for (int i = 0; i < 10; i++) {
			String str = br.readLine();
			for (int j = 0; j < 10; j++) {
				map[i][j] = str.charAt(j);
				if (map[i][j] == 'L') {
					starty = i;
					startx = j;
				}
			}
		}
		BFS();

	}

	private static void BFS() {
		Queue<Node> q = new LinkedList<>();
		q.add(new Node(starty, startx, 0));
		visited[starty][startx] = true;
		while (!q.isEmpty()) {
			Node temp = q.poll();
			int y = temp.y;
			int x = temp.x;
			int l = temp.l;
			if (map[y][x] == 'B') {
				System.out.println(l - 1);
				return;
			}
			for (int i = 0; i < dir.length; i++) {
				int dy = y + dir[i][0];
				int dx = x + dir[i][1];

				if (isOK(dy, dx)) {
					visited[dy][dx]=true;
					q.add(new Node(dy, dx, l + 1));
				}
			}
		}
	}

	private static boolean isOK(int dy, int dx) {
		if (dy >= 0 && dy < 10 && dx >= 0 && dx < 10) {
			if (!visited[dy][dx] && map[dy][dx]!='R')
				return true;
		}
		return false;
	}

	static class Node {
		int y;
		int x;
		int l;

		public Node(int y, int x, int l) {
			super();
			this.y = y;
			this.x = x;
			this.l = l;
		}
	}
}
