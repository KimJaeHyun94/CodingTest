package 누적합;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 기상청인턴신현수 {

	static int N, K;
	static int dp[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		dp = new int[N+1];
		int max = Integer.MIN_VALUE;
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			dp[i] = Integer.parseInt(st.nextToken());
		}
		int sum = 0;
		for (int i = 1; i <= K; i++) {
			sum += dp[i];
		}
		
		max = sum;
		for (int i = K + 1; i <= N; i++) {
			sum += dp[i];
			sum -= dp[i - K];
			max = Math.max(max, sum);

		}
		System.out.println(max);
	}
}
