package M0404;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 예산 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		long arr[] = new long[N];
		long sum = 0;
		long max = 0;
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Long.parseLong(st.nextToken());
			sum += arr[i];
			max = Math.max(max, arr[i]);
		}
		Arrays.sort(arr);
		int M = Integer.parseInt(br.readLine());

		long left = 1;
		long right = arr[N - 1];

		while (left <= right) {
			long mid = (left + right) >> 1;
			long ans = 0;

			for (int i = 0; i < N; i++) {
				if (arr[i] > mid) {
					ans += mid;
				} else {
					ans += arr[i];
				}
			}

			if (ans > M) {
				right = mid - 1;
			} else {
				left = mid + 1;
			}

		}
		System.out.println(left-1);
	}

}
