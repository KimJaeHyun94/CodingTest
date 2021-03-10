package M0309;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 이항계수3페르마 {
	static int N, K;
	static int MOD = 1000000007;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		long dp[] = new long[N+1];
		dp[0] = 1; dp[1] = 1;
		
		for (int i = 2; i <= N; i++) {
			dp[i] = (dp[i-1]*i)%MOD;
		}
		long a= dp[N];
		long b = (dp[K]*dp[N-K]) %MOD;
		
		long c = pow(b, MOD - 2);
		
		System.out.println((a*c)%MOD);
		
	}
	private static long pow(long b, int m) {
		long result = 1;
		while (m > 0) {
			if (m % 2 != 0) {
				result *= b;
				result %= MOD;
			}
			b *= b;
			b %= MOD;
			m /= 2;
		}
		return result;
	}
	
}
