package M0411;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class 동물원 {
	static int N;
	static int dp[][];
	static int mod = 9901;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		dp = new int[N + 1][3];
		
		dp[1][0] = 1;
		dp[1][1] = 1;
		dp[1][2] = 1;
		
		for (int i = 2; i <= N; i++) {
			dp[i][0] = (dp[i-1][0]+dp[i-1][1]+dp[i-1][2])%mod;
			dp[i][1] = (dp[i-1][0]+dp[i-1][2])%mod;
			dp[i][2] = (dp[i-1][0]+dp[i-1][1])%mod;
 		} 
		System.out.println((dp[N][0]+dp[N][1]+dp[N][2])%mod);
	
	}

	
}
