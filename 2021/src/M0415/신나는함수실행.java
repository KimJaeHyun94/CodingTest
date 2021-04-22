package M0415;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 신나는함수실행 {
	public static int[][][] dp = new int[21][21][21];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		while (true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());

			int ans = 0;
			if (a == -1 && b == -1 && c == -1) {
				break;
			}
			ans = func(a, b, c);

			sb.append("w(").append(a + ", " + b + ", " + c + ") = ").append(ans).append("\n");

		}
		System.out.println(sb);
	}

	private static int func(int a, int b, int c) {
		
		if (isOK(a, b, c) && dp[a][b][c] != 0) {
			return dp[a][b][c];
		}
		
		if (a <= 0 || b <= 0 || c <= 0)
			return 1;
		if (a > 20 || b > 20 || c > 20) {
			return dp[20][20][20] = func(20, 20, 20);
		}
		if (a < b && b < c) {
			return dp[a][b][c] = func(a, b, c - 1) + func(a, b - 1, c - 1) - func(a, b - 1, c);
		} else {
			return dp[a][b][c] = func(a - 1, b, c) + func(a - 1, b - 1, c) + func(a - 1, b, c - 1)
					- func(a - 1, b - 1, c - 1);
		}
	}

	private static boolean isOK(int a, int b, int c) {
		return a >= 0 && a <= 20 && b >= 0 && b <= 20 && c >= 0 && c <= 20;
	}
}
