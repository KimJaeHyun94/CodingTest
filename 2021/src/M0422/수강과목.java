package M0422;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 수강과목 {
	static int N, K;
	static int jungyo[];
	static int time[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		jungyo = new int[K + 1];
		time = new int[K + 1];
		int dp[] = new int[N + 1];
		for (int i = 1; i <= K; i++) {
			st = new StringTokenizer(br.readLine());
			int j = Integer.parseInt(st.nextToken());
			int t = Integer.parseInt(st.nextToken());
			
			for (int k = N; k >=t; k--) {
				dp[k] = Math.max(dp[k], dp[k-t]+j);
			}
		}
		System.out.println(dp[N]);
	}
}
