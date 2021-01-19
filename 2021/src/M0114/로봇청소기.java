package M0114;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
/*
 * @See https://velog.io/@pss407/%EB%B0%B1%EC%A4%804991-%EB%A1%9C%EB%B4%87-%EC%B2%AD%EC%86%8C%EA%B8%B0
 * @Author AKKabiyo
 * 
 */
public class 로봇청소기 {
	static int W, H;
	static int map[][];
	static boolean flag;
	static int ans;
	static robot[] dirty;
	static int[][] dirs = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };
	static int[][] graph;
	static boolean[] selected;
	static int[] arr;
	static int idx;

	public static void main(String[] args) throws IOException {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		while (true) {

			StringTokenizer st = new StringTokenizer(br.readLine());
			W = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());
			dirty = new robot[11]; // 최소 10개 이하이므로

			if (W == 0 && H == 0)
				break;
			flag = true;
			map = new int[H][W];
			idx = 1;
			ans = Integer.MAX_VALUE;
			for (int i = 0; i < H; i++) {
				String line = br.readLine();
				for (int j = 0; j < W; j++) {
					map[i][j] = line.charAt(j);
					if (map[i][j] == '*')
						dirty[idx++] = new robot(i, j, 0);
					if (map[i][j] == 'o') {
						dirty[0] = new robot(i, j, 0);
					}
				}
			}

			graph = new int[idx][idx];
			for (int i = 0; i < idx - 1; i++) {
				for (int j = i + 1; j < idx; j++) {
					int len = BFS(dirty[i], dirty[j]);
					if (len == -1) {
						ans = -1;
						flag = false;
					}
					graph[i][j] = len;
					graph[j][i] = len;
				}
			}
			if (!flag) {
				sb.append(ans).append("\n");
				continue;
			}

			selected = new boolean[idx];
			arr = new int[idx];
			selected[0] = true;
			arr[0] = 0;
			permutation(1);
			sb.append(ans).append("\n");
		}
		System.out.println(sb);
	}

	private static void permutation(int cnt) {
		if (cnt == idx) {
			int sum = 0;
			for (int i = 0; i < idx - 1; i++) {
				sum += graph[arr[i]][arr[i + 1]];
			}
			ans = Math.min(ans, sum);
			return;
		}
		for (int i = 0; i < idx; i++) {
			if (!selected[i]) {
				selected[i] = true;
				arr[cnt] = i;
				permutation(cnt + 1);
				selected[i] = false;
			}
		}
	}

	private static int BFS(robot start, robot end) {
		Queue<robot> q = new LinkedList<>();
		q.add(new robot(start.r, start.c, 0));
		boolean[][] visited = new boolean[H][W];
		visited[start.r][start.c] = true;

		while (!q.isEmpty()) {
			robot temp = q.poll();

			for (int d = 0; d < dirs.length; d++) {
				int dr = temp.r + dirs[d][0];
				int dc = temp.c + dirs[d][1];

				if (isOK(dr, dc) && map[dr][dc] != 'x' && !visited[dr][dc]) {
					if (dr == end.r && dc == end.c) {
						return temp.d + 1;
					}
					visited[dr][dc] = true;
					q.add(new robot(dr, dc, temp.d + 1));
				}
				else continue;
			}
		}
		return -1;
	}

	private static boolean isOK(int dr, int dc) {
		return dr >= 0 && dr < H && dc >= 0 && dc < W;
	}

	static class robot {
		int r;
		int c;
		int d;

		public robot(int r, int c, int d) {
			this.r = r;
			this.c = c;
			this.d = d;
		}

	}
}
