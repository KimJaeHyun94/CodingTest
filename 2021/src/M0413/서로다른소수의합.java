package M0413;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class 서로다른소수의합 {
	static int MAX = 1120;
	static boolean prime[];
	static ArrayList<Integer> list = new ArrayList<>();
	static int dp[][];

	public static void main(String[] args) throws NumberFormatException, IOException {
		prime = new boolean[MAX + 1];
		prime[0] = true;
		prime[1] = true;

		for (int i = 2; i * i <= MAX; i++) {
			if (!prime[i]) {
				for (int j = i * i; j <= MAX; j += i) {
					prime[j] = true;
				}
			}
		}
		for (int i = 2; i <= MAX; i++) {
			if (!prime[i])
				list.add(i);
		}

		dp = new int[MAX + 1][15];
		dp[0][0] = 1;

		for (int i = 0; i < list.size(); i++) {
			for (int j = MAX; j >= list.get(i); j--) {
				for (int k = 1; k < 15; k++) {
					dp[j][k] += dp[j - list.get(i)][k - 1];
				}
			}
		}

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int tc = 0; tc < T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			sb.append(dp[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())]).append("\n");
		}
		System.out.println(sb);
	}
}
