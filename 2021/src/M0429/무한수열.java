package M0429;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class 무한수열 {
	static long N;
	static int P, Q;
	static HashMap<Long, Long> map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Long.parseLong(st.nextToken());
		P = Integer.parseInt(st.nextToken());
		Q = Integer.parseInt(st.nextToken());
		map = new HashMap();
		System.out.println(dfs(N, P, Q));

	}

	private static long dfs(long n, int p, int q) {
		if (n == 0) {
			return 1;
		}

		if (map.containsKey(n)) {
			return map.get(n);
		}
		long v = dfs(n / p, p, q) + dfs(n / q, p, q);
		map.put(n, v);

		return v;
	}
}
