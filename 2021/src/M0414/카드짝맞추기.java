package M0414;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class 카드짝맞추기 {
	private static int MAX;
	private static int min_dist;
	private static int[][] dirs = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };
	static boolean isExists[];
	static Pair pair[];

	public static void main(String[] args) {
		System.out.println(
				solution(new int[][] { { 1, 0, 0, 3 }, { 2, 0, 0, 0 }, { 0, 0, 0, 2 }, { 3, 0, 1, 0 } }, 1, 0));
		System.out.println(
				solution(new int[][] { { 3, 0, 0, 2 }, { 0, 0, 1, 0 }, { 0, 1, 0, 0 }, { 2, 0, 0, 3 } }, 0, 1));
	}

	public static int solution(int[][] board, int r, int c) {
		min_dist = Integer.MAX_VALUE;
		MAX = 0; // 카드에서 최고로 큰 수
		pair = new Pair[7];
		isExists = new boolean[7];
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				if (board[i][j] != 0) {
					if (pair[board[i][j]] == null) {
						MAX++;
						pair[board[i][j]] = new Pair(board[i][j], i, j, -1, -1);
					} else {
						pair[board[i][j]].r2 = i;
						pair[board[i][j]].c2 = j;
					}
				}
			}
		}

		DFS(r, c, 0, 0, board);
		return min_dist;
	}

	private static void DFS(int r, int c, int cnt, int dist, int[][] board) {
		if (cnt == MAX) {
			min_dist = Math.min(dist, min_dist);
			return;
		}

		for (int i = 1; i <= MAX; i++) {
			if (!isExists[i]) {
				isExists[i] = true;

				int r1 = pair[i].r1;
				int c1 = pair[i].c1;
				int r2 = pair[i].r2;
				int c2 = pair[i].c2;

				int first = getDist(r, c, r1, c1, board);
				int second = getDist(r1, c1, r2, c2, board);
				board[r1][c1] = 0;
				board[r2][c2] = 0;

				DFS(r2, c2, cnt + 1, dist + first + second, board);

				board[r1][c1] = i;
				board[r2][c2] = i;

				first = getDist(r, c, r2, c2, board);
				second = getDist(r2, c2, r1, c1, board);
				board[r1][c1] = 0;
				board[r2][c2] = 0;

				DFS(r1, c1, cnt + 1, dist + first + second, board);
				isExists[i] = false;
				board[r1][c1] = i;
				board[r2][c2] = i;
			}
		}
	}

	private static int getDist(int r, int c, int r1, int c1, int[][] board) {
		Queue<Node> q = new LinkedList<>();
		q.add(new Node(r, c, 0));
		boolean visited[][] = new boolean[4][4];
		visited[r][c] = true;

		while (!q.isEmpty()) {
			Node cur = q.poll();
			
			if(cur.r==r1 && cur.c==c1) {
				return cur.d+1;
			}
			
			for (int d = 0; d < dirs.length; d++) {
				int dr = cur.r;
				int dc = cur.c;
				int cnt = 0;
				
				while(isOK(dr+dirs[d][0], dc+dirs[d][1])) {
					cnt++;
					dr+=dirs[d][0];
					dc+=dirs[d][1];
					
					if(board[dr][dc]!=0) {
						break;
					}
				}
				if(!visited[dr][dc]) {
					visited[dr][dc] = true;
					q.add(new Node(dr,dc,cur.d+1));
				}
				
				dr = cur.r+dirs[d][0];
				dc = cur.c+dirs[d][1];
				
				if(isOK(dr,dc) && !visited[dr][dc]) {
					visited[dr][dc] = true;
					q.add(new Node(dr,dc,cur.d+1));
				}
			}
		}
		return 0;
	}

	private static boolean isOK(int nr, int nc) {
		return nr >= 0 && nr < 4 && nc >= 0 && nc < 4;
	}

	static class Pair {
		int idx;
		int r1, c1, r2, c2;

		public Pair(int idx, int r1, int c1, int r2, int c2) {
			this.idx = idx;
			this.r1 = r1;
			this.c1 = c1;
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
