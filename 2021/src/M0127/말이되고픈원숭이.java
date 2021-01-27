package M0127;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 말이되고픈원숭이 {
	static int K;
	static int arr[][];
	static int R, C;
	static boolean visited[][][];
	static int ans = -1;
	static int dirs[][] = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
	static int horse[][] = { { -2, -1 }, { -2, 1 }, { -1, -2 }, { -1, 2 }, { 1, -2 }, { 1, 2 }, { 2, 1 }, { 2, -1 } };

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		K = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		C = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());

		arr = new int[R][C];
		visited = new boolean[R][C][K + 1];
		for (int r = 0; r < R; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < C; c++) {
				arr[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		BFS();
		System.out.println(ans);
	}

	private static void BFS() {
		Queue<monkey> q = new LinkedList<>();
		q.add(new monkey(0, 0, 0, K)); // 처음 시작점
		visited[0][0][K] = true;

		while (!q.isEmpty()) {
			monkey temp = q.poll();
			if (temp.r == R - 1 && temp.c == C - 1) {
				ans = temp.d;
				return;
			}

			for (int d = 0; d < dirs.length; d++) {
				int dr = temp.r + dirs[d][0];
				int dc = temp.c + dirs[d][1];

				if (isOK(dr, dc) && !visited[dr][dc][temp.k] && arr[dr][dc] != 1) {
					visited[dr][dc][temp.k] = true;
					q.add(new monkey(dr, dc, temp.d + 1, temp.k));
				}
			}
			if (temp.k < 1)	//가지치기
				continue;
			
			for (int h = 0; h < horse.length; h++) {
				int dr = temp.r + horse[h][0];
				int dc = temp.c + horse[h][1];

				if (isOK(dr, dc) && !visited[dr][dc][temp.k - 1] && arr[dr][dc] != 1) {
					visited[dr][dc][temp.k - 1] = true;
					q.add(new monkey(dr, dc, temp.d + 1, temp.k - 1));
				}
			}
		}
		return;
	}

	private static boolean isOK(int dr, int dc) {
		return dr >= 0 && dr < R && dc >= 0 && dc < C;
	}

	static class monkey { // 원숭이 (좌표, 깊이, k값)
		int r;
		int c;
		int d;
		int k;

		monkey(int r, int c, int d, int k) {
			this.r = r;
			this.c = c;
			this.d = d;
			this.k = k;
		}
	}
}
