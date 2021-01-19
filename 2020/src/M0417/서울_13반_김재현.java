package M0417;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 서울_13반_김재현 {
	static int map[][];
	static int dirs[][] = { { -1, 0 }, { 1, 0 }, { 0, 1 }, { 0, -1 }, { -1, 1 }, { -1, -1 }, { 1, -1 }, { 1, 1 } };
	static int cnt;
	static boolean visited[][];

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			map = new int[10][10];
			cnt = 0;
			visited = new boolean[10][10];
			for (int i = 0; i < 10; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < 10; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			for (int i = 0; i < 10; i++) {
				for (int j = 0; j < 10; j++) {
					if (!visited[i][j] && map[i][j]==1) {
						BFS(i, j);
						cnt++;
					}
				}
			}
			System.out.println("#"+tc+" "+cnt);
		}
	}

	private static void BFS(int i, int j) {
		visited[i][j] = true;
		Queue<Node> q = new LinkedList<>();
		q.add(new Node(i,j));
		
		while(!q.isEmpty()) {
			Node temp = q.poll();
			int x = temp.x;
			int y = temp.y;
			for (int d = 0; d < dirs.length; d++) {
				int dx = x+dirs[d][0];
				int dy = y+dirs[d][1];
				
				if(isOK(dx,dy)) {
					if(!visited[dx][dy] && map[dx][dy]==1) {
						q.add(new Node(dx,dy));
						visited[dx][dy] = true;
					}
				}
			}
		}
	}

	private static boolean isOK(int dx, int dy) {
		
		return dx>=0&&dx<10&&dy>=0&&dy<10;
	}

	static class Node {
		int x;
		int y;

		public Node(int x, int y) {

			this.x = x;
			this.y = y;
		}
	}

}
