package M0305;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ14716현수막 {
	static int N, M;
	static boolean visited[][];
	static int map[][];
	static int cnt;
	static int dir[][] = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 }, { -1, -1 }, { -1, 1 }, { 1, -1 }, { 1, 1 } };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		map = new int[M][N];
		visited=new boolean[M][N];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		for (int i = 0; i < M; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] == 1 && !visited[i][j]) {
					DFS(i, j);
					cnt++;
				}
			}
		}
		System.out.println(cnt);
	}

	private static void DFS(int y, int x) {
		visited[y][x] = true;

		for (int i = 0; i < dir.length; i++) {
			int dy=y+dir[i][0];
			int dx=x+dir[i][1];
			
			if(isOK(dy,dx)) {
				DFS(dy,dx);
			}
		}
	}

	private static boolean isOK(int dy, int dx) {
		if(dy>=0&&dy<M&&dx>=0&&dx<N) {
			if(!visited[dy][dx] && map[dy][dx]==1)
				return true;
		}
		return false;
	}

}
