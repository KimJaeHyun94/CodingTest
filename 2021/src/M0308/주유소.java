package M0308;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 주유소 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		long[] city = new long[N - 1];
		long[] cost = new long[N];

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N - 1; i++) {
			city[i] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			cost[i] = Integer.parseInt(st.nextToken());
		}

		long min = Long.MAX_VALUE;

		long sum = 0;
		for (int i = 0; i < N-1; i++) {
			if (cost[i] < min) {
				min = cost[i];
			}
			sum += min * city[i];
		}
		System.out.println(sum);
	}
}
