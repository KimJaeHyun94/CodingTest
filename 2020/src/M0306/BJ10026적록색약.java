package M0306;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ10026적록색약 {
	static int N;
	static char map[][];
	static boolean visited[][];
	static int dir[][] = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
	static int cnt,cnt2;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		map = new char[N][N];
		visited = new boolean[N][N];
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < N; j++) {
				map[i][j] = str.charAt(j);
			}
		}
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (!visited[i][j]) {
					BFS(i, j);
					cnt++;
				}
			}
		}
		visited = new boolean[N][N];
		for (int i = 0; i <N; i++) {
			for (int j = 0; j < N; j++) {
				if(map[i][j]=='R')
					map[i][j]='G';
			}
		}
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (!visited[i][j]) {
					BFS(i, j);
					cnt2++;
				}
			}
		}
		System.out.println(cnt+" "+cnt2);
	}

	private static void BFS(int a, int b) {
		Queue<Node> q = new LinkedList<>();
		q.add(new Node(a, b));
		visited[a][b] = true;

		while (!q.isEmpty()) {
			Node temp = q.poll();
			int y = temp.y;
			int x = temp.x;
			for (int i = 0; i < dir.length; i++) {
				int dy = y + dir[i][0];
				int dx = x + dir[i][1];
				if (isOK(dy, dx, y, x)) {
					visited[dy][dx]=true;
					q.add(new Node(dy,dx));
				}
			}
		}
	}

	private static boolean isOK(int dy, int dx, int y, int x) {
		if (dy >= 0 && dy < N && dx >= 0 && dx < N) {
			if (!visited[dy][dx] && map[y][x] == map[dy][dx]) {
				return true;
			}
		}
		return false;
	}

	static class Node {
		int y;
		int x;

		public Node(int y, int x) {
			super();
			this.y = y;
			this.x = x;
		}
	}
}
