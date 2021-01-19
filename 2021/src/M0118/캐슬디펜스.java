package M0118;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 캐슬디펜스 {
	static int[][] dirs = { { 0, -1 }, { -1, 0 }, { 0, 1 } };
	static int N, M, D;
	static int map[][];
	static int copy[][];
	static int[] bower;
	static ArrayList<int[]> list;
	static int ans;
	static boolean killed[][];
	static boolean visited[][];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());

		map = new int[N + 1][M];
		bower = new int[3];
		
		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < M; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		combination(0, 0);
		System.out.println(ans);
	}

	private static void combination(int start, int r) {
		if (r == 3) { // 궁수 3명 배치 되었다면
			ans = Math.max(ans, sol());
			return;
		}
		for (int i = start; i < M; i++) {
			bower[r] = i; // 궁수 배치
			combination(i + 1, r + 1);
		}
	}

	private static int sol() {
		int cnt = 0;
		copy = new int[N + 1][M];
		deepcopy(); // 초기화 세팅

		for (int i = 0; i < N; i++) {
			killed = new boolean[N+1][M]; // 죽이기
			list = new ArrayList<>();
			for (int j = 0; j < 3; j++) {
				cnt+=bfs(N, bower[j]); // 궁수 한명씩 배치해서 죽이기
			}
			
			for (int[] arr : list) {
				copy[arr[0]][arr[1]] = 0;
			}
			move();
		}

		return cnt;
	}

	private static void move() { // 하나씩 밑으로 이동시킨다
		for (int i = N - 2; i >= 0; i--) {
			for (int j = 0; j < M; j++) {
				copy[i + 1][j] = copy[i][j];
			}
		}
		for (int i = 0; i < M; i++) {
			copy[0][i] = 0;
		}
	}

	private static int bfs(int br, int bc) {
		Queue<Node> q = new LinkedList<>();
		q.add(new Node(br, bc, 0));
		visited = new boolean[N + 1][M];
		visited[br][bc] = true;

		while (!q.isEmpty()) {
			Node temp = q.poll();

			if (temp.d == D)
				return 0;

			for (int d = 0; d < dirs.length; d++) {
				int dr = temp.r + dirs[d][0];
				int dc = temp.c + dirs[d][1];

				if (isOK(dr, dc) && !visited[dr][dc]) {
					if (copy[dr][dc] == 1) {
						if(!killed[dr][dc]) {
							killed[dr][dc] = true;
							list.add(new int[] {dr, dc});
							return 1;
						}
						else {
							return 0;
						}
					}
					q.offer(new Node(dr, dc, temp.d + 1));
					visited[dr][dc] = true;
				}
			}

		}
		return 0;
	}

	private static boolean isOK(int dr, int dc) {
		return dr >= 0 && dr <= N && dc >= 0 && dc < M;
	}

	private static void deepcopy() {
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				copy[r][c] = map[r][c];
			}
		}

	}

	static class Node {
		int r;
		int c;
		int d;

		public Node(int r, int c, int d) {
			this.r = r;
			this.c = c;
			this.d = d;
		}

	}
}