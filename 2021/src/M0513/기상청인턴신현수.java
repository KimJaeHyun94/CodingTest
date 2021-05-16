package M0513;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 기상청인턴신현수 {

	static int N, K;
	static int dp[], arr[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		dp = new int[N + 1];
		arr = new int[N + 1];
		int max = Integer.MIN_VALUE;
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			if (i == 1)
				dp[i] = arr[i];
			else
				dp[i] = dp[i - 1] + arr[i];
		}
		
		max = Math.max(dp[K], max);
		for (int i = 1; i <= N-K; i++) {
			max = Math.max(dp[i+K]-dp[i], max);
		}
		System.out.println(max);
	}
}
