package M0224;

public class 블록게임_직사각형 {
	static int N;
	private int board[][];
	public int solution(int[][] board) {
		int answer = 0;
		N = board.length;
		this.board = board;
		int cnt = 1;
		while (cnt > 0) {
			cnt = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (i <= N - 3 && j <= N - 2 && square(i, j, 3, 2))
						cnt++;
					else if (i <= N - 2 && j <= N - 3 && square(i, j, 2, 3))
						cnt++;
				}
			}
			answer += cnt;
		}
		return answer;
	}
	private boolean square(int r, int c, int h, int w) {
		int cnt = 0;
		int value = -1;
		for (int i = r; i < r + h; i++) {
			for (int j = c; j < c + w; j++) {
				if (board[i][j] == 0) {
					if (!fill(i, j) || ++cnt > 2)
						return false;
				} else {
					if (value != -1 && value != board[i][j])
						return false;
					value = board[i][j];
				}
			}
		}

		for (int i = r; i < r + h; i++)
			for (int j = c; j < c + w; j++)
				board[i][j] = 0;

		return true;
	}

	private boolean fill(int r, int c) {
		for (int i = 0; i < r; i++)
			if (board[i][c] != 0)
				return false;
		return true;
	}
}
