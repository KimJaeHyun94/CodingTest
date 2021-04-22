package M0415;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 합분해 {
	static int dp[][];
	static int mod = 1000000000;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		dp = new int[201][201];

		for (int i = 0; i <= N; i++) {
			dp[i][1] = 1; // 1개 되는건 자기 자신 뿐
		}
		
		for (int i = 2; i <= K; i++) {
			dp[0][i] = 1;
		}
		for (int i = 1; i <= N; i++) {
			for (int j = 2; j <= K; j++) {
				dp[i][j] = (dp[i][j - 1] + dp[i - 1][j]) % mod;
			}
		}
		System.out.println(dp[N][K]);
	}
}
