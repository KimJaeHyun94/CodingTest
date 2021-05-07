package M0505;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 내선물을받아줘 {
	static int N, M;
	static int map[][];
	static int dirs[][] = { { 0, -1 }, { 0, 1 }, { 1, 0 }, { -1, 0 } };
	static int dp[][];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		dp = new int[N][M];
		for (int i = 0; i < N; i++) {
			String line = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = findDir(line.charAt(j));
			}
		}
		
		int cnt = 1;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (cnt == dfs(i, j, cnt)) {
					cnt++;
				}
			}
		}
		System.out.println(cnt-1);
		
	}
	private static int dfs(int i, int j, int cnt) {
		if (dp[i][j] != 0)
			return dp[i][j];
		dp[i][j] = cnt;

		int di = i + dirs[map[i][j]][0];
		int dj = j + dirs[map[i][j]][1];
		dp[i][j] = dfs(di, dj, cnt);
		return dp[i][j];
	}
	private static int findDir(char c) {
		switch (c) {
		case 'W':
			return 0;
		case 'E':
			return 1;
		case 'S':
			return 2;
		case 'N':
			return 3;
		}
		return -1;
	}
}
