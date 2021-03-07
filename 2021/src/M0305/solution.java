package M0305;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class solution {
	static int N, M;
	static int map[][];
	static ArrayList<Node> two;
	static int MAX;
	static boolean visited[][];
	static boolean visited0[][];
	static int dirs[][] = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
	static ArrayList<Node> zero;
	static int[] selected_blank;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		MAX = 0;
		two = new ArrayList<>();
		zero = new ArrayList<>();
		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < M; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
				if (map[r][c] == 2) {
					two.add(new Node(r, c));
				}
			}
		}

		if (two.isEmpty()) {
			System.out.println(0);
			System.exit(0);
		}

		visited = new boolean[N][M];
		visited0 = new boolean[N][M];
		for (Node child : two) {
			if (!visited[child.r][child.c]) {
				findZero(child);
			}
		}
		selected_blank = new int[2];
		combination(0, 0);
		System.out.println(MAX);
	}

	private static void findZero(Node start) {
		Queue<Node> q = new LinkedList<>();
		q.add(start);
		visited[start.r][start.c] = true;

		while (!q.isEmpty()) {
			Node temp = q.poll();

			for (int d = 0; d < 4; d++) {
				int dr = temp.r + dirs[d][0];
				int dc = temp.c + dirs[d][1];

				if (isOK(dr, dc)) {
					if (map[dr][dc] == 2 && !visited[dr][dc]) {
						visited[dr][dc] = true;
						q.add(new Node(dr, dc));
					} else if (map[dr][dc] == 0 && !visited0[dr][dc]) {
						visited0[dr][dc] = true;
						zero.add(new Node(dr, dc));
					}
				}
			}
		}
	}

	private static void combination(int start, int count) {
		if (count == 2) {
			sol();
			return;
		}

		for (int i = start; i < zero.size(); i++) {
			selected_blank[count] = i;
			combination(i + 1, count + 1);
		}
	}

	private static void sol() {

		for (int i = 0; i < selected_blank.length; i++) {
			int idx = selected_blank[i];
			Node p = zero.get(idx);
			map[p.r][p.c] = 1;
		}
		visited = new boolean[N][M]; // 초기화
		int cnt = 0;
		for (Node child : two) {
			if (!visited[child.r][child.c]) {
				cnt += BFS(child);
			}
		}
		MAX = Math.max(MAX, cnt);
		for (int i = 0; i < selected_blank.length; i++) {
			int idx = selected_blank[i];
			Node p = zero.get(idx);
			map[p.r][p.c] = 0;
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