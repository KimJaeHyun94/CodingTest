package M0323;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class 배열돌리기4 {
	static int N, M, K;
	static int map[][];
	static boolean visited[];
	static Node[] Route;
	static int MIN = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		map = new int[N][M];

		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < M; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		visited = new boolean[K];
		Route = new Node[K];

		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken()) - 1;
			int c = Integer.parseInt(st.nextToken()) - 1;
			int s = Integer.parseInt(st.nextToken());
			Route[i] = new Node(r, c, s);
		}
		DFS(0, new Node[K]);
		System.out.println(MIN);
	}

	private static void DFS(int cnt, Node[] temp) {
		if (cnt == K) {
			MIN = Math.min(MIN, sol(temp));
			return;
		}

		for (int i = 0; i < K; i++) {
			if (!visited[i]) {
				visited[i] = true;
				temp[cnt] = Route[i];
				DFS(cnt + 1, temp);
				visited[i] = false;
			}
		}
	}

	private static int sol(Node[] temp) {
		int[][] copy = Copy(map);
		
		for (int i = 0; i < K; i++) {
			Node cur = temp[i];
			int r = cur.r;
			int c = cur.c;
			int s = cur.s;

			for (int j = 1; j <= s; j++) { // 0은 가운데여서 안돈다.
				int sr = r - j;
				int sc = c - j; // 가장 왼쪽
				int er = r + j;
				int ec = c + j; // 가장 오른쪽

				int fi = copy[sr][sc]; // 맨 처음 값 체크해놓기
				
				
				for (int k = sr; k <= er - 1; k++) {
					copy[k][sc] = copy[k + 1][sc];
				}

				for (int k = sc; k <= ec - 1; k++) {
					copy[er][k] = copy[er][k + 1];
				}

				for (int k = er; k > sr; k--) {
					copy[k][ec] = copy[k - 1][ec];
				}

				for (int k = ec; k > sc; k--) {
					copy[sr][k] = copy[sr][k - 1];
				}
				copy[sr][sc + 1] = fi;
			}
			for (int j = 0; j < N; j++) {
				int sum = 0;
				for (int j2 = 0; j2 < M; j2++) {
				}
			}
		}
		int min = Integer.MAX_VALUE;
		for (int j = 0; j < N; j++) {
			int sum = 0;
			for (int j2 = 0; j2 < M; j2++) {
				sum += copy[j][j2];
			}
			min = Math.min(min, sum);
		}
		return min;
	}

	private static int[][] Copy(int[][] map) {
		int[][] copy = new int[N][M];
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				copy[r][c] = map[r][c];
			}
		}
		return copy;
	}

	static class Node {
		int r;
		int c;
		int s;

		public Node(int r, int c, int s) {
			super();
			this.r = r;
			this.c = c;
			this.s = s;
		}

	}

}
