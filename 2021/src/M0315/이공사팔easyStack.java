package M0315;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class 이공사팔easyStack {
	static int N;
	static int map[][];
	static int MAX = Integer.MIN_VALUE;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		map = new int[N][N];
		for (int r = 0; r < N; r++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int c = 0; c < N; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		DFS(0, map);
		System.out.println(MAX);
	}

	private static void DFS(int cnt, int map[][]) {
		if (cnt == 5) {
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
					MAX = Math.max(MAX, map[r][c]);
				}
			}
			return;
		}

		for (int d = 0; d < 4; d++) { // 상, 하, 좌, 우
			DFS(cnt + 1,sol(d, map));
		}
	}

	private static int[][] sol(int d, int map[][]) {
		int copy[][] = new int[N][N];
		switch (d) {
		case 0:
			for (int c = 0; c < N; c++) {
				Stack<Integer> stack = new Stack<>();
				for (int r = N - 1; r >= 0; r--) {
					if (map[r][c] != 0) {
						stack.add(map[r][c]);
					}
				}
				int idx = 0;
				while (!stack.isEmpty()) {
					int cur = stack.pop();
					
					if (!stack.isEmpty() && cur == stack.peek()) {
						copy[idx][c] = 2 * cur;
						stack.pop();
					} else {
						copy[idx][c] = cur;
					}
					idx++;
				}
			}
			break;
		case 1:
			for (int c = 0; c < N; c++) {
				Stack<Integer> stack = new Stack<>();
				for (int r = 0; r < N; r++) {
					if (map[r][c] != 0) {
						stack.add(map[r][c]);
					}
				}
				int idx = N - 1;
				while (!stack.isEmpty()) {
					int cur = stack.pop();
					if (!stack.isEmpty() && cur == stack.peek()) {
						copy[idx][c] = 2 * cur;
						stack.pop();
					} else {
						copy[idx][c] = cur;
					}
					idx--;
				}
			}
			break;
		case 2:
			for (int r = 0; r < N; r++) {
				Stack<Integer> stack = new Stack<>();
				for (int c = N - 1; c >= 0; c--) {
					if (map[r][c] != 0) {
						stack.add(map[r][c]);
					}
				}
				int idx = 0;
				while (!stack.isEmpty()) {
					int cur = stack.pop();
					if (!stack.isEmpty() && cur == stack.peek()) {
						copy[r][idx] = 2 * cur;
						stack.pop();
					} else {
						copy[r][idx] = cur;
					}
					idx++;
				}
			}
			break;
		case 3:
			for (int r = 0; r < N; r++) {
				Stack<Integer> stack = new Stack<>();
				for (int c = 0; c < N; c++) {
					if (map[r][c] != 0) {
						stack.add(map[r][c]);
					}
				}
				int idx = N - 1;
				while (!stack.isEmpty()) {
					int cur = stack.pop();
					if (!stack.isEmpty() && cur == stack.peek()) {
						copy[r][idx] = 2 * cur;
						stack.pop();
					} else {
						copy[r][idx] = cur;
					}
					idx--;
				}
			}
			break;
		}
		return copy;
	}

	private static void Copy(int[][] copymap, int[][] map2) {
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				copymap[r][c] = map2[r][c];
			}
		}
	}
}
