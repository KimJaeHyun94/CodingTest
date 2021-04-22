package M0415;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 오르막수 {
	static int dp[][];
	static int mod = 10007;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
	
		dp = new int[N+1][10];
		for (int i = 0; i <= 9; i++) {
			dp[1][i] = 1;	//1로 초기화해둔다.
		}
		
		for (int i = 2; i <= N; i++) {
			for (int j = 0; j < 10; j++) {
				for (int k = 0; k <= j; k++) {		//j보다 작아야되므로
					dp[i][j] +=dp[i-1][k] % mod;
				}
			}
		}
		int ans = 0;
		for (int i = 0; i < 10; i++) {
			ans+=dp[N][i];
		}
		System.out.println(ans%mod);
	}
}
