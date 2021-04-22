package M0422;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 안녕 {
	static int N;
	static int health[];
	static int happy[];
	static int dp[][];

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		health = new int[N + 1];
		happy = new int[N + 1];
		dp = new int[N + 1][101];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			health[i] = Integer.parseInt(st.nextToken());
		}

		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			happy[i] = Integer.parseInt(st.nextToken());
		}

		for (int i = 1; i <= N; i++) {
			for (int j = 0; j <= 100; j++) {
				dp[i][j] = dp[i - 1][j];

				if (j > health[i]) {
					dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - health[i]] + happy[i]);
				}
			}
		}
		System.out.println(dp[N][100]);

	}
}
