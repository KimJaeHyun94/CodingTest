package M0303;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ14503로봇청소기 {
	static int N, M;
	static int dir[][] = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };
	static int map[][];
	static int ans;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map=new int[N][M];
		st = new StringTokenizer(br.readLine());
		int a = Integer.parseInt(st.nextToken());
		int b = Integer.parseInt(st.nextToken());
		int d = Integer.parseInt(st.nextToken());
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		BFS(a, b, d);
		
		System.out.println(ans);
	}

	private static void BFS(int a, int b, int z) {

		Queue<robot> q = new LinkedList<>();
		q.add(new robot(a, b, z));
		while (!q.isEmpty()) {
			robot now = q.poll();
			int y = now.y;
			int x = now.x;
			int d = now.d;
			boolean flag = false;
			if (map[y][x] == 0) {
				map[y][x] = 2;
				ans++;
			}
			for (int i = 0; i < dir.length; i++) {
				int direction = (d + (3 - i)) % 4;
				int dy = y + dir[direction][0];
				int dx = x + dir[direction][1];

				if (isOK(dy, dx)) {
					if (map[dy][dx] == 0) {
						q.add(new robot(dy, dx, direction));
						flag = true;
						break;
					}
				}
			}
			if (!flag) {
				int by = y - dir[d][0];
				int bx = x - dir[d][1];
				if(isOK(by,bx)) {
					if(map[by][bx]!=1)
						q.add(new robot(by,bx,d));
					else
						break;
				}
			}
		}
	}

	private static boolean isOK(int dy, int dx) {
		if (dy >= 0 && dy < N && dx >= 0 && dx < M) {
			return true;
		}
		return false;
	}

	static class robot {
		int y;
		int x;
		int d;

		public robot(int y, int x, int d) {
			super();
			this.y = y;
			this.x = x;
			this.d = d;
		}
	}
}
