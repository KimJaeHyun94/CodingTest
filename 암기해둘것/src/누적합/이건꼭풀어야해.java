package 누적합;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 이건꼭풀어야해 {
	static int N, Q;
	static int sum[], arr[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		Q = Integer.parseInt(st.nextToken());

		sum = new int[N];
		arr = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			int n = Integer.parseInt(st.nextToken());
			arr[i] = n;
		}

		Arrays.sort(arr);
		
		for (int i = 0; i < N; i++) {
			if (i == 0) {
				sum[i] = arr[i];
			} else {
				sum[i] = arr[i] + sum[i - 1];
			}
		}

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < Q; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken()) - 1;
			int e = Integer.parseInt(st.nextToken()) - 1;

			if (s == 0) {
				sb.append(sum[e]).append("\n");
			} else {
				sb.append(sum[e] - sum[s - 1]).append("\n");
			}

		}
		System.out.println(sb);
	}
}
