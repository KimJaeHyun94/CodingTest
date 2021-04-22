package M0417;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class 불켜기DFS {
	static int N, M;
	static int map[][];
	static int dirs[][] = { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 } };
	static List<Node> graph[][];
	static int cnt = 1;
	static boolean visited[][];
	static boolean light[][];
	static boolean move[][];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		graph = new List[N][N];
		visited = new boolean[N][N];
		light = new boolean[N][N];
		move = new boolean[N][N];
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				graph[i][j] = new ArrayList<>();
			}

		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken()) - 1;
			int c = Integer.parseInt(st.nextToken()) - 1;
			int r1 = Integer.parseInt(st.nextToken()) - 1;
			int c1 = Integer.parseInt(st.nextToken()) - 1;

			graph[r][c].add(new Node(r1, c1));

		}
		light[0][0] = true;
		DFS(0, 0);

		System.out.println(cnt);
	}

	private static void DFS(int r, int c) {
		visited[r][c] = true;
		for (int d = 0; d < dirs.length; d++) { // 이동 가능한 곳 알아보기
			int dr = r + dirs[d][0];
			int dc = c + dirs[d][1];

			if (isOK(dr, dc) && !visited[dr][dc]) {
				move[dr][dc] = true;
			}

		}
		if (!graph[r][c].isEmpty()) { // 현재 위치에서 불 켤 수 있는곳 켜기
			for (Node child : graph[r][c]) {
				if (!light[child.r][child.c]) {
					light[child.r][child.c] = true;
					cnt++;

					if (move[child.r][child.c] && !visited[child.r][child.c]) { // 불을 켤수있는데 움직일 수 있다면 움직이기 ->
																				// (2,1)좌표
						DFS(child.r, child.c);
					}
				}
			}
		}

		for (int d = 0; d < dirs.length; d++) { // 이동할 수 있는 불켜진곳 이동하기
			int dr = r + dirs[d][0];
			int dc = c + dirs[d][1];

			if (isOK(dr, dc) && !visited[dr][dc] && light[dr][dc] && move[dr][dc]) {
				visited[dr][dc] = true;
				DFS(dr, dc);
			}

		}
	}

	private static boolean isOK(int dr, int dc) {
		return dr >= 0 && dr < N && dc >= 0 && dc < N;
	}

	static class Node {
		int r;
		int c;

		public Node(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}

	}
}
