package M0414;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ2638_치즈 {
	static int N, M;
	static int map[][];
	static int time;
	static int result;
	static int visited[][];
	static int dir[][] = { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 } };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		visited = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		boolean flag = true;
		time = 0;
		while (flag) {
			visited = new int[N][M];
			BFS();
			
			flag = false;

			loop: for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (map[i][j] != 0) {
						flag = true;
						break loop;
					}
				}
			}
			time++;
		}
		System.out.println(time);
	}

	private static void BFS() {
		Queue<Cheese> q = new LinkedList<>();
		q.add(new Cheese(0, 0));

		while (!q.isEmpty()) {
			Cheese temp = q.poll();

			int y = temp.y;
			int x = temp.x;
			for (int i = 0; i < dir.length; i++) {
				int dy = y + dir[i][0];
				int dx = x + dir[i][1];
				if (isOK(dy, dx)) {
					if (map[dy][dx] == 0 && visited[dy][dx] == 0) {
						visited[dy][dx] = 1;
						q.add(new Cheese(dy, dx));
						
					}
					if (map[dy][dx] == 1) {
						visited[dy][dx]++;
						if (visited[dy][dx] >= 2)
							map[dy][dx] = 0;
					}
				}
			}
		}
	}

	private static boolean isOK(int dy, int dx) {
		return dy >= 0 && dy < N && dx >= 0 && dx < M;
	}

	static class Cheese {
		int y;
		int x;

		public Cheese(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}
}