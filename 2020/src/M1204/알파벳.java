package M1204;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class 알파벳 {
	static int R, C;
	static char map[][];
	static int MAX = Integer.MIN_VALUE;
	static boolean visited[][];
	static int[][] dirs = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new char[R][C];

		for (int r = 0; r < R; r++) {
			String line = br.readLine();
			for (int c = 0; c < C; c++) {
				map[r][c] = line.charAt(c);
			}
		}
		BFS(0, 0);
		System.out.println(MAX);
	}

	private static void BFS(int r, int c) {
		visited = new boolean[R][C];
		Queue<Game> q = new LinkedList<>();
		LinkedList<Character> list = new LinkedList<Character>();
		list.add(map[r][c]);
		q.add(new Game(r, c, 1, list));
		visited[r][c] = true;
	
		while (!q.isEmpty()) {
			Game temp = q.poll();
			MAX = Math.max(MAX, temp.d);
			for (int d = 0; d < dirs.length; d++) {
				int nr = temp.r + dirs[d][0];
				int nc = temp.c + dirs[d][1];
				MAX = Math.max(MAX, temp.d);
				if (isOK(nr, nc)) {
					if (!temp.list.contains(map[nr][nc])) {
						LinkedList<Character> next = new LinkedList<>();
						next.addAll(temp.list);
						next.add(map[nr][nc]);
						q.add(new Game(nr, nc, temp.d + 1, next));
					}
				}
			}
		}
		return;
	}

	private static boolean isOK(int nr, int nc) {
		return nr >= 0 && nr < R && nc >= 0 && nc < C;
	}

	static class Game {
		int r;
		int c;
		int d;
		LinkedList<Character> list;

		public Game(int r, int c, int d, LinkedList<Character> list) {

			this.r = r;
			this.c = c;
			this.d = d;
			this.list = list;
		}

	}
}
