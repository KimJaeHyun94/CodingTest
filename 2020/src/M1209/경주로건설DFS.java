package M1209;

public class 경주로건설DFS {
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

		DFS(0, 0, 0, -1, board);
		return ans;
	}

	private static void DFS(int r, int c, int s, int d, int[][] board) {
		if (r == N - 1 && c == N - 1) {
			ans = Math.min(ans, s);
			return;
		}
		for (int dir = 0; dir < dirs.length; dir++) {
			int dr = r + dirs[dir][0];
			int dc = c + dirs[dir][1];

			if (isOK(dr, dc) && board[dr][dc] != 1) {
				if (d == dir || d == -1) {
					if (board[dr][dc] >= s + 100 || board[dr][dc] == 0) {
						board[dr][dc] = s + 100;
						DFS(dr, dc, s + 100, dir, board);
					}
				} else {
					if (board[dr][dc] >= s + 600 || board[dr][dc] == 0) {
						board[dr][dc] = s + 600;
						DFS(dr, dc, s + 600, dir, board);
					}
				}
			}
		}
	}

	private static boolean isOK(int dr, int dc) {
		return dr >= 0 && dr < N && dc >= 0 && dc < N;
	}
}
