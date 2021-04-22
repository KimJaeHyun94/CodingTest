package M0418;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 아맞다우산 {
	static int N, M;
	static int dirs[][] = { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 } };
	static char map[][];
	static int sr, sc, er, ec;
	static int idx;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new char[M][N];
		idx = 0;

		for (int r = 0; r < M; r++) {
			String line = br.readLine();
			for (int c = 0; c < N; c++) {
				map[r][c]  = line.charAt(c);
				if (map[r][c]  == 'S') {
					sr = r;
					sc = c;
				} else if (map[r][c]  == 'E') {
					er = r;
					ec = c;
				} else if (map[r][c]  == 'X') {
					map[r][c]  = (char) (idx++ + '0');
					
				}
			}
		}
		BFS();
	}

	private static void BFS() {
		Queue<Data> q = new LinkedList<>();
		boolean visited[][][] = new boolean[M][N][1 << idx];
		q.add(new Data(sr, sc, 0, 0));
		visited[sr][sc][0] = true;

		while (!q.isEmpty()) {
			Data cur = q.poll();

			if (cur.r == er && cur.c == ec && cur.key == (1 << idx) - 1) {
				System.out.println(cur.time);
				return;
			}

			for (int d = 0; d < dirs.length; d++) {
				int dr = cur.r + dirs[d][0];
				int dc = cur.c + dirs[d][1];
				int key = cur.key;
				int time = cur.time;

				if (!isOK(dr, dc) || map[dr][dc] == '#' || visited[dr][dc][key])
					continue;

				char ch = (char)(idx+'0');
	
				if ('0' <= map[dr][dc] && map[dr][dc] < ch) {
					key |= (1 << (map[dr][dc] - '0'));
				}
				
				visited[dr][dc][key] = true;
				q.add(new Data(dr,dc,key,time+1));
			}
		}
	}

	private static boolean isOK(int dr, int dc) {
		return dr >= 0 && dr < M && dc >= 0 && dc < N;
	}

	static class Data {
		int r, c;
		int key;
		int time;

		public Data(int r, int c, int key, int time) {
			super();
			this.r = r;
			this.c = c;
			this.key = key;
			this.time = time;
		}

	}
}
