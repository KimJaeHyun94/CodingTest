package M0429;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 돌게임 {
	static int dp[];

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		dp = new int[1010];
		dp[0] = 0;
		dp[1] = 1;
		dp[2] = 0;
		dp[3] = 1;

		for (int i = 4; i < N; i++) {
			int min = Math.min(dp[i - 1], Math.min(dp[i - 3], dp[i - 4]));
			if (min == 1)
				dp[i] = 0;
			else
				dp[i] = 1;
		}
		if (dp[N - 1] == 1) {
			System.out.println("SK");
		} else {
			System.out.println("CY");
	
		
		}
	}
}
