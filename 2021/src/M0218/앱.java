package M0218;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 앱 {
	static int N, M;
	static int app[];
	static int memory[];
	static int dp[][];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		app = new int[N+1];
		memory = new int[N+1];
		dp = new int[N + 1][10001];

		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			app[i] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			memory[i] = Integer.parseInt(st.nextToken());
		}
		
		int ans = Integer.MAX_VALUE;
		for (int i = 1; i <= N; i++) {
			for (int j = 0; j <= 10000; j++) {
				dp[i][j] = dp[i - 1][j];
				
				if(j-memory[i]>=0) {
					dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-memory[i]]+app[i]);
				}

				if (dp[i][j] >= M)
					ans = Math.min(ans, j);
			}
		}

		System.out.println(ans);
	}

}
