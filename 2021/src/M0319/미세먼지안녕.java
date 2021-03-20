package M0319;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 미세먼지안녕 {
	static int R, C, T;
	static int map[][];
	static int sr = -1, er;
	static int dirs[][] = { { 0, 1 }, { -1, 0 }, { 0, -1 }, { 1, 0 } };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());

		map = new int[R][C];

		for (int r = 0; r < R; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < C; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
				if (map[r][c] == -1) {
					if (sr == -1) {
						sr = r;
					} else {
						er = r;
					}
				}
			}
		}

		while (T-- > 0) {
			ArrayList<Node> dust = new ArrayList<>();
			for (int r = 0; r < R; r++) {
				for (int c = 0; c < C; c++) {
					if (map[r][c] > 0) {
						dust.add(new Node(r, c, map[r][c])); // 미세먼지들을 넣기
					}
				}
			}
			for (Node node : dust) {
				int cnt = 0;
				int r = node.r;
				int c = node.c;
				int fine = node.w; // 원래 가지고 있는 미세먼지

				for (int d = 0; d < 4; d++) {
					int dr = r + dirs[d][0];
					int dc = c + dirs[d][1];

					if (isOK(dr, dc) && map[dr][dc] !=-1) { // 격자 안에 있고 공기청정기가 설치되지 않으면서 확산시킬 수 있는 곳이라면
						cnt++;
						map[dr][dc] += fine / 5;
					}
				}
				map[r][c] -= (fine / 5) * cnt;
			}
			
		
			Rotate();
		}

		int sum = 0;
		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				sum += map[r][c];
			}
		}
		System.out.println(sum + 2);
	}
	private static void Rotate() {
		for (int r = sr - 1; r > 0; r--) { // 아래쪽
			map[r][0] = map[r - 1][0];
		}
		for (int c = 0; c < C - 1; c++) { // 왼쪽
			map[0][c] = map[0][c + 1];
		}
		for (int r = 0; r < sr; r++) { // 위쪽
			map[r][C - 1] = map[r + 1][C - 1];
		}
		for (int c = C - 1; c > 1; c--) {
			map[sr][c] = map[sr][c - 1];
		}
		map[sr][1] = 0; // 처음 시작 방향은 0

		for (int r = er + 1; r < R - 1; r++) { // 위쪽
			map[r][0] = map[r + 1][0];
		}
		for (int c = 0; c < C - 1; c++) { // 왼쪽
			map[R - 1][c] = map[R - 1][c + 1];
		}
		for (int r = R - 1; r > er; r--) { // 아래쪽
			map[r][C-1] = map[r - 1][C - 1];
		}
		for (int c = C - 1; c > 1; c--) {
			map[er][c] = map[er][c - 1];
		}
		map[er][1] = 0;
	}

	private static boolean isOK(int dr, int dc) {
		return dr >= 0 && dr < R && dc >= 0 && dc < C;
	}

	static class Node {
		int r;
		int c;
		int w;

		public Node(int r, int c, int w) {
			this.r = r;
			this.c = c;
			this.w = w;
		}
	}
}
