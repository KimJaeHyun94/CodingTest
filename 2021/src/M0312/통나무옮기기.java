package M0312;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class 통나무옮기기 {
	static int N;
	static char map[][];
	static ArrayList<Node> slist, elist;
	static int dirs[][] = { { -1, 0 }, { 1, 0 }, { 0, 1 }, { 0, -1 } };
	static int rdirs[][] = { { -1, 0 }, { 1, 0 }, { 0, 1 }, { 0, -1 }, { -1, -1 }, { -1, 1 }, { 1, -1 }, { 1, 1 } };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new char[N][N];

		slist = new ArrayList<>();
		elist = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < N; j++) {
				map[i][j] = str.charAt(j);

				if (map[i][j] == 'B') {
					slist.add(new Node(i, j));
					map[i][j] = '0';
				} else if (map[i][j] == 'E') {
					elist.add(new Node(i, j));
					map[i][j] = '0';
				}
			}
		}
		int stype = 0; // 방향 찾기 (세로인지 가로인지)
		if (slist.get(0).r == slist.get(1).r) {
			stype = 0;
		} else {
			stype = 1;
		}
		int etype = 0;
		if (elist.get(0).r == elist.get(1).r) {
			etype = 0;
		} else {
			etype = 1;
		}

		System.out.println(BFS(stype, etype));
	}

	private static int BFS(int stype, int etype) {
		int sr = slist.get(1).r;
		int sc = slist.get(1).c;
		int er = elist.get(1).r;
		int ec = elist.get(1).c;

		boolean visited[][][] = new boolean[N][N][2];
		Queue<Log> q = new LinkedList<>();
		q.add(new Log(sr, sc, stype, 0));
		visited[sr][sc][stype] = true;

		while (!q.isEmpty()) {
			Log cur = q.poll();
			int r = cur.r;
			int c = cur.c;
			int type = cur.type;
			int cnt = cur.cnt;
			
			
			if (type == etype && r == er && c == ec) {
				return cnt;
			}

			for (int d = 0; d < dirs.length; d++) {
				int dr = r + dirs[d][0];
				int dc = c + dirs[d][1];

				if (!isOK(dr, dc))
					continue;

				if (map[dr][dc] == '1')
					continue;

				if (visited[dr][dc][type])
					continue;

				if (type == 0) { // 가로일때
					int lc = dc - 1; // 왼쪽
					int rc = dc + 1; // 오른쪽

					if (isOK(lc, rc) && map[dr][lc] != '1' && map[dr][rc] != '1') {
						visited[dr][dc][type] = true;
						q.add(new Log(dr, dc, type, cnt + 1));
					} else
						continue;
				} else { // 세로일때
					int lr = dr - 1; // 위
					int rr = dr + 1; // 아래

					if (isOK(lr, rr) && map[lr][dc] != '1' && map[rr][dc] != '1') {
						visited[dr][dc][type] = true;
						q.add(new Log(dr, dc, type, cnt + 1));
					} else
						continue;
				}
			}
			boolean rotate = true;  
			for (int d = 0; d < rdirs.length; d++) {   //회전하기
				int dr = r + rdirs[d][0];
				int dc = c + rdirs[d][1];

				if (!isOK(dr, dc)) {
					rotate = false;
					break;
				}

				if (map[dr][dc] == '1') {
					rotate = false;
					break;
				}
			}
			if (rotate) {
				int rtype = -1;
				if (type == 1)
					rtype = 0;
				else
					rtype = 1;
				if (!visited[r][c][rtype]) {
					visited[r][c][rtype] = true;
					q.add(new Log(r, c, rtype, cnt + 1));
				}
			}

		}
		return 0;
	}

	private static boolean isOK(int dr, int dc) {
		return dr >= 0 && dr < N && dc >= 0 && dc < N;
	}

	static class Log {
		int r;
		int c;
		int type;
		int cnt;

		public Log(int r, int c, int type, int cnt) {
			this.r = r;
			this.c = c;
			this.type = type;
			this.cnt = cnt;
		}
	}

	static class Node {
		int r;
		int c;

		public Node(int r, int c) {
			this.r = r;
			this.c = c;
		}

	}
}
