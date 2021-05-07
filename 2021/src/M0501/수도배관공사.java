package M0501;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 수도배관공사 {
	static int D, P;
	static int dp[];
	static int INF = 987654321;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		D = Integer.parseInt(st.nextToken());
		P = Integer.parseInt(st.nextToken());

		dp = new int[D + 1];
		dp[0] = INF;
		for (int i = 0; i < P; i++) {
			st = new StringTokenizer(br.readLine());
			int l = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			for (int j = D; j >= l; j--) {
				dp[j] = Math.max(dp[j], Math.min(c, dp[j-l]));
			}
		}
		System.out.println(dp[D]);

	}

}
