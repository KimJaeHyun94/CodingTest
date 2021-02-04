package M0204;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 문제1번 {
	static final int INF = 1000000007;
	static Long K, P;
	static Long N;

	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		K = Long.parseLong(st.nextToken());
		P = Long.parseLong(st.nextToken());
		N = Long.parseLong(st.nextToken());

		long sol = K * Sol(P, 10 * N);
		System.out.println(sol);

	}

	private static long Sol(long k, long cnt) {
		if (cnt == 1)
			return k;

		if (cnt % 2 == 0) {
			return Sol(k, cnt / 2) * Sol(k, cnt / 2) % INF;
		} else {
			return Sol(k, cnt / 2) * Sol(k, cnt / 2) * Sol(k, 1) % INF;
		}
	}

}