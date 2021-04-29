package M0427;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 사전topdown {
	static int N, M, K;
	static long dp[][];
	static int skip;
	static String word;
	static boolean flag;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		dp = new long[201][201];
		for (int i = 0; i < 201; i++) {
			Arrays.fill(dp[i], -1);
		}

		if (dfs(N, M) <= K - 1) {
			System.out.println(-1);
		} else {
			word = "";
			skip = K - 1;
			solve(N, M, skip);
			
			if(flag) {
				System.out.println(-1);
			}else
				System.out.println(word);
		}
	}

	private static long dfs(int n, int m) {
		if (n == 0 || m == 0) {
			return 1;
		}
		if (dp[n][m] != -1) {
			return dp[n][m];
		}

		dp[n][m] = Math.min(1000000000, dfs(n - 1, m) + dfs(n, m - 1));
		return dp[n][m];
	}

	private static void solve(int n, int m, long skip) {
		if (n == 0) {
			for (int i = 0; i < m; i++) {
				word += 'z';
			}
			return;
		}
		if (m == 0) {
			for (int i = 0; i < n; i++) {
				word += 'a';
			}
			return;
		}
		
		long idx = dfs(n-1,m);
		if(skip<idx) {
			word+='a';
			solve(n-1,m,skip);
		}
		else if(skip<=1000000000) {
			word+='z';
			solve(n,m-1,skip-idx);
		}else {
			flag = true;
		}
	}
}
