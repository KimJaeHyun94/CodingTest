package M0106;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.TreeSet;

public class 리틀프렌즈사천성 {
	private int[][] dirs = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };
	private HashMap<Integer, Character> hm = new HashMap<>();
	private HashMap<Character, Pair> start = new HashMap<>();
	private HashMap<Character, Pair> end = new HashMap<>();
	private static char[][] map;
	public static void main(String[] args) {

	}

	public String solution(int m, int n, String[] board) {
		String answer = "";
		TreeSet<Character> set =  new TreeSet<>();
		map = new char[m][n];

		for (int i = 0; i < board.length; i++) { 
			for (int j = 0; j < n; j++) {
				map[i][j] = board[i].charAt(j);
				if (board[i].charAt(j) == '.' || board[i].charAt(j) == '*') {
					continue;
				} else {
					set.add(board[i].charAt(j));
					if (start.get(board[i].charAt(j)) == null) {
						start.put(board[i].charAt(j), new Pair(i, j));
					} else if (end.get(board[i].charAt(j)) == null) {
						end.put(board[i].charAt(j), new Pair(i, j));
					}
				}
			}
		}

		int idx = 0;
		for (char ch : set) {
			hm.put(idx, ch);
			idx++;
		}
		StringBuilder sb = new StringBuilder();
		boolean isRemoved = true;
		
		while (true) {
			if (!isRemoved)
				break;
			isRemoved = false;

			Iterator<Integer> iter = hm.keySet().iterator();
			while (iter.hasNext()) {
				int key = iter.next();
				char ch = hm.get(key);
				Pair left = start.get(ch);
				Pair right = end.get(ch);

				int sr = left.r;
				int sc = left.c;
				int er = right.r;
				int ec = right.c;

				if (bfs(sr, sc, er, ec)) {
					map[sr][sc] = '.';
					map[er][ec] = '.';

					hm.remove(key);
					sb.append(ch);
					isRemoved = true;
					break;
				}

			}

		}
		if (hm.size() != 0)
			answer = "IMPOSSIBLE";
		else answer = sb.toString();
		
		return answer;
	}

	private boolean bfs(int sr, int sc, int er, int ec) {
		Queue<Node> q = new LinkedList<>();
		int R = map.length;
		int C = map[0].length;
		boolean[][][] visited = new boolean[4][R][C];

		for (int d = 0; d < dirs.length; d++) {
			int nr = sr + dirs[d][0];
			int nc = sc + dirs[d][1];

			if (!isOk(sr, sc, nr, nc, R, C))
				continue;

			q.offer(new Node(nr, nc, d, false));
			visited[d][nr][nc] = true;
		}

		while (!q.isEmpty()) {
			Node e = q.poll();
			int r = e.r;
			int c = e.c;
			int dir = e.dir;
			boolean flag = e.flag;

			if (r == er && c == ec)
				return true;

			if (!flag) {
				for (int i = -1; i <= 1; i += 2) {
					int next_dir = (dir + i + 4) % 4;
					int nr = r + dirs[next_dir][0];
					int nc = c + dirs[next_dir][1];

					if (!isOk(sr, sc, nr, nc, R, C))
						continue;
					if (visited[next_dir][nr][nc])
						continue;

					q.offer(new Node(nr, nc, next_dir, true));
					visited[next_dir][nr][nc] = true;
				}
			}

			int nr = r + dirs[dir][0];
			int nc = c + dirs[dir][1];

			if (!isOk(sr, sc, nr, nc, R, C))
				continue;
			if (visited[dir][nr][nc])
				continue;

			q.offer(new Node(nr, nc, dir, flag));
			visited[dir][nr][nc] = true;
		}

		return false;
	}

	private boolean isOk(int sr, int sc, int nr, int nc, int r, int c) {
		return nr>=0 && nr<r && nc>=0 && nc<c && (map[nr][nc] == '.' || map[nr][nc] == map[sr][sc]);
	}

	static class Pair {
		int r, c;

		public Pair(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}

	static class Node {
		int r, c, dir;
		boolean flag;

		public Node(int r, int c, int dir, boolean flag) {
			
			this.r = r;
			this.c = c;
			this.dir = dir;
			this.flag = flag;
		}

	}

}
