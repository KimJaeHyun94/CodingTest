package M1212;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 수들의합2 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[] arr = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < arr.length; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		int start = 0, end = 0, sum = arr[0];
		int ans = 0;

		while (true) {
			if (sum == M) {
				ans++;
			}
			if (sum >= M) {
				sum -= arr[start++];
			} else if (end == N - 1) {
				break;
			} else {
				end++;
				sum += arr[end];
			}
		}
		System.out.println(ans);
	}
}
