package M0414;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Moo게임 {
	static int N;
	static int dp[] = new int[30];

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		dp[0] = 3;
		for (int i = 1; i < dp.length; i++) {
			dp[i] = (dp[i - 1] * 2) + (i + 3);
		}
		

		System.out.println(moo(N));

	}

	private static char moo(int n) {
		if (n == 1)
			return 'm';
		if (n == 2 || n == 3)
			return 'o';

		int idx = 0;
		while (dp[idx] < n)
			idx++;
		if (dp[idx] == n)
			return 'o';
		if (n - dp[idx - 1] == 1)
			return 'm'; // 다음 칸
		if (n - dp[idx - 1] <= idx + 3)
			return 'o'; // moo.... 에서 o 해당하는 칸

		return moo((n - dp[idx - 1] - (idx + 3)));

	}

}
