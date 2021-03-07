package M0306;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class _01타일 {
	static int dp[];
	static int mod = 15746;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		if(N==1) {
			System.out.println(1);
		}else if(N==2) {
			System.out.println(2);
		}else {
			dp = new int[N+1];
			dp[1] = 1;
			dp[2] = 2;
			for (int i = 3; i <= N; i++) {
				dp[i] = (dp[i-1]+dp[i-2])%mod;
			}
			System.out.println(dp[N]%mod);
		}
	}
}
