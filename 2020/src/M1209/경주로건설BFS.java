package M1209;

import java.util.LinkedList;
import java.util.Queue;

public class 경주로건설BFS {
	static int dirs[][] = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };
	static int N;
	static int ans;

	public static void main(String[] args) {
		int[][] board = { { 0, 0, 1, 0 }, { 0, 0, 0, 0 }, { 0, 1, 0, 1 }, { 1, 0, 0, 0 } };
		System.out.println(solution(board));
	}

	public static int solution(int[][] board) {
		ans = Integer.MAX_VALUE;

		N = board.length;

		BFS(board);
		return ans;
	}

	private static void BFS(int[][] board) {
		Queue<road> q = new LinkedList<>();
		q.add(new road(0, 0, -1, 0));
		board[0][0]=1;
		while (!q.isEmpty()) {
			road temp = q.poll();
			int r = temp.r;
			int c = temp.c;
			int d = temp.d;
			int s = temp.s;
			if (r == N - 1 && c == N - 1) {
				ans = Math.min(ans, s);
			}
			for (int dir = 0; dir < dirs.length; dir++) {
				int dr = r + dirs[dir][0];
				int dc = c + dirs[dir][1];

				if (isOK(dr, dc) && board[dr][dc] != 1) {
					if (d == dir || d == -1) {
						if (board[dr][dc] == 0 || board[dr][dc]>=s+100) {
							board[dr][dc] = s+100;
							q.add(new road(dr, dc, dir, s + 100));
						}
					} else {
						if (board[dr][dc] == 0 || board[dr][dc]>=s+600) {
							board[dr][dc] = s+600;
							q.add(new road(dr, dc, dir, s + 600));
						}
					}
				}
			}
		}
	}

	private static boolean isOK(int dr, int dc) {
		return dr >= 0 && dr < N && dc >= 0 && dc < N;
	}

	static class road {
		int r;
		int c;
		int d;
		int s;

		public road(int r, int c, int d, int s) {
			this.r = r;
			this.c = c;
			this.d = d;
			this.s = s;
		}

	}
}