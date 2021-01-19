package M0329;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ12865평범한배낭 {
	static int bag[][];
	static boolean status[];
	static int max;
	static int check[];
	static int N, K;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		bag = new int[N][2];
		status = new boolean[bag.length];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			bag[i][0] = Integer.parseInt(st.nextToken());
			bag[i][1] = Integer.parseInt(st.nextToken());
		}
		powerset(N, 0, 0);
		System.out.println(max);
	}

	private static void powerset(int n, int r, int sum) {
		int summit = 0;
		if (r == n) {
			if (sum == K) {
				for (int i = 0; i < status.length; i++) {
					if (status[i]) {
						summit += bag[i][1];
					}
				}
				max = Math.max(max, summit);
				return;
			} else if (sum > K) {
				return;
			}
		} else {
			status[r] = true;
			powerset(n, r + 1, sum + bag[r][0]);
			status[r] = false;
			powerset(n, r + 1, sum);
		}
	}
}
