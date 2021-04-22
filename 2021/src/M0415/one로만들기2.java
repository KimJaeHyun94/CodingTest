package M0415;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class one로만들기2 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int dp[] = new int[N + 1];
		int before[] = new int[N + 1];

		dp[1] = 0;
		for (int i = 2; i <= N; i++) {
			dp[i] = dp[i - 1] + 1;
			before[i] = i - 1;
			
			if (i % 2 == 0) {
				if (dp[i] > dp[i / 2] + 1) {
					dp[i] = dp[i / 2] + 1;
					before[i] = i / 2;
				}
			}
			if (i % 3 == 0) {
				if (dp[i] > dp[i / 3] + 1) {
					dp[i] = dp[i / 3] + 1;
					before[i] = i / 3;
				}
			}
		}
		System.out.println(dp[N]);

		while(N>0) {
			System.out.print(N+" ");
			N = before[N];
		}
	}
}
