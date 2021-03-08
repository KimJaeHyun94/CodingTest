package M0307;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.util.StringTokenizer;

public class 가장긴바이토닉부분수열 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		int arr[] = new int[N];
		int dp[] = new int[N];
		int rp[] = new int[N];

		StringTokenizer st = new StringTokenizer(br.readLine());

		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		for (int i = 0; i < N; i++) {
			dp[i] = 1;
			for (int j = 0; j < i; j++) {
				if (arr[j] < arr[i] && dp[i] <= dp[j]) {
					dp[i] = dp[j] + 1;

				}
			}
		}

		for (int i = N - 1; i >= 0; i--) {
			rp[i] = 1;
			for (int j = N - 1; j > i; j--) {
				if (arr[j] < arr[i] && rp[i] <= rp[j]) {
					rp[i] = rp[j] + 1;
				}
			}
		}
		int max = 0;
		for (int j = 0; j < dp.length; j++) {
			max = Math.max(max, dp[j] + rp[j]);
		}
		System.out.println(max-1);
	}

}
