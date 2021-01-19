package M0304;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class CowHopcotch {
	static int R, C, K;
	static int map[][];
	static int cnt;
	static int dir[][] = { { 1, 0 }, { 1, 1 }, { 0, 1 } };
	static int dp[][];
	static int mod =1000000007;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		map = new int[R][C];
		dp = new int[R][C];
		for (int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < C; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				dp[i][j]=-1;
			}
		}
		System.out.println(DFS(0,0));
	}

	private static int DFS(int y, int x) {
		if (y == R - 1 && x == C - 1) {
			return 1;
		}
		if(dp[y][x]!=-1)
			return dp[y][x];
		dp[y][x]=0;
		for (int i = y + 1; i < R; i++) {
			for (int j = x + 1; j < C; j++) {
				if (map[y][x] != map[i][j]) {
					dp[y][x]+=DFS(i, j);
					dp[y][x]%=mod;
				}
			}
		}
		return dp[y][x]%mod;
	}
}
