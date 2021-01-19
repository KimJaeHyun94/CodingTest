package M0303;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.util.LinkedList;

import java.util.Queue;
import java.util.StringTokenizer;

public class BJ16236아기상어 {

	static int N;
	static int map[][];
	static int visited[][];
	static int dir[][] = { { 0, -1 }, { 0, 1 }, { 1, 0 }, { -1, 0 } };
	static int size = 2;
	static int starty, startx;
	static int time;
	static int max_val = 401, max_int = 21;
	static int min_x, min_y, min_dist, eat_cnt;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());

		map = new int[N + 1][N + 1];
		visited = new int[N + 1][N + 1];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 9) {
					starty = i;
					startx = j;
					map[i][j] = 0;
				}
			}
		}

		while (true) {
			init_check();

			BFS();

			if (min_x != max_int && min_y != max_int) {
				time += visited[min_y][min_x];

				eat_cnt++;

				if (eat_cnt == size) {
					size++;
					eat_cnt = 0;
				}

				map[min_y][min_x] = 0;

				starty = min_y;
				startx = min_x;
			}

			else {
				break;
			}
		}
		System.out.println(time);
	}

	private static void init_check() {
		min_dist = max_val;
		min_y = max_int;
		min_x = max_int;

		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				visited[i][j] = -1;
			}
		}
	}

	private static void BFS() {
		Queue<shark> q = new LinkedList<>();
		visited[starty][startx] = 0;
		q.add(new shark(starty, startx));

		while (!q.isEmpty()) {
			shark temp = q.poll();
			int y = temp.y;
			int x = temp.x;

			for (int i = 0; i < 4; i++) {
				int dy = y + dir[i][0];
				int dx = x + dir[i][1];

				if (IsOK(dy, dx)) {

					visited[dy][dx] = visited[y][x] + 1;

					if (map[dy][dx] != 0 && map[dy][dx] < size) {

						if (min_dist > visited[dy][dx]) {
							min_y = dy;
							min_x = dx;
							min_dist = visited[dy][dx];
						} else if (min_dist == visited[dy][dx]) {
							if (min_y == dy) {
								if (min_x > dx) {
									min_y = dy;
									min_x = dx;
								}
							} else if (min_y > dy) {
								min_y = dy;
								min_x = dx;
							}
						}

					}
					q.add(new shark(dy, dx));
				}
			}
		}
	}

	private static boolean IsOK(int dy, int dx) {
		if (dy >= 1 && dy <= N && dx >= 1 && dx <= N) {
			if (visited[dy][dx] == -1 && map[dy][dx] <= size) {
				return true;
			}
		}
		return false;
	}

	static class shark {
		int y, x;

		shark(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}
}
