package M0404;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 용액 {
	static int N;
	static long arr[];

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new long[N];

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Long.parseLong(st.nextToken());
		}

		Arrays.sort(arr);

		int left = 0;
		int right = N - 1;

		long start = 0;
		long end = 0;
		long check = Integer.MAX_VALUE;
	
			while (left < right) {
			long sum = arr[left] + arr[right];

			if (check > Math.abs(sum)) {
				check = Math.abs(sum);
				start = arr[left];
				end = arr[right];
			}
			if (sum > 0)
				right--;
			else
				left++;
		}
		System.out.println(start+" "+end);
	}
}
