package M0501;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 성냥개비 {
	static int num[] = { 1, 7, 4, 2, 0, 8, 10 }; // 그 개수로 만들 수 있는 가장 작은 숫자
	static long dp[];

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		StringBuilder sb = new StringBuilder();

		dp = new long[101];
		for (int i = 2; i <= 8; i++) {
			dp[i] = num[i - 2];
		}
		dp[6] = 6; // 0으로 시작할 수 없으므로

		for (int i = 9; i <= 100; i++) {
			dp[i] = Long.MAX_VALUE;
			for (int j = 2; j <= 7; j++) {
				dp[i] = Math.min(dp[i - j] * 10 + num[j - 2], dp[i]);
			}
		}

		for (int tc = 0; tc < T; tc++) {
			int N = Integer.parseInt(br.readLine());

			String max = "";
			if (N % 2 == 0) { // 짝수면
				int t = N / 2;
				while (t-- > 0) {
					max += "1";
				}
			} else {
				max += 7;
				int t = N / 2 - 1;
				while (t-- > 0) {
					max += "1";
				}
			}
			sb.append(dp[N]).append(" ").append(max).append("\n");
		}
		System.out.println(sb);
	}
}
