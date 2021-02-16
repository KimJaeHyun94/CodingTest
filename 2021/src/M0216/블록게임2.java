package M0216;

/**
 * @author 김재현
 * @see https://leveloper.tistory.com/109
 */
public class 블록게임2 {
	static int R, C;
	private int board[][];
	static int dirs[][] = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

	public static void main(String[] args) {

	}

	public int solution(int[][] board) {
		int answer = 0;
		R = board.length;
		C = board[0].length;
		this.board = board;
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (board[i][j] != 0) {
					int type = getType(i, j);
					if (type == -1) // 해당하는 5가지 모양이 아니라면 지나쳐라
						continue;
					if (canremove(i, j, type)) {
						remove(i, j, board[i][j]);
						j = -1;
						answer++;
					}
				}
			}
		}
		return answer;
	}

	private void remove(int i, int j, int val) {
		board[i][j] = 0;

		for (int d = 0; d < 4; d++) {
			int di = i + dirs[d][0];
			int dj = j + dirs[d][1];

			if (isOK(di, dj) && board[di][dj] == val) {
				remove(di, dj, val);
			}
		}
	}

	private boolean isOK(int di, int dj) {
		return di >= 0 && di < R && dj >= 0 && dj < C;
	}

	private boolean remover(int r, int c, int value) {
		for (int i = 0; i < r; i++) {
			if (board[i][c] == 0)
				continue;
			if (board[i][c] != value)
				return false;
		}
		return true;
	}

	private boolean canremove(int i, int j, int type) {// |___ , _| , |_, ____ㅣ , ㅗ
		switch (type) {
		case 1:
			if (remover(i + 1, j + 1, board[i][j]) && remover(i + 1, j + 2, board[i][j]))
				break;
			else
				return false;
		case 2:
			if (remover(i + 2, j - 1, board[i][j]))
				break;
			else
				return false;
		case 3:
			if (remover(i + 2, j + 1, board[i][j]))
				break;
			else
				return false;
		case 4:
			if (remover(i + 1, j - 1, board[i][j]) && remover(i + 1, j - 2, board[i][j]))
				break;
			else
				return false;
		default:
			if (remover(i + 1, j - 1, board[i][j]) && remover(i + 1, j + 1, board[i][j]))
				break;
			else
				return false;
		}
		return true;
	}

	private int getType(int i, int j) {
		int value = board[i][j];
		if (i + 1 < R && j + 2 < C && board[i + 1][j] == value && board[i + 1][j + 1] == value
				&& board[i + 1][j + 2] == value) {
			return 1;
		} else if (i + 2 < R && j - 1 >= 0 && board[i + 1][j] == value && board[i + 2][j] == value
				&& board[i + 2][j - 1] == value) {
			return 2;
		} else if (i + 2 < R && j + 1 < C && board[i + 1][j] == value && board[i + 2][j] == value
				&& board[i + 2][j + 1] == value) {
			return 3;
		} else if (i + 1 < R && j - 2 >= 0 && board[i + 1][j] == value && board[i + 1][j - 1] == value
				&& board[i + 1][j - 2] == value) {
			return 4;
		} else if (i + 1 < R && j - 1 >= 0 && j + 1 < C && board[i + 1][j] == value && board[i + 1][j - 1] == value
				&& board[i + 1][j + 1] == value) {
			return 5;
		}

		return -1;
	}
}
