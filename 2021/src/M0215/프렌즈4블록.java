package M0215;
/**
 * 
 * @author 김재현
 * @See https://tech.kakao.com/2017/09/27/kakao-blind-recruitment-round-1/
 */
public class 프렌즈4블록 {
	static int[][] dirs = { { 1, 0 }, { 0, 1 }, { 1, 1 } }; // 오른쪽 왼쪽 대각선 아래
	static int R, C;
	static int block[][];
	static boolean erased[][];

	public static void main(String[] args) {
		System.out.println(solution(4, 5, new String[] { "CCBDE", "AAADE", "AAABF", "CCBBF" }));
		System.out.println(solution(6, 6, new String[] { "TTTANT", "RRFACC", "RRRFCC", "TRRRAA", "TTMMMF", "TMMTTJ" }));
	}

	public static int solution(int m, int n, String[] board) {
		int answer = 0;
		R = m;
		C = n;
		block = new int[R][C];
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length(); j++) {
				block[i][j] = board[i].charAt(j) - '0';
			}
		}

		while (true) {
			erased = new boolean[R][C];
			if (!check()) {
				break;
			}
			answer += eraseblock(); // 얼마나 지울꺼니?
			fresh(); // 비운데를 채우기
		}
		return answer;
	}

	private static void fresh() {
		for (int c = 0; c < C; c++) {
			for (int r = R - 1; r >= 0; r--) {
				if (block[r][c] == -1) {
					for (int k = r - 1; k >= 0; k--) {
						if (block[k][c] != -1) { // 가장 먼저 만나는 -1아닌 것과 치환한다.
							block[r][c] = block[k][c];
							block[k][c] = -1;
							break;
						}
					}
				}
			}
		}
	}

	private static int eraseblock() {
		int cnt = 0;
		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				if (erased[r][c]) {
					block[r][c] = -1;
					cnt++;
				}
			}
		}
		return cnt;
	}

	private static boolean check() {
		boolean erase = false; // 지울 수 있는지 확인하자

		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				if (block[r][c] == -1) {
					continue;
				}
				boolean flag = true;
				for (int d = 0; d < dirs.length; d++) {
					int dr = r + dirs[d][0];
					int dc = c + dirs[d][1];

					if (!isOK(dr, dc) || block[r][c] != block[dr][dc]) {
						flag = false;
					}
				}

				if (flag) { // 지울 수 있다면
					erase = true;
					erased[r][c] = true;
					for (int d = 0; d < dirs.length; d++) {
						int dr = r + dirs[d][0];
						int dc = c + dirs[d][1];

						erased[dr][dc] = true;
					}
				}
			}
		}
		return erase;
	}

	private static boolean isOK(int dr, int dc) {
		return dr >= 0 && dr < R && dc >= 0 && dc < C;
	}
}
