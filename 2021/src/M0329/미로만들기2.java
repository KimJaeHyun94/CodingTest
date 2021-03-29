package M0329;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.util.LinkedList;
import java.util.Queue;

public class 미로만들기2 {
	static int map[][];
	static int N;
	static int min = 0;
	static int dirs[][] = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
	static int visited[][];

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		map = new int[N][N];
		visited = new int[N][N];
		for (int r = 0; r < N; r++) {
			String line = br.readLine();
			for (int c = 0; c < N; c++) {
				map[r][c] = line.charAt(c) - '0';
				visited[r][c] = Integer.MAX_VALUE;
			}
		}
		BFS();
		System.out.println(visited[N - 1][N - 1]);
	}

	private static void BFS() {
		Queue<Node> q = new LinkedList<>();
		q.add(new Node(0, 0));
	
		while (!q.isEmpty()) {
			Node cur = q.poll();

			for (int d = 0; d < dirs.length; d++) {
				int dr = cur.r + dirs[d][0];
				int dc = cur.c + dirs[d][1];
				if (isOK(dr, dc)) {
					if (map[dr][dc] == 1 && visited[dr][dc] > visited[cur.r][cur.c]) {
						q.add(new Node(dr, dc));
						visited[dr][dc] = visited[cur.r][cur.c];
					} else if (map[dr][dc] == 0 && visited[dr][dc] > visited[cur.r][cur.c]) {
						q.add(new Node(dr, dc));
						visited[dr][dc] = visited[cur.r][cur.c];
					}
				}
			}
		}

	}

	private static boolean isOK(int dr, int dc) {
		return dr >= 0 && dr < N && dc >= 0 && dc < N;
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
