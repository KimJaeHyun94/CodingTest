package M0225;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 앱 {
	static int N, M;
	static int W[], V[], dp[][];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		W = new int[N + 1]; // 무게
		V = new int[N + 1]; // 가치

		dp = new int[N + 1][10001];
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			V[i] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			W[i] = Integer.parseInt(st.nextToken());
		}

		int ans = Integer.MAX_VALUE;
		for (int i = 1; i <= N; i++) {
			for (int j = 0; j <= 10000; j++) {
				if (j >= W[i]) { // 무게를 버틸 수 있을때
					dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - W[i]] + V[i]); // 그 전과 지금의 가치를 비교해서 더 큰걸 넣어준다.
				} else {
					dp[i][j] = dp[i - 1][j];
				}
				if (dp[i][j] >= M)
					ans = Math.min(j, ans);
			}
		}
		System.out.println(ans);
	}

}
