package M1130;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 연구소2{
	static int arr[][];
	static int N, M, count = 0;
	static int[][] dirs = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };
	static int MIN = Integer.MAX_VALUE;
	private static ArrayList<Node> virus = new ArrayList<>();
	private static ArrayList<Node> realvirus = new ArrayList<>();
	private static boolean[][] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		arr = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				if (arr[i][j] == 2) {
					virus.add(new Node(i, j));
					count++;
				}
				if (arr[i][j] == 0)
					count++;
			}
		}
		combination(0, 0);

		if (MIN == Integer.MAX_VALUE) {
			System.out.println(-1);
		} else
			System.out.println(MIN);
	}

	private static void combination(int d, int s) {
		if (s == M) {
			MIN = Math.min(MIN, BFS());
			return;
		}
		for (int i = d; i < virus.size(); i++) {
			realvirus.add(virus.get(i));
			combination(i + 1, s + 1);
			realvirus.remove(virus.get(i));
		}

	}

	private static int BFS() {
		int check = count; // 빈 곳을 저장해놓는다.
		Queue<Sol> q = new LinkedList<Sol>();
		visited = new boolean[N][N];

		for (int i = 0; i < realvirus.size(); i++) {
			int x = realvirus.get(i).x;
			int y = realvirus.get(i).y;

			q.add(new Sol(x, y, 0));
			visited[x][y] = true;
			check--; // 바이러스가 있는 곳 빼준다
		}

		if (check == 0) {
			return 0;
		}
		while (!q.isEmpty()) {
			Sol temp = q.poll();
			int x = temp.x;
			int y = temp.y;
			int dep = temp.d;
			for (int d = 0; d < dirs.length; d++) {
				int dx = x + dirs[d][0];
				int dy = y + dirs[d][1];

				if (iSOK(dx, dy)) {
					q.add(new Sol(dx, dy, dep + 1));
					visited[dx][dy]=true;
					check--;
				}
			}
			if(check==0) return dep+1;
		}
		return Integer.MAX_VALUE;
	}

	private static boolean iSOK(int dx, int dy) {
		return dx >= 0 && dx < N && dy >= 0 && dy < N && !visited[dx][dy] && arr[dx][dy] != 1;
	}

	static class Node {
		int x;
		int y;

		public Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	static class Sol {
		int x;
		int y;
		int d;

		public Sol(int x, int y, int d) {
			this.x = x;
			this.y = y;
			this.d = d;
		}

	}
}