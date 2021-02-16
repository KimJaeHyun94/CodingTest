package M0216;

/**
 * @author 김재현
 * @see https://leveloper.tistory.com/109
 */
public class 블록게임 {

	static int R, C;
	private int board[][];

	public static void main(String[] args) {

	}

	public int solution(int[][] board) {
		int answer = 0;
		R = board.length;
		C = board[0].length;
		this.board = board;
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (board[i][j] == 0)
					continue;
				if (isA(i, j)) {
					if (drop(i + 1, j + 1, board[i][j]) && drop(i + 1, j + 2, board[i][j])) {
						remove(i, j, i + 1, j, i + 1, j + 1, i + 1, j + 2);
						j = -1;
						answer++;
					}
				} else if (isB(i, j)) {
					if (drop(i + 2, j - 1, board[i][j])) {
						remove(i, j, i + 1, j, i + 2, j, i + 2, j - 1);
						j = -1;
						answer++;
					}
				} else if (isC(i, j)) {
					if (drop(i + 2, j + 1, board[i][j])) {
						remove(i, j, i + 1, j, i + 2, j, i + 2, j + 1);
						j = -1;
						answer++;
					}
				} else if (isD(i, j)) {
					if (drop(i + 1, j - 1, board[i][j]) && drop(i + 1, j - 2, board[i][j])) {
						remove(i, j, i + 1, j, i + 1, j - 1, i + 1, j - 2);
						j = -1;
						answer++;
					}
				} else if (isE(i, j)) {
					if (drop(i + 1, j - 1, board[i][j]) && drop(i + 1, j + 1, board[i][j])) {
						remove(i, j, i + 1, j, i + 1, j - 1, i + 1, j + 1);
						j = -1;
						answer++;
					}
				}
			}
		}
		return answer;
	}

	private void remove(int x1, int y1, int x2, int y2, int x3, int y3, int x4, int y4) {
		board[x1][y1] = 0;
		board[x2][y2] = 0;
		board[x3][y3] = 0;
		board[x4][y4] = 0;
	}

	private boolean drop(int r, int c, int value) {
		for (int i = 0; i < r; i++) {
			if (board[i][c] == 0)
				continue;
			if (board[i][c] != value)
				return false;
		}
		return true;
	}

	private boolean isA(int r, int c) {
		int num = board[r][c];
		if (c + 2 >= C || r + 1 >= R)
			return false;
		return board[r + 1][c] == num && board[r + 1][c + 1] == num && board[r + 1][c + 2] == num;
	}

	private boolean isB(int r, int c) {
		int num = board[r][c];
		if (r + 2 >= R || c - 1 < 0)
			return false;
		return board[r + 1][c] == num && board[r + 2][c] == num && board[r + 2][c - 1] == num;
	}

	private boolean isC(int r, int c) {
		int num = board[r][c];
		if (r+ 2 >= R|| c + 1 >= C)
			return false;
		return board[r + 1][c] == num && board[r + 2][c] == num && board[r + 2][c + 1] == num;
	}

	private boolean isD(int r, int c) {
		int num = board[r][c];
		if (r + 1 >= R || c - 2 < 0)
			return false;
		return board[r + 1][c] == num && board[r + 1][c - 1] == num && board[r + 1][c - 2] == num;
	}

	private boolean isE(int r, int c) {
		int num = board[r][c];
		if (r + 1 >= R || c - 1 < 0 || c + 1 >= C)
			return false;
		return board[r + 1][c] == num && board[r + 1][c + 1] == num && board[r + 1][c - 1] == num;
	}

}
