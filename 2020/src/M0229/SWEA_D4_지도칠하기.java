package M0229;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWEA_D4_지도칠하기 {
	static int N;
	static int map[][];
	static int color[];
	static int cnt;
	static boolean visited[];
	static int Max = Integer.MIN_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			map = new int[N][N];
			color = new int[N];
			visited = new boolean[N];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				color[i] = Integer.parseInt(st.nextToken());
			}
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					;
				}
			}
			for (int i = 0; i < N; i++) {
				if (!visited[i]) {
					BFS(i);
				}
			}
			int check = 0;
			for (int i = 0; i < N; i++) {
				if (color[i] == 0) {
					check++;
				}
			}
			System.out.println("#" + tc + " " + check);
		}
	}

	private static void BFS(int index) {
		Queue<Integer> q = new LinkedList<>();
		q.offer(index);

		while (!q.isEmpty()) {
			int now = q.poll();
			visited[now] = true;
			for (int i = 0; i < map[now].length; i++) {
				if (!visited[i] && map[now][i] == 1) {
					if (color[now] == color[i]) {
						color[now] = 0;
					}
					q.offer(i);
				}
			}
		}
	}
}
