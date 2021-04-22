package M0421;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class LCS2 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String str1 = br.readLine();
		String str2 = br.readLine();
		int N = str1.length();
		int M = str2.length();
		int dp[][] = new int[N + 1][M + 1];

		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= M; j++) {
				if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
					dp[i][j] = dp[i - 1][j - 1] + 1;
				} else {
					dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
				}
			}
		}

		System.out.println(dp[N][M]);

		int o1 = N, o2 = M;
		String str = "";
		Stack<Character> stack = new Stack<>();
		while (o1 >= 1 && o2 >= 1) {
			if (dp[o1][o2] == dp[o1 - 1][o2]) {
				o1--;
			} else if (dp[o1][o2] == dp[o1][o2 - 1]) {
				o2--;
			} else {
				stack.push(str1.charAt(o1 - 1));
				o1--;
				o2--;
			}
		}
		while(!stack.isEmpty()) {
			System.out.print(stack.pop());
		}

	}
}
