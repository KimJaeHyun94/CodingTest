package M0416;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 모래성 {
	static int H, W;
	static int map[][];
	static Queue<Node> q = new LinkedList<>();
	static int dirs[][] = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 }, { -1, -1 }, { -1, 1 }, { 1, 1 }, { 1, -1 } };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		H = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());

		map = new int[H][W];

		for (int r = 0; r < H; r++) {
			String line = br.readLine();
			for (int c = 0; c < W; c++) {
				if (line.charAt(c) == '.') {
					map[r][c] = 0;
					q.add(new Node(r, c));
				} else {
					map[r][c] = line.charAt(c) - '0';
				}
			}
		}
		BFS();
	}

	private static void BFS() {
		int time = 0;
		while (true) {
			int size = q.size();
			while (size-- > 0) {
				Node cur = q.poll();
				
				for (int d = 0; d < dirs.length; d++) {
					int dr = cur.r+dirs[d][0];
					int dc = cur.c+dirs[d][1];
					
					if(isOK(dr,dc) && map[dr][dc]>0) {
						map[dr][dc]-=1;
						if(map[dr][dc]==0) {
							q.add(new Node(dr,dc));
						}
					}
				}
			}
			if (q.isEmpty())
				break;
			time++;
		}
		System.out.println(time);
	}

	private static boolean isOK(int dr, int dc) {
		return dr>=0 && dr<H && dc>=0 && dc<W;
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
