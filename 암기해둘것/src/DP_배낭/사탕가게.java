package DP_배낭;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 사탕가게 {
	static int calory[];
	static int price[];

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringBuilder sb = new StringBuilder();
		while (true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int C = (int) Math.round((Double.parseDouble(st.nextToken()) * 100));

			if (N == 0 && C == 0) {
				break;
			}
			calory = new int[N + 1];
			price = new int[N + 1];
			for (int i = 1; i <= N; i++) {
				st = new StringTokenizer(br.readLine());
				calory[i] = Integer.parseInt(st.nextToken());
				price[i] = (int) Math.round((Double.parseDouble(st.nextToken()) * 100));
			}

			int dp[] = new int[C + 1];

			for (int i = 0; i <= C; i++) {
				for (int j = 1; j <= N; j++) {

					if (i >= price[j]) {
						dp[i] = Math.max(dp[i], dp[i-price[j]]+calory[j]);
					} 
				}
			}
			System.out.println(dp[C]);
		}
	}
}
