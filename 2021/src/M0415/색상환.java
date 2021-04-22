package M0415;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 색상환 {
	static long dp[][];
	static long mod =1000000003;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int K = Integer.parseInt(br.readLine());	
		
		dp = new long[N+1][N+1];
		
		for (int i = 0; i <= N; i++) {
			dp[i][1] = i;
			dp[i][0] = 1;
		}
		
		for (int i = 2; i <= N; i++) {
			for (int j = 2; j <= K; j++) {
				dp[i][j] = (dp[i-2][j-1]+dp[i-1][j]) % mod;
			}
		}
		
		System.out.println((dp[N-1][K] + dp[N-3][K-1]) % mod);
		
	}
}
