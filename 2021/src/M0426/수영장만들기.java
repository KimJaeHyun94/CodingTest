package M0426;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 수영장만들기 {
	static int N, M;
	static int pool[][];
	static boolean visited[][];
	static int size = 0;
	static int dirs[][] = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		pool = new int[N][M];

		int max = Integer.MIN_VALUE;

		for (int i = 0; i < N; i++) {
			String line = br.readLine();
			for (int j = 0; j < M; j++) {
				pool[i][j] = line.charAt(j) - '0';
				max = Math.max(pool[i][j], max);
			}
		}

		int h = 2; // 물을 부을 높이
		int ans = 0;

		while (true) {
			visited = new boolean[N][M];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (!visited[i][j] && pool[i][j] < h) {
						ans += BFS(i, j, h);
					}
				}
			}
			h++;
			if (h > max)
				break;
		}

		System.out.println(ans);
	}

	private static int BFS(int r, int c, int h) {
		visited[r][c] = true;
		Queue<Node> q = new LinkedList<>();
		q.add(new Node(r, c));
		int size = 1;
		boolean flag = true;
		outer: while (!q.isEmpty()) {
			Node cur = q.poll();
	
			for (int d = 0; d < dirs.length; d++) {
				int dr = cur.r + dirs[d][0];
				int dc = cur.c + dirs[d][1];
				
				if (!isOK(dr, dc)) {
					flag = false;
					continue;
				}
				if (isOK(dr, dc) && !visited[dr][dc] && pool[dr][dc] < h) {
					size++;
					q.add(new Node(dr, dc));
					visited[dr][dc] = true;
				}
			}
		}
		if (flag)
			return size;
		else
			return 0;
	}

	private static boolean isOK(int dr, int dc) {
		return dr >= 0 && dr < N && dc >= 0 && dc < M;
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
