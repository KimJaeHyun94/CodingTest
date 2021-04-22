package M0421;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 파일합치기 {
	static int K;
	static int file[];
	static int sum[];
	static int dp[][];

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int t = 0; t < TC; t++) {
			K = Integer.parseInt(br.readLine());

			file = new int[K + 1];
			sum = new int[K + 1];
			dp = new int[K + 1][K + 1];

			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= K; i++) {
				file[i] = Integer.parseInt(st.nextToken());
				sum[i] = sum[i - 1] + file[i];
			}

			for (int i = 1; i <= K; i++) {
				for (int j = 1; j <= K - i; j++) {
					int k = j + i;
					dp[j][k] = Integer.MAX_VALUE;
					for (int d = j; d < k; d++) {
						dp[j][k] = Math.min(dp[j][k], dp[j][d] + dp[d + 1][k] + sum[k] - sum[j - 1]);
					}
				}
			}
			sb.append(dp[1][K]).append("\n");
		}
		System.out.println(sb);
	}
}
