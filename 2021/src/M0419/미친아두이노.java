package M0419;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 미친아두이노 {
	static int R, C;
	static char map[][];
	static int[][] dirs = { { 1, -1 }, { 1, 0 }, { 1, 1 }, { 0, -1 }, { 0, 0 }, { 0, 1 }, { -1, -1 }, { -1, 0 },
			{ -1, 1 } };
	static int sr, sc;
	static Queue<Node> aduino;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		map = new char[R][C];
		aduino = new LinkedList<>();
		for (int r = 0; r < R; r++) {
			String line = br.readLine();
			for (int c = 0; c < C; c++) {
				map[r][c] = line.charAt(c);
				if (map[r][c] == 'I') {
					sr = r;
					sc = c;
				} else if (map[r][c] == 'R') {
					aduino.add(new Node(r, c));
				}
			}
		}

		String line = br.readLine();
		boolean flag = true;
		for (int i = 0; i < line.length(); i++) {
			int d = line.charAt(i) - '0' - 1;

			if (!moveJoungSu(d)) {
				System.out.println("kraj " + (i + 1));
				flag = false;
				break;
			}

			if (!Move()) {
				System.out.println("kraj " + (i + 1));
				flag = false;
				break;
			}
			reMap();
		}
		if (flag)
			print();

	}

	private static void print() {
		StringBuilder sb= new StringBuilder();
		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				sb.append(map[r][c]);
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}

	private static void reMap() {
		for (int r = 0; r < R; r++) {
			Arrays.fill(map[r], '.'); // 맵 초기화
		}
		map[sr][sc] = 'I';
		for (Node node : aduino) {
			int r = node.r;
			int c = node.c;

			map[r][c] = 'R';
		}
	}

	private static boolean moveJoungSu(int d) {
		int dr = sr + dirs[d][0];
		int dc = sc + dirs[d][1];

		if (map[dr][dc] == 'R')
			return false;
		else {
			sr = dr;
			sc = dc; // 이동
			return true;
		}
	}

	private static boolean Move() {
		int cnt[][] = new int[R][C];

		while (!aduino.isEmpty()) {
			Node cur = aduino.poll();

			int r = cur.r;
			int c = cur.c;
			int distance = Integer.MAX_VALUE;

			int nr = -1, nc = -1; // 움직일 좌표
			for (int d = 0; d < dirs.length; d++) {
				int dr = r + dirs[d][0];
				int dc = c + dirs[d][1];

				if (isOK(dr, dc)) {
					int dist = Math.abs(dr - sr) + Math.abs(dc - sc);
					if (dist < distance) {
						distance = dist;
						nr = dr;
						nc = dc;
					}
				}
			}
			if (nr == sr && nc == sc) {
				return false; // 움직였는데 정수가 있으면 터진다 (게임 오버)
			}
			
			cnt[nr][nc] += 1; // 여기에 아두이노가 하나 도착하였다 표시
		}

		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				if (cnt[r][c] == 1) {
					aduino.add(new Node(r, c)); // 여러개가 같이 뭉쳐있으면 값을 넣어주지 말자.
				}
			}
		}
		return true;

	}

	private static boolean isOK(int dr, int dc) {
		return dr >= 0 && dr < R && dc >= 0 && dc < C;
	}

	static class Node {
		int r;
		int c;

		public Node(int r, int c) {
			this.r = r;
			this.c = c;
		}

	}
}
