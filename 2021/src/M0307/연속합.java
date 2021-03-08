package M0307;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 연속합 {
	static int N;
	static int map[];
	static int dp[];
	static int max = Integer.MIN_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		max = Integer.MIN_VALUE;
		N = Integer.parseInt(st.nextToken());
		map = new int[N];
		dp = new int[N];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			map[i] = Integer.parseInt(st.nextToken());
		}
		dp[0] = map[0];
		max = dp[0];
		for (int i = 1; i < N; i++) {
			dp[i] = Math.max(dp[i - 1] + map[i], map[i]);
			max = Math.max(dp[i], max);
		}
		System.out.println(max);
	}
}
