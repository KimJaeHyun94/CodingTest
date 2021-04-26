package M0423;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 외판원순회3 {
	static int N;
	static double dp[][];
	static Pair arr[];
	static int INF = 987654321;
	static double cost[][];

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		dp = new double[N][1 << N];
		arr = new Pair[N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int f = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			arr[i] = new Pair(f, s);
		}
		for (int i = 0; i < N; i++) {
			Arrays.fill(dp[i], INF);
		}

		System.out.println(DFS(0, 1 << 0));
	}

	private static double DFS(int n, int visit) {
		if (visit == (1 << N) - 1) {
			if (getDistance(n, 0) == 0.0) {
				return INF;
			}
			return getDistance(n, 0);
		}

		if (dp[n][visit] != INF)
			return dp[n][visit];

		for (int i = 0; i < N; i++) {
			if (getDistance(n, i) == 0 || (visit & (1 << i)) != 0)
				continue;
			dp[n][visit] = Math.min(dp[n][visit], DFS(i, visit | (1 << i)) + getDistance(n, i));
		}
		return dp[n][visit];
	}

	private static double getDistance(int i, int j) {
		return Math.sqrt(Math.pow(arr[i].r - arr[j].r, 2) + Math.pow(arr[i].c - arr[j].c, 2));
	}

	static class Pair {
		int r;
		int c;

		public Pair(int r, int c) {
			this.r = r;
			this.c = c;
		}

	}
}
