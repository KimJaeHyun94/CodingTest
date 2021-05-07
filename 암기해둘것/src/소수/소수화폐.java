package 소수;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class 소수화폐 {
	static int N;
	static int MAX = 40000;
	static int MOD = 123456789;
	static ArrayList<Integer> prime;
	static int dp[];

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		prime = new ArrayList<>();
		boolean[] cache = new boolean[MAX];
		cache[0] = cache[1] = false;

		for (int i = 2; i < MAX; i++) {
			cache[i] = true;
		}
		for (int i = 2; i * i < MAX; i++) {
			if (cache[i]) {
				for (int j = i * i; j < MAX; j += i) {
					if (cache[j]) {
						cache[j] = false;
					}
				}
			}
		}
		for (int i = 2; i < MAX; i++) {
			if (cache[i])
				prime.add(i);
		}

		dp = new int[N + 1];
		dp[0] = 1;

		for (int i = 0; i < prime.size(); i++) {
			for (int j = prime.get(i); j <= N; j++) {
				dp[j] = (dp[j] + dp[j - prime.get(i)]) % MOD;
			}
		}
		
		System.out.println(dp[N]%MOD);
	}

}
