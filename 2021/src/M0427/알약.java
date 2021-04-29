package M0427;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 알약 {
	static long dp[][];

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		dp = new long[31][31];
		while (true) {
			int N = Integer.parseInt(br.readLine());
			if (N == 0)
				break;

			sb.append(dfs(N, 0)).append("\n");
		}

		System.out.println(sb);

	}

	private static long dfs(int w, int h) {

		if (dp[w][h] != 0)
			return dp[w][h];

		if (w == 0)
			return 1;

		dp[w][h] = dfs(w - 1, h + 1);	//1조각을 꺼내고 빈조각은 추가
		if (h > 0)
			dp[w][h] += dfs(w, h - 1);

		return dp[w][h];
	}

}
