package M0228;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ1912연속합 {
	static int N;
	static int map[];
	static int max = Integer.MIN_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		max = Integer.MIN_VALUE;
		N = Integer.parseInt(st.nextToken());
		map = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			map[i] = Integer.parseInt(st.nextToken());
		}
		int sum = 0;
		for (int i = 0; i < N; i++) {
			sum += map[i];
			max = Math.max(sum, max);
			if (sum < 0)
				sum = 0;
		}
		System.out.println(max);
	}

}