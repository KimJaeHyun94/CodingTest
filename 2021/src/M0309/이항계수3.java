package M0309;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 이항계수3 {
	static int N, K;
	static int MOD = 1000000007;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		long a = factorial(N);
		long b = (factorial(N - K) * factorial(K)) % MOD;
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

	private static long factorial(int n) {
		long result = 1;
		for (int i = 2; i <= n; i++) {
			result *= i;
			result %= MOD;
		}
		return result;
	}
}
