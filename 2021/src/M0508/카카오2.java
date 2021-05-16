package M0508;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class 카카오2 {
	public static void main(String[] args) {
		String[][] places = { { "POOOP", "OXXOX", "OPXPX", "OOXOX", "POXXP" },
				{ "POOPX", "OXPXP", "PXXXO", "OXXXO", "OOOPP" }, { "PXOPX", "OXOXP", "OXPXX", "OXXXP", "POOXX" },
				{ "OOOXX", "XOOOX", "OOOXX", "OXOOX", "OOOOO" }, { "PXPXP", "XPXPX", "PXPXP", "XPXPX", "PXPXP" } };
		System.out.println(Arrays.toString(solution(places)));
	}

	static boolean flag;
	static int dirs[][] = { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 } };
	static boolean visited[][];
	static char map[][];
	static int idx;

	public static int[] solution(String[][] places) {

		int[] answer = new int[places.length];

		for (int i = 0; i < places.length; i++) {
			idx = i;
			answer[i] = Sol(places[i]);
		}

		return answer;
	}

	private static int Sol(String[] str) {
		map = new char[5][5];
		for (int i = 0; i < 5; i++) {
			String line = str[i];
			for (int j = 0; j < 5; j++) {
				map[i][j] = line.charAt(j);
			}
		}

		flag = false;
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				if (flag)
					break;
				if (map[i][j] == 'P')
					bfs(i, j);
			}
		}

		if (flag)
			return 0;
		else
			return 1;
	}

	private static void bfs(int sr, int sc) {
		Queue<Node> q = new LinkedList<>();
		q.add(new Node(sr, sc));
		visited = new boolean[5][5];
		visited[sr][sc] = true;

		while (!q.isEmpty()) {
			Node cur = q.poll();
			int r = cur.r;
			int c = cur.c;

			for (int d = 0; d < dirs.length; d++) {
				int dr = r + dirs[d][0];
				int dc = c + dirs[d][1];

				if (isOK(dr, dc) && !visited[dr][dc]) {
					if (manhaten(sr, sc, dr, dc)) {
						if (map[dr][dc] == 'O') {
							q.add(new Node(dr, dc));
							visited[dr][dc] = true;
						} else if (map[dr][dc] == 'P') {
							flag = true;
							return;
						}
					}
				}
			}
		}
	}

	private static boolean manhaten(int sr, int sc, int dr, int dc) {
		if (Math.abs(sr - dr) + Math.abs(sc - dc) <= 2)
			return true;
		return false;
	}

	private static boolean isOK(int dr, int dc) {
		return dr >= 0 && dr < 5 && dc >= 0 && dc < 5;
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
