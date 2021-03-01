package M0228;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;
/**
 * 
 * @author 김재현
 * @See Beaverbae Code
 */
public class 백조의호수 {
	static int R, C;
	static int startR = -1, startC, endR, endC;
	static char map[][];
	static boolean visited[][];
	static int[][] dirs = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
	static Queue<Lake> sq = new LinkedList<>();
	static Queue<Lake> q = new LinkedList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		map = new char[R][C];
		visited = new boolean[R][C];
		for (int r = 0; r < R; r++) {
			String line = br.readLine();
			for (int c = 0; c < C; c++) {
				map[r][c] = line.charAt(c);
				if (map[r][c] == 'L') {
					if (startR != -1) {
						endR = r;
						endC = c;
					} else {
						startR = r;
						startC = c;
					}
					map[r][c] = '.';
					q.add(new Lake(r, c));
				} else if (map[r][c] == '.') {
					q.add(new Lake(r, c));
				}
			}
		}
		sq.add(new Lake(startR, startC));
		visited[startR][startC] = true;

		int day = 0; // 날짜 계산
		while (true) {
			if (BFS()) { // 만약에 정답을 찾으면
				break;
			}
			melt(); // 녹이기
			day++;
		}
		System.out.println(day);
	}

	private static boolean BFS() {
		Queue<Lake> newq = new LinkedList<>();
		while (!sq.isEmpty()) {
			Lake temp = sq.poll();
			int r = temp.r;
			int c = temp.c;

			if (r == endR && c == endC) {
				return true;
			}
			for (int d = 0; d < dirs.length; d++) {
				int dr = r + dirs[d][0];
				int dc = c + dirs[d][1];

				if (isOK(dr, dc) && !visited[dr][dc]) {
					visited[dr][dc] = true;
					if (map[dr][dc] == 'X') {
						map[dr][dc] = '.'; // 백조가 갈 수 있는 벽은 모두 다 녹일 수 있다.
						newq.add(new Lake(dr, dc));
						continue;
					} else {
						sq.add(new Lake(dr, dc));
					}
				}
			}
		}
		sq = newq;
		return false;
	}

	private static void melt() {
		int size = q.size();
		for (int i = 0; i < size; i++) {
			Lake temp = q.poll();

			int r = temp.r;
			int c = temp.c;

			for (int d = 0; d < dirs.length; d++) {
				int dr = r + dirs[d][0];
				int dc = c + dirs[d][1];

				if (isOK(dr, dc) && map[dr][dc] == 'X') {
					map[dr][dc] = '.';
					q.add(new Lake(dr, dc));
				}
			}
		}
	}

	private static boolean isOK(int dr, int dc) {
		return dr >= 0 && dr < R && dc >= 0 && dc < C;
	}

	static class Lake {
		int r;
		int c;

		public Lake() {
		}

		public Lake(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
}
