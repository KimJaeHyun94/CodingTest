package 두포인터;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 좋다 {
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

		int cnt = 0;
		for (int i = 0; i < N; i++) {
			long val = arr[i];
			int left = 0;
			int right = N - 1;

			while (left < right) {
				long sum = arr[left] + arr[right];
				if (sum == val) {
					if (left != i && right != i) {
						cnt++;
						break;
					} else if (left == i) {
						left++;
					} else if (right == i)
						right--;
				}
				else if (sum < val)
					left++;
				else
					right--;
			}
		}
		System.out.println(cnt);
	}

}
