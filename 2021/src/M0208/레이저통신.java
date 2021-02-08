package M0208;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class 레이저통신 {
	static int W, H;
	static int dirs[][] = { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 } };
	static int map[][];
	static boolean flag;
	static int StartR, StartC, EndR, EndC;
	static int Min = Integer.MAX_VALUE;
	static boolean visited[][];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		W = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());

		map = new int[H][W];
		flag = false;
		for (int r = 0; r < H; r++) {
			String line = br.readLine();
			for (int c = 0; c < W; c++) {
				map[r][c] = line.charAt(c);
				if (map[r][c] == 'C') {
					if (flag) {
						EndR = r;
						EndC = c;
					} else {
						flag = true;
						StartR = r;
						StartC = c;
					}
				}
			}
		}
		System.out.println(BFS());
	}

	private static int BFS() {
		Queue<Node> q = new PriorityQueue<>();
		boolean visited[][][] = new boolean[H][W][4];
		q.add(new Node(StartR, StartC, -1, 0));

		while (!q.isEmpty()) {
			Node temp = q.poll();
			int r = temp.r;
			int c = temp.c;
			int dir = temp.dir;
			int depth = temp.depth;

			if (r == EndR && c == EndC) {
				return depth;
			}
			if (dir == -1) { // 처음에 출발할때 거울 설치
				for (int d = 0; d < dirs.length; d++) {
					int dr = r + dirs[d][0];
					int dc = c + dirs[d][1];

					if (isOK(dr, dc) && map[dr][dc] != '*') {
						q.add(new Node(dr, dc, d, 0));
					}
				}
			} else {
				if (visited[r][c][dir])
					continue;
				visited[r][c][dir] = true;
				
				for (int d = 0; d < dirs.length; d++) {
					int nr = r + dirs[d][0];
					int nc = c + dirs[d][1];
					if (isOK(nr, nc) && map[nr][nc] != '*') {
						if (dir == d) {
							q.add(new Node(nr, nc, dir, depth));

						} else {
							q.add(new Node(nr, nc, d, depth + 1));
						}
					}
				}
			}
		}
		return 0;
	}

	private static boolean isOK(int dr, int dc) {
		return dr >= 0 && dr < H && dc >= 0 && dc < W;
	}

	static class Node implements Comparable<Node> {
		int r;
		int c;
		int dir;
		int depth; // 거울 갯수

		public Node(int r, int c, int dir, int depth) {
			this.r = r;
			this.c = c;
			this.dir = dir;
			this.depth = depth;
		}

		@Override
		public int compareTo(Node arg0) {
			return Integer.compare(this.depth, arg0.depth);
		}

	}
}
