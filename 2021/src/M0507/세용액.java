package M0507;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 세용액 {
	static long arr[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		arr = new long[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Long.parseLong(st.nextToken());
		}
		Arrays.sort(arr);

		long ans = Long.MAX_VALUE;
		int o = 0;
		int l = 0;
		int r = 0;

		outer: for (int i = 0; i < N - 2; i++) {
			int left = i + 1;
			int right = N - 1;

			while (left < right) {
				long sum = arr[i] + arr[left] + arr[right];

				if (ans > Math.abs(sum)) {
					o = i;
					l = left;
					r = right;
					ans = Math.abs(sum);
				}

				if (sum == 0)
					break outer;
				if(sum>0) {
					right--;
				}else
					left++;
			}

		}
		System.out.println(arr[o]+" "+arr[l]+" "+arr[r]);
	}
}
