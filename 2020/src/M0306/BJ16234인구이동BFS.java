package M0306;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ16234인구이동BFS {
	static int N, L, R;
	static int map[][];
	static int cnt;
	static boolean visited[][];
	static List<Node> list;
	static int sum = 0;
	static int dir[][] = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		while (true) {
			boolean flag = false;
			visited = new boolean[N][N];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (!visited[i][j]) {
						list = new LinkedList<>();
						list.add(new Node(i, j));
						sum = map[i][j];
						BFS(i, j);
						if (list.size() > 1) {
							flag = true;
							int pung = sum / list.size();
							for (int k = 0; k < list.size(); k++) {
								map[list.get(k).y][list.get(k).x] = pung;
							}
						}

					}
				}
			}
			if (flag) {
				cnt++;
			} else {
				break;
			}
		}
		System.out.println(cnt);
	}

	private static void BFS(int a, int b) {
		Queue<Node> q = new LinkedList<>();
		q.add(new Node(a, b));
		visited[a][b] = true;

		while (!q.isEmpty()) {
			Node temp = q.poll();
			int y = temp.y;
			int x = temp.x;

			for (int i = 0; i < dir.length; i++) {
				int dy = y + dir[i][0];
				int dx = x + dir[i][1];

				if (isOK(dy, dx, y, x)) {
					visited[dy][dx] = true;
					q.add(new Node(dy, dx));
					list.add(new Node(dy, dx));
					sum += map[dy][dx];
				}
			}
		}
	}

	private static boolean isOK(int dy, int dx, int y, int x) {
		if (dy >= 0 && dy < N && dx >= 0 && dx < N) {
			int num = Math.abs(map[dy][dx] - map[y][x]);
			if (!visited[dy][dx] && num >= L && num <= R) {
				return true;
			}
		}
		return false;
	}

	static class Node {
		int y;
		int x;

		public Node(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}
}
