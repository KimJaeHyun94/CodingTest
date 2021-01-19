package M0229;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ7562나이트의이동 {
	static int dir[][] = { { -2, -1 }, { -1, -2 }, { 1, -2 }, { 2, -1 }, { 2, 1 }, { 1, 2 }, { -1, 2 }, { -2, 1 } };
	static int map[][];
	static boolean visited[][];
	static int N;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int T = Integer.parseInt(st.nextToken());
		for (int i = 0; i < T; i++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			map = new int[N][N];
			visited = new boolean[N][N];
			st = new StringTokenizer(br.readLine());
			int y = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
			BFS(y, x);
			st = new StringTokenizer(br.readLine());
			int endy=Integer.parseInt(st.nextToken());
			int endx=Integer.parseInt(st.nextToken());
			System.out.println(map[endy][endx]);
		}
	}

	private static void BFS(int y, int x) {
		Queue<Node> queue = new LinkedList<>();
		queue.add(new Node(y, x));
		visited[y][x]=true;
		while (!queue.isEmpty()) {
			Node temp = queue.poll();
			for (int i = 0; i < dir.length; i++) {
				int dy = temp.y + dir[i][0];
				int dx = temp.x + dir[i][1];

				if (isOK(dy, dx)) {
					visited[dy][dx]=true;
					map[dy][dx]=map[temp.y][temp.x]+1;
					queue.add(new Node(dy,dx));
				}
			}
		}
	}

	private static boolean isOK(int dy, int dx) {
		if(dy>=0&&dy<N&&dx>=0&&dx<N) {
			if(!visited[dy][dx])
				return true;
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
