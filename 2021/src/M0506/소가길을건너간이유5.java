package M0506;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 소가길을건너간이유5 {
	static int N, K, B;
	static int arr[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());

		arr = new int[N + 1];
		Arrays.fill(arr, 1);
		for (int i = 0; i < B; i++) {
			arr[Integer.parseInt(br.readLine())] = 0;
		}

		for (int i = 1; i <= N; i++) {
			arr[i] += arr[i - 1]; // 누적합
		}

		int ans = Integer.MAX_VALUE;
		for (int i = K; i <= N; i++) {
			ans = Math.min(ans, K - (arr[i] - arr[i - K]));
		}
		System.out.println(ans);

	}
}
