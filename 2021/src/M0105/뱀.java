package M0105;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.StringTokenizer;

public class 뱀 {
	static int[][] dirs = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };
	static int N, K;
	static int[][] arr;
	static Map<Integer, String> dirmap;
	static int time;
	static Deque<node> snake;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new int[N][N];
		dirmap = new HashMap<>();
		int K = Integer.parseInt(br.readLine());
		for (int i = 0; i < K; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken()) - 1;
			int c = Integer.parseInt(st.nextToken()) - 1;
			arr[r][c] = 1;
		}
		int L = Integer.parseInt(br.readLine());
		for (int i = 0; i < L; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int time = Integer.parseInt(st.nextToken());
			String dir = st.nextToken();
			dirmap.put(time, dir);
		}
		sol();
		System.out.println(time);
	}

	private static void sol() {
		snake = new LinkedList<>();
		snake.add(new node(0, 0, 0));

		int x = 0;
		int y = 0;
		int dir = 0;
		time = 1;
		arr[0][0] = 2; // 처음 시작에 뱀 표시
		
		while (true) {

			int dx = x + dirs[dir][0];
			int dy = y + dirs[dir][1];

			if (!isOK(dx, dy) || arr[dx][dy] == 2) {
				break;
			}
			if (dirmap.containsKey(time)) { // 그 시간에 방향을 바꿔야한다면
				if (dirmap.get(time).equals("D")) { // 동쪽
					dir = (dir + 1) % 4;
				} else { // 서쪽
					dir = (dir + 3) % 4;
				}
			}

			if (arr[dx][dy] == 1) { // 사과가 있다면
				snake.add(new node(dx, dy, dir));
				arr[dx][dy] = 2; // 먹는다(뱀이 지나감)
			} else { // 암것도 없다면
				snake.add(new node(dx, dy, dir));
				arr[dx][dy] = 2;
				node temp = snake.removeFirst(); // 처음꺼 없애버린다
				arr[temp.r][temp.c] = 0; // 원상 복구한다
			}

			x = dx;
			y = dy;
			time++;
		}
	}

	private static boolean isOK(int dx, int dy) {
		return dx >= 0 && dx < N && dy >= 0 && dy < N;
	}

	static class node {
		int r;
		int c;
		int dir;

		public node(int r, int c, int dir) {
			this.r = r;
			this.c = c;
			this.dir = dir;
		}

	}
}
