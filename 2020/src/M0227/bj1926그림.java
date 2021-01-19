package M0227;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class bj1926그림 {
	private static int N, M;
	private static int map[][];
	static boolean[][] visited;
	private static int cnt;
	static int count;
	private static int dir[][] = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map=new int[N][M];
		visited=new boolean[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j]=Integer.parseInt(st.nextToken());
			}
		}
		int max=0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(map[i][j]==1 && !visited[i][j]) {
					count=0; DFS(i,j); cnt++;
					max=Math.max(max, count);
				}
			}
		}
		System.out.println(cnt);
		System.out.println(max);
	}

	private static void DFS(int y, int x) {
		visited[y][x]=true;
		count++;
		for (int i = 0; i < dir.length; i++) {
			int dy = y + dir[i][0];
			int dx = x + dir[i][1];

			if (isOK(dy, dx)) {
				DFS(dy, dx);
			}
		}

	}

	private static boolean isOK(int dy, int dx) {
		if (dy >= 0 && dy < N && dx >= 0 && dx < M) {
			if (!visited[dy][dx] && map[dy][dx] == 1)
				return true;
		}
		return false;
	}
}