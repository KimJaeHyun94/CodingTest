package M1205;

import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 마법사상어와토네이도 {
	static int N;
	static int map[][];
	static int wx[][] = { { -1, 1, -1, 1, -1, 1, -2, 2, 0 }, // LEFT
			{ -1, -1, 0, 0, 1, 1, 0, 0, 2 }, // DOWN
			{ -1, 1, -1, 1, -1, 1, -2, 2, 0 }, // RIGHT
			{ 1, 1, 0, 0, -1, -1, 0, 0, -2 }, // UP
	};

	static int wy[][] = { { 1, 1, 0, 0, -1, -1, 0, 0, -2 }, // LEFT
			{ -1, 1, -1, 1, -1, 1, -2, 2, 0 }, // DOWN
			{ -1, -1, 0, 0, 1, 1, 0, 0, 2 }, // RIGHT
			{ -1, 1, -1, 1, -1, 1, -2, 2, 0 }, // UP
	};
	static int dirs[][] = { { 0, -1 }, { 1, 0 }, { 0, 1 }, { -1, 0 } };
	static int per[] = { 1, 1, 7, 7, 10, 10, 2, 2, 5 };
	static int ans;
	static Node windy;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		map = new int[N][N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		windy = new Node(N / 2, N / 2, 0);
		int len = 1;

		for (int i = 1; i < N; ++i) {
			for (int j = 1; j <= 2; ++j) {
				moveWindy(len);
				windy.dir = (windy.dir + 1) % 4;
			}
			len++;
		}
		moveWindy(len - 1);
		System.out.println(ans);
	}

	private static void moveWindy(int len) {
		for (int d = 0; d < len; d++) {
			windy.r += dirs[windy.dir][0];
			windy.c += dirs[windy.dir][1];
			Sand();
		}

	}

	private static void Sand() {
		int val = map[windy.r][windy.c];
		for (int d = 0; d < 9; d++) {
			int r = windy.r + wx[windy.dir][d];
			int c = windy.c + wy[windy.dir][d];

			int sand = (val * per[d]) / 100;
			map[windy.r][windy.c] -= sand;

			if (!isOK(r, c)) {
				ans += sand;
				continue;
			}
			map[r][c] += sand;
		}
		
		int r = windy.r+dirs[windy.dir][0];
		int c = windy.c+dirs[windy.dir][1];
		if(!isOK(r,c)) {
			ans+=map[windy.r][windy.c];
			map[windy.r][windy.c]=0;
			return;
		}
		map[r][c] +=map[windy.r][windy.c];
		map[windy.r][windy.c]=0;
	}

	private static boolean isOK(int r, int c) {
		return r >= 0 && r < N && c >= 0 && c < N;
	}

	static class Node {
		int r;
		int c;
		int dir;

		public Node(int r, int c, int dir) {
			this.r = r;
			this.c = c;
			this.dir = dir;
		}

	}
}
