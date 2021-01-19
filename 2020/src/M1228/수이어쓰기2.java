package M1228;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 수이어쓰기2 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());

		if (calc(N) < k) {
			System.out.println(-1);
			return;
		}

		int left = 1;
		int right = N;
		int ans = 0;

		while (left <= right) {
			int mid = (left + right) / 2;
			long len = calc(mid);
			if (k > len) {
				left = mid + 1;
			} else {
				ans = mid;
				right = mid - 1;
			}
		}
		String s = String.valueOf(ans);
		long l = calc(ans);
		System.out.println(s.charAt((int) (s.length()-(l-k)-1)));
	}

	static long calc(int n) {
		long ans = 0;
		int len = 1;
		for (int start = 1; start <= n; start *= 10) {
			int end = start * 10 - 1;
			if (end >= n)
				ans += (long) ((n - start + 1) * len);
			else
				ans += (long) ((end - start + 1) * len);
			len++;
		}
		return ans;
	}
}
