package M0307;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

public class 전깃줄 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		int arr[][] = new int[N][2]; // a,b
		int dp[] = new int[N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			arr[i][0] = a;
			arr[i][1] = b;
		}

		Arrays.sort(arr, new Comparator<int[]>() {

			@Override
			public int compare(int[] a, int[] b) {
				return a[0] - b[0];
			}
		});

		for (int i = 0; i < N; i++) {
			dp[i] = 1;
			for (int j = 0; j < i; j++) {
				if (arr[j][1] < arr[i][1] && dp[i] <= dp[j]) {
					dp[i] = dp[j] + 1;

				}
			}
		}
		int max = 0;
		for (int j = 0; j < dp.length; j++) {
			max = Math.max(max, dp[j]);
		}
		System.out.println(N - max);
	}

}
