package M0307;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;
import java.util.StringTokenizer;

public class 가장긴증가하는부분수열 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		int arr[] = new int[N];
		int dp[] = new int[N];

		
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
		int max = 0;
		ArrayList<Integer> list = new ArrayList<>();

		for (int j = 0; j < dp.length; j++) {
			if (max < dp[j]) {
				max = dp[j];
			}
		}

		System.out.println(max);
	}

}
