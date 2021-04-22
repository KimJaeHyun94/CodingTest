package M0415;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
/**
 * 
 * @author 김재현
 * @see https://kibbomi.tistory.com/124
 */
public class 색상환_2 {
	static int dp[][];
	static int mod =1000000003;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int K = Integer.parseInt(br.readLine());	
		
		dp = new int[N+1][N+1];
		
		for (int i = 0; i <= N; i++) {
			Arrays.fill(dp[i], -1);
		}
		
		System.out.println(Sol(N, K));
		
	}

	private static int Sol(int n, int k) {
		if(n<=0 || n<2*k) return 0;	//2k보다 n이 작으면 색을 칠할수가 없다 
		if(k==1) return n;
		if(dp[n][k]!=-1) return dp[n][k];
		return dp[n][k] = (Sol(n-2, k-1)+Sol(n-1,k))%mod;
	}
}
