package M0511;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 침투 {
	static int N, M;
	static int map[][];
	static int dirs[][] = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 }};
	static boolean visited[][];
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		map = new int[M][N];
		visited = new boolean[M][N];
		for (int i = 0; i < M; i++) {
			String line = br.readLine();
			for (int j = 0; j < N; j++) {
				map[i][j] = line.charAt(j)-'0';
			}
		}
		
		for (int i = 0; i < N; i++) {
			if(!visited[0][i] && map[0][i]==0) {
				dfs(0,i);
			}
		}
		
		
		System.out.println("NO");
		
	}


	private static void dfs(int r, int c) {
		visited[r][c] = true;
		if(r==M-1) {
			System.out.println("YES");
			System.exit(0);
		}
		
		for (int d = 0; d < dirs.length; d++) {
			int dr = r + dirs[d][0];
			int dc = c + dirs[d][1];

			if (isOK(dr, dc) && map[dr][dc] == 0 && !visited[dr][dc]) {
				dfs(dr, dc);
			}
		}
		
	}
	private static boolean isOK(int dr, int dc) {
		return dr >= 0 && dr < M && dc >= 0 && dc < N;
	}
}
