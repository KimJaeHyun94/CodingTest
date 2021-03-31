package M0331;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class PuyoPuyo {
	static int map[][];
	static boolean visited[][];
	static int dirs[][] = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
	static int cnt = 0;
	static boolean flag;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		map = new int[12][6];

		for (int r = 0; r < 12; r++) {
			String line = br.readLine();
			for (int c = 0; c < 6; c++) {
				char ch = line.charAt(c);
				if (ch == '.') {
					map[r][c] = 0;
				} else if (ch == 'R') {
					map[r][c] = 1;
				} else if (ch == 'G') {
					map[r][c] = 2;
				} else if (ch == 'B') {
					map[r][c] = 3;
				} else if (ch == 'Y') {
					map[r][c] = 4;
				} else {
					map[r][c] = 5;
				}
			}
		}

		while (true) {
			flag = false;
			visited = new boolean[12][6];

			for (int r = 0; r < 12; r++) {
				for (int c = 0; c < 6; c++) {
					if (!visited[r][c] && map[r][c] != 0) {
						BFS(r, c);
					}
				}
			}
			if (flag) {
				cnt++;
				fall();
			} else {
				break;
			}
		}
		System.out.println(cnt);
	}

	private static void fall() {
		for (int c = 0; c < 6; c++) {
			for (int r = 11; r > 0; r--) {
				if (map[r][c] == 0) {
					for (int j = r - 1; j >= 0; j--) {
						if (map[j][c] != 0) {
							map[r][c] = map[j][c];
							map[j][c] = 0;
							break;
						}
					}
				}
			}
		}
	}

	private static void BFS(int r, int c) {
		Queue<Node> q = new LinkedList<>();
		ArrayList<Node> Route = new ArrayList<>();
		q.add(new Node(r, c));
		Route.add(new Node(r, c));
		visited[r][c] = true;

		while (!q.isEmpty()) {
			Node cur = q.poll();

			for (int d = 0; d < dirs.length; d++) {
				int dr = cur.r + dirs[d][0];
				int dc = cur.c + dirs[d][1];

				if (isOK(dr, dc) && !visited[dr][dc] && map[dr][dc] == map[r][c]) {
					Route.add(new Node(dr, dc));
					q.add(new Node(dr, dc));
					visited[dr][dc] = true;
				}
			}
		}

		if (Route.size() >= 4) {
			flag = true;
			for (Node node : Route) {
				map[node.r][node.c] = 0;
			}
		} else
			return;
	}

	private static boolean isOK(int dr, int dc) {
		return dr >= 0 && dr < 12 && dc >= 0 && dc < 6;
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
