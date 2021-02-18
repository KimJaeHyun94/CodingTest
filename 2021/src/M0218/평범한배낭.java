package M0218;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 평범한배낭 {
	static int N, K;
	static int W[], V[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		W = new int[N + 1];
		V = new int[N + 1];

		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			W[i] = Integer.parseInt(st.nextToken());
			V[i] = Integer.parseInt(st.nextToken());
		}

		int sum = Integer.MIN_VALUE;
		int dp[][] = new int[N + 1][K + 1];
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= K; j++) {
				if (j >= W[i]) {  //무게를 버틸 수 있을때 
					dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - W[i]] + V[i]);     //p[그 전 아이템][지금까지 저장해 둔 무게 - 지금 아이템의 무게]+현재 아이템의 가치와 이전 구한 무게의 가치와 비교해서 넣는다. 
				} else {
					dp[i][j] = dp[i - 1][j];
				}
			}
		}
		System.out.println(dp[N][K]);
	}
}
