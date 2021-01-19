package M0119;

public class 경주로건설 {
	static int dirs[][] = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };
	static int N;
	static int ans;
	public static void main(String[] args) {
		System.out.println(solution(new int[][] {{0,0,0},{0,0,0},{0,0,0}}));
	}
	public static int solution(int[][] board) {
		ans = Integer.MAX_VALUE;

		N = board.length;

		DFS(0, 0, 0, -1, board);
		return ans;
	}

	private static void DFS(int r, int c, int cnt, int dir, int[][] board) {
		if (r == N - 1 && c == N - 1) {
			ans = Math.min(ans, cnt);
			return;
		}

		for (int d = 0; d < dirs.length; d++) {
			int dr = r + dirs[d][0];
			int dc = c + dirs[d][1];

			if (isOK(dr, dc)) {
				if(dir==-1 || d==dir) { 	//방향이 같거나 처음 시작할 때
					if(board[dr][dc]==0 || board[dr][dc]>=cnt+100) {	//처음 가는 곳이거나 이미 있는곳이 더 클때
						board[dr][dc] = cnt+100;
						DFS(dr,dc, cnt+100, d, board);
					}
				}
				else {
					if(board[dr][dc]==0 || board[dr][dc]>=cnt+600) {	//처음 가는 곳이거나 이미 있는곳이 더 클때
						board[dr][dc] = cnt+600;
						DFS(dr,dc, cnt+600, d, board);
					}
				}
			}
		}
	}

	private static boolean isOK(int dr, int dc) {
		return dr >= 0 && dr < N && dc >= 0 && dc < N;
	}
}
