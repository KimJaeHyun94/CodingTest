package M0416;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ2234성곽 {
	static int N, M;
	static int map[][];
	static int dirs[][] = { { 0, -1 }, { -1, 0 }, { 0, 1 }, { 1, 0 } }; // 서, 북, 동, 남
	static boolean visited[][];
	static int cnt; // 방의 갯수
	static int max = Integer.MIN_VALUE;
	static int extent;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[M][N];
		visited = new boolean[M][N];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		for (int i = 0; i < M; i++) {
			for (int j = 0; j < N; j++) {
				if (!visited[i][j]) {
					extent = Math.max(extent, BFS(i, j));
					cnt++;
				}
			}
		}
		System.out.println(cnt);    	//갯수 
		System.out.println(extent);     //최대 넓이
		
		for (int i = 0; i < M; i++) {
			for (int j = 0; j < N; j++) {
				for (int k = 1; k <=8; k<<=1) {
					if((map[i][j] & k) != 0) {
						visited = new boolean[M][N];
						map[i][j]-=k;
						extent = Math.max(extent, BFS(i,j));
						map[i][j]+=k;
					}
				}
			}
		}
		System.out.println(extent); 
	}

	private static int BFS(int i, int j) {
		Queue<Node> q = new LinkedList<>();
		q.add(new Node(i, j));
		int area = 1;
		visited[i][j] = true;

		while (!q.isEmpty()) {
			Node temp = q.poll();
			int x = temp.x;
			int y = temp.y;

			int flag = 1;
			for (int k = 0; k < dirs.length; k++) {
				int dx = x + dirs[k][0];
				int dy = y + dirs[k][1];
				if (isOK(dx, dy)) {
					if ((map[x][y] & flag) == 0) { // 그 방향에 벽이 없다면 
						if (!visited[dx][dy]) {
							area++;
							visited[dx][dy] = true;
							q.add(new Node(dx, dy));
						}
					}
				}
				flag <<= 1;
			}
		}
		return area;
	}

	private static boolean isOK(int dx, int dy) {
		return dx >= 0 && dx < M && dy >= 0 && dy < N;
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
