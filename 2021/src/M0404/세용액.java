package M0404;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 세용액 {
	static int N;
	static long arr[];
	static long[] pick = new long[3];

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new long[N];

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Long.parseLong(st.nextToken());
		}

		Arrays.sort(arr);
		long check = Long.MAX_VALUE;

		for (int i = 0; i < N - 2; i++) {
			int left = i + 1;
			int right = N - 1;

			while (left < right) {
				long sum = arr[i] + arr[left] + arr[right];

				if (check > Math.abs(sum)) {
					pick[0] = arr[left];
					pick[1] = arr[right];
					pick[2] = arr[i];
					check = Math.abs(sum);
				}

				if (sum > 0)
					right--;
				else
					left++;
			}
		}
		Arrays.sort(pick);
		for (int i = 0; i < 3; i++) {
			System.out.print(pick[i]+" ");
		}

	}
}
