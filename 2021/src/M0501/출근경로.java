package M0501;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 출근경로 {
	static int W, H;
	static int dp[][][][];
	static int MOD = 100000;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		W = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());

		dp = new int[101][101][2][2];

		for (int i = 2; i <= H; i++) {
			dp[i][1][0][0] = 1; // 가로 시작
		}

		for (int i = 2; i <= W; i++) {
			dp[1][i][0][1] = 1; // 세로 시작
		}

		for (int i = 2; i <= H; i++) {
			for (int j = 2; j <= W; j++) {
				dp[i][j][0][0] = (dp[i - 1][j][0][0] + dp[i - 1][j][1][0]) % MOD;	//방향 전환 가능
				dp[i][j][0][1] = (dp[i][j - 1][0][1] + dp[i][j - 1][1][1]) % MOD;	//방향 전환 가능
				dp[i][j][1][0] = (dp[i - 1][j][0][1]) % MOD;
				dp[i][j][1][1] = (dp[i][j - 1][0][0]) % MOD;
			}
		}
		
		System.out.println((dp[H][W][0][0]+dp[H][W][0][1]+dp[H][W][1][0]+dp[H][W][1][1])%MOD);
	}

}
