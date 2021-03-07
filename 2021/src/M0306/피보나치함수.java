package M0306;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 피보나치함수 {
	static int DP[][];

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		for (int tc = 0; tc < T; tc++) {
			int N = Integer.parseInt(br.readLine());

			DP = new int[41][2];

			DP[0][0] = 1;
			DP[0][1] = 0;
			DP[1][0] = 0;
			DP[1][1] = 1;

			fibo(N);
			System.out.println(DP[N][0] + " " + DP[N][1]);

		}
	}

	private static void fibo(int n) {
		if (n == 0 || n == 1)
			return;

		for (int i = 2; i <= n; i++) {
			DP[i][0] = DP[i - 1][0] + DP[i - 2][0];
			DP[i][1] = DP[i - 1][1] + DP[i - 2][1];
		}

	}
}
