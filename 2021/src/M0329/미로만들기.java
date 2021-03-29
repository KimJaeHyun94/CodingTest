package M0329;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class 미로만들기 {
	static int map[][];
	static int N;
	static ArrayList<Node> black;
	static int min = 0;
	static int dirs[][] = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		map = new int[N][N];
		black = new ArrayList<>();
		for (int r = 0; r < N; r++) {
			String line = br.readLine();
			for (int c = 0; c < N; c++) {
				map[r][c] = line.charAt(c) - '0';
				if (map[r][c] == 0) {
					black.add(new Node(r, c));
				}
			}
		}

		for (int i = 0; i < black.size(); i++) {
			int temp[][] = Copy(map);
			permutation(0, i, temp);
		}

		System.out.println(black.size());
	}

	private static int[][] Copy(int[][] map) {
		int temp[][] = new int[N][N];
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				temp[r][c] = map[r][c];
			}
		}
		return temp;
	}

	private static void permutation(int cnt, int limit, int temp[][]) {
		if(cnt>limit) return;
		if (cnt == limit) {
			//print(temp);
			if (BFS(temp)) {
				System.out.println(limit);
				System.exit(0);
			}
		}

		for (Node node : black) {
			if (temp[node.r][node.c] == 0) {
				temp[node.r][node.c] = 1;
				permutation(cnt + 1, limit, temp);
				temp[node.r][node.c] = 0;
			}
		}

	}

	private static void print(int[][] temp) {
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				System.out.print(temp[r][c]);
			}
			System.out.println();
		}
	}

	private static boolean BFS(int[][] temp) {
		Queue<Node> q = new LinkedList<>();
		q.add(new Node(0, 0));
		boolean visited[][] = new boolean[N][N];
		visited[0][0] = true;

		while (!q.isEmpty()) {
			Node cur = q.poll();

			if (cur.r == N - 1 && cur.c == N - 1) {
				return true;
			}
			for (int d = 0; d < dirs.length; d++) {
				int dr = cur.r + dirs[d][0];
				int dc = cur.c + dirs[d][1];
				if (isOK(dr, dc) && !visited[dr][dc] && temp[dr][dc] == 1) {
					q.add(new Node(dr, dc));
					visited[dr][dc] = true;
				}
			}
		}
		return false;
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
