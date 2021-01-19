package M0501;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWEA1949_등산로조정_BFS {
	static int map[][];
	static int N, K;
	static int max, ans;
	static int[][] dirs = { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 } };

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		StringBuilder sb = new StringBuilder();

		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			map = new int[N][N];
			ans = 0; max = 0;
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					max = Math.max(max, map[i][j]);
				}
			}
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					for (int k = 1; k <= K; k++) {
						map[i][j] -= k; // 모든 원소들에 대해서 K를 빼본다
						for (int a = 0; a < N; a++) {
							for (int b = 0; b < N; b++) {
								if (map[a][b] == max) {	//안깎은 가장 긴 봉우리부터 역으로 찾는다
									bfs(a, b, 1);
								}
							}
						}
						map[i][j] += k; // 진행한 후에 원래대로 되돌린다.
					}
				}
			}
			sb.append("#").append(t).append(" ").append(ans).append("\n");
		}
		System.out.println(sb);
	}

	private static void bfs(int i, int j, int cnt) {
		Queue<Node> q =new LinkedList<>();
		q.add(new Node(i,j,cnt));
		
		while(!q.isEmpty()) {
			Node temp = q.poll();
			int x = temp.x;
			int y = temp.y;
			int d = temp.d;
			
			ans = Math.max(ans, d);
			for (int di = 0; di < dirs.length; di++) {
				int dx = x+dirs[di][0];
				int dy = y+dirs[di][1];
				if(isOK(dx,dy)) {
					if(map[x][y]>map[dx][dy]) {
						q.add(new Node(dx,dy,d+1));
					}
				}
			}
		}
	}
	static class Node{
		int x;
		int y;
		int d;
		public Node(int x, int y, int d) {
		
			this.x = x;
			this.y = y;
			this.d = d;
		}
	}
	private static boolean isOK(int di, int dj) {
		return di >= 0 && di < N && dj >= 0 && dj < N;
	}
}
