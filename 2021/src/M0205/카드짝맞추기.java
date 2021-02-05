package M0205;

import java.util.LinkedList;
import java.util.Queue;

public class 카드짝맞추기 {
	static int[][] dirs = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };
	static Card pair[];
	static int MAX;
	static int answer;
	static boolean isExists[];

	public static void main(String[] args) {
		System.out.println(
				solution(new int[][] { { 1, 0, 0, 3 }, { 2, 0, 0, 0 }, { 0, 0, 0, 2 }, { 3, 0, 1, 0 } }, 1, 0));
		System.out.println(
				solution(new int[][] { { 3, 0, 0, 2 }, { 0, 0, 1, 0 }, { 0, 1, 0, 0 }, { 2, 0, 0, 3 } }, 0, 1));
	}

	public static int solution(int[][] board, int r, int c) {
		pair = new Card[7];
		answer = Integer.MAX_VALUE;
		MAX = 0;
		isExists = new boolean[7];
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				if (board[i][j] != 0) {
					if (pair[board[i][j]] == null) {
						MAX++;
						pair[board[i][j]] = new Card(board[i][j], i, j, -1, -1);
					} else {
						pair[board[i][j]].r2 = i;
						pair[board[i][j]].c2 = j;
					}
				}
			}
		}

		permutation(r, c, 0, 0, board); // 1,2,3 순서 정하기
		return answer;
	}

	private static void permutation(int r, int c, int cnt, int dist, int[][] board) {
		if (cnt == MAX) {
			answer = Math.min(dist, answer);
			return;
		}

		for (int i = 1; i <= MAX; i++) {
			if (!isExists[i]) {
				isExists[i] = true;
				int r1 = pair[i].r;
				int c1 = pair[i].c; // 그 숫자의 첫번째 카드
				int r2 = pair[i].r2;
				int c2 = pair[i].c2; // 그 숫자의 두번째 카드
				int first = BFS(r, c, r1, c1, board);
				int second = BFS(r1, c1, r2, c2, board);
				board[r1][c1] = 0;
				board[r2][c2] = 0;

				permutation(r2, c2, cnt + 1, dist + first + second, board);

				board[r1][c1] = i;
				board[r2][c2] = i;

				first = BFS(r, c, r2, c2, board);
				second = BFS(r2, c2, r1, c1, board);
				board[r1][c1] = 0;
				board[r2][c2] = 0;

				permutation(r1, c1, cnt + 1, dist + first + second, board);
				isExists[i] = false;
				board[r1][c1] = i;
				board[r2][c2] = i;
			}
		}
	}

	private static int BFS(int sr, int sc, int dr, int dc, int[][] board) {
		Queue<Node> q = new LinkedList<>();
		boolean[][] visited = new boolean[4][4];
		q.offer(new Node(sr, sc, 0));
		visited[sr][sc] = true;

		while (!q.isEmpty()) {
			Node node = q.poll();
			int depth = node.d;

			if (node.r == dr && node.c == dc) {
				return depth + 1;
			}

			for (int d = 0; d < dirs.length; d++) {
				int nr = 0, nc = 0;
				int r = node.r, c = node.c;
				while (true) {   		      //원래 방향대로 쭉 간다(다른 숫자를 만나기 전까지
					nr = r + dirs[d][0];
					nc = c + dirs[d][1];
					if (!isInMap(nr, nc)) {   //맵보다 크면 그 전까지 간다
						break;
					}
					r = nr;
					c = nc;
					if (board[r][c] != 0)   //다른 숫자 만나면 break
						break;
				}

				if (!visited[r][c]) {
					visited[r][c] = true;
					q.offer(new Node(r, c, depth + 1));
				}
				
				nr = node.r + dirs[d][0];
				nc = node.c + dirs[d][1];

				if (isInMap(nr, nc) && !visited[nr][nc]) {  //방향 전환
					visited[nr][nc] = true;
					q.offer(new Node(nr, nc, depth + 1));
				}
			}
		}
		return 0;
	}
	private static boolean isInMap(int nr, int nc) {
		return nr >= 0 && nr < 4 && nc >= 0 && nc < 4;
	}

	static class Card {
		int idx;
		int r;
		int c;
		int r2;
		int c2;

		public Card(int idx, int r, int c, int r2, int c2) {
			this.idx = idx;
			this.r = r;
			this.c = c;
			this.r2 = r2;
			this.c2 = c2;
		}
	}

	static class Node {
		int r;
		int c;
		int d;

		public Node(int r, int c, int d) {
			this.r = r;
			this.c = c;
			this.d = d;
		}

	}
}
