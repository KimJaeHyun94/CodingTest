package M0305;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Baaaaaaaaaduk2 {
	static int N, M;
	static int map[][];
	static ArrayList<Node> zero, two;
	static int MAX;
	static boolean visited[][];
	static int dirs[][] = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		MAX = Integer.MIN_VALUE;
		two = new ArrayList<>();
		zero = new ArrayList<>();
		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < M; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
				if (map[r][c] == 2) {
					two.add(new Node(r, c));
				} else if (map[r][c] == 0) {
					zero.add(new Node(r, c));
				}
			}
		}

		permutation(0);
		System.out.println(MAX);
	}

	private static void permutation(int count) {
		if (count == 2) {
			visited = new boolean[N][M]; // 초기화
			int cnt = 0;
			for (Node child : two) {
				if (!visited[child.r][child.c]) {
					cnt += BFS(child);
				}
			}
			MAX = Math.max(MAX, cnt);
			return;
		}

		for (Node child : zero) {
			map[child.r][child.c] = 1;
			permutation(count + 1);
			map[child.r][child.c] = 0;
		}
	}

	private static int BFS(Node start) {
		Queue<Node> q = new LinkedList<>();
		q.add(start);
		visited[start.r][start.c] = true;
		int cnt = 1;
		boolean flag = true;
		while (!q.isEmpty()) {
			Node temp = q.poll();

			for (int d = 0; d < 4; d++) {
				int dr = temp.r + dirs[d][0];
				int dc = temp.c + dirs[d][1];

				if (isOK(dr, dc) && !visited[dr][dc]) {
					if (map[dr][dc] == 2) {
						visited[dr][dc] = true;
						cnt++;
						q.add(new Node(dr, dc));
					} else if (map[dr][dc] == 0) {
						flag = false;
					}
				}
			}
		}
		if (flag)
			return cnt;
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
