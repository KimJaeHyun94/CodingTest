package 두포인터;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 같이눈사람만들래 {
	static int N;
	static int arr[];

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(arr);

		int ans = Integer.MAX_VALUE;

		for (int i = 0; i < N - 3; i++) {
			for (int j = i + 3; j < N; j++) {
				int left = i + 1;
				int right = j - 1;

				while (left < right) {
					int sum = arr[left] + arr[right] - arr[i] - arr[j];
					ans = Math.min(ans, Math.abs(sum));
					if (sum > 0) {
						right--;
					} else
						left++;
				}
				if (ans == 0) {
					System.out.println(0);
					System.exit(0);
				}
			}
		}
		System.out.println(ans);

	}

}
