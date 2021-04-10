package M0408;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 연속합2 {
	static int N;
	static int arr[];
	static int dp[][];

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new int[N];
		dp = new int[N][2];

		StringTokenizer st = new StringTokenizer(br.readLine());

		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		int max = arr[0];
		dp[0][0] = arr[0];
		dp[0][1] = arr[0];

		for (int i = 1; i < N; i++) {
			dp[i][0] = Math.max(dp[i - 1][0] + arr[i], arr[i]);
			dp[i][1] = Math.max(dp[i - 1][0], dp[i - 1][1] + arr[i]);
			max = Math.max(max, Math.max(dp[i][0], dp[i][1]));
		}
		System.out.println(max);
	}
}
