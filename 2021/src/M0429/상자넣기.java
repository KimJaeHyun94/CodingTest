package M0429;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 상자넣기 {
	static int N;
	static int box[], dp[];

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		box = new int[N];
		dp = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			box[i] = Integer.parseInt(st.nextToken());
		}
		dp[0] = 1;
		int max = Integer.MIN_VALUE;
		for (int i = 1; i < N; i++) {
			for (int j = 0; j < i; j++) {
				if (box[i] > box[j]) {
					dp[i] = Math.max(dp[i], dp[j]);
				}
			}
			dp[i]++;
			max = Math.max(max, dp[i]);
		}

		System.out.println(max);

	}

}
