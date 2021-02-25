package M0224;

public class 블록게임 {
	static int R, C;
	private int board[][];
	static int dirs[][] = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };


	public int solution(int[][] board) {
		int answer = 0;
		R = board.length;
		C = board[0].length;
		this.board = board;
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (board[i][j] != 0) {
					int type = getType(i, j); // 지울수 있는 5가지 모양 |___ , _| , |_, ____ㅣ , ㅗ인지 확인한다.
					if (type == -1)
						continue;
					if (canremove(i, j, type)) { // |___ , _| , |_, ____ㅣ , ㅗ인 것 중에 지울 수 있는지 확인한다.
						remove(i, j, board[i][j]);
						j = -1;
						answer++;
					}
				}
			}
		}

		return answer;
	}

	private void remove(int i, int j, int cnt) {
		board[i][j] = 0;

		for (int d = 0; d < dirs.length; d++) {
			int di = i + dirs[d][0];
			int dj = j + dirs[d][1];

			if (isOK(di, dj) && board[di][dj]==cnt) {
				remove(di, dj, cnt);
			}
		}
	}

	private boolean eraser(int r, int c, int value) { // 지울 수 있는지 확인한다.
		for (int i = 0; i < r; i++) { // 그 위로 0이 아니거나 value가 아니면 지울 수 없다(다른 블럭이 있기 때문에)
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
			if (eraser(i + 1, j + 1, board[i][j]) && eraser(i + 1, j + 2, board[i][j])) {
				return true;
			} else
				return false;
		case 2:
			if (eraser(i + 2, j - 1, board[i][j])) {
				return true;
			} else
				return false;
		case 3:
			if (eraser(i + 2, j + 1, board[i][j])) {
				return true;
			} else {
				return false;
			}
		case 4:
			if (eraser(i + 1, j - 1, board[i][j]) && eraser(i + 1, j - 2, board[i][j])) {
				return true;
			} else {
				return false;
			}
		case 5:
			if (eraser(i + 1, j - 1, board[i][j]) && eraser(i + 1, j + 1, board[i][j]))
				break;
			else
				return false;
		}
		return true;
	}

	private int getType(int i, int j) { // |___ , _| , |_, ____ㅣ , ㅗ
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

	private boolean isOK(int di, int dj) {
		return di >= 0 && di < R && dj >= 0 && dj < C;
	}
}
