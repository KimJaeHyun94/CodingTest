package M0201;

import java.util.*;


public class 리틀프렌즈사천성 {
	private int[][] dirs = { { 0, 1 }, { 0, -1 }, { 1, 0 } };
	private HashMap<Integer, Character> hm = new HashMap<>();
	private HashMap<Character, Pair> start = new HashMap<>();
	private HashMap<Character, Pair> end = new HashMap<>();
	private static char[][] map;
	private static int R, C;

//	public static void main(String[] args) {
//		String test[] = { "DBA", "C*A", "CDB" };
//		System.out.println(solution(3, 3, test));
//		String test2[] = { "NRYN", "ARYA" };
//		System.out.println(solution(2, 4, test2));
//	}

	public String solution(int m, int n, String[] board) {
		String answer = "";
		map = new char[m][n];
		R = m;
		C = n;
		HashSet<Character> set = new HashSet<>();
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
		
		List<Character> list = new ArrayList<>(set);
		Collections.sort(list);
		
		boolean remove = true; // 지워지는게 가능한지 판별
		StringBuilder sb = new StringBuilder();
		while (true) {
			if (!remove)
				break;
			remove = false;

			for (Character ch : list) {
				Pair left = start.get(ch);   //시작점
				Pair right = end.get(ch);   //끝점
				if (bfs(left, right)) {    //지울 수 있다면 지운다
					map[left.r][left.c] = '.';
					map[right.r][right.c] = '.';

					list.remove(ch);
					sb.append(ch);
					remove = true;
					break;
				}
			}
		}

		if (list.size() != 0)
			answer = "IMPOSSIBLE";
		else
			answer = sb.toString();
		return answer;
	}

	private boolean bfs(Pair sta, Pair en) {
		Queue<Node> q = new LinkedList<>();

		boolean[][][] visited = new boolean[3][R][C];

		for (int d = 0; d < dirs.length; d++) {
			int nr = sta.r + dirs[d][0];
			int nc = sta.c + dirs[d][1];

			if (!isOk(sta.r, sta.c, nr, nc))
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

			if (r == en.r && c == en.c)
				return true;

			if (!flag) // 방향 바꿀 수 있다면
			{
				if (dir == 0 || dir == 1) {
					int nr = r + dirs[2][0];
					int nc = c + dirs[2][1];

					if (isOk(sta.r, sta.c, nr, nc) && !visited[2][nr][nc]) {
						visited[2][nr][nc] = true;
						q.add(new Node(nr, nc, 2, true));
					}
				} else {
					for (int i = 0; i <= 1; i++) {
						int nr = r + dirs[i][0];
						int nc = c + dirs[i][1];

						if (isOk(sta.r, sta.c, nr, nc) && !visited[i][nr][nc]) {
							visited[i][nr][nc] = true;
							q.add(new Node(nr, nc, i, true));
						}
					}
				}
			}

			int nr = r + dirs[dir][0];
			int nc = c + dirs[dir][1];

			if (isOk(sta.r, sta.c, nr, nc) && !visited[dir][nr][nc]) {
				q.add(new Node(nr, nc, dir, flag));
				visited[dir][nr][nc] = true;
			}
		}

		return false;
	}

	private boolean isOk(int sr, int sc, int nr, int nc) {
		return nr >= 0 && nr < R && nc >= 0 && nc < C && (map[nr][nc] == '.' || map[nr][nc] == map[sr][sc]);
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
