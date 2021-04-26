package 두포인터;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class 귀여운라이언 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		int arr[] = new int[N + 1];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		int min = Integer.MAX_VALUE;
		int left = 0;
		int right = 0;
		int cnt = 0;
		while (true) {
			if (cnt >= K) {
				if (arr[left] == 1) {
					cnt--;
					left++;
				} else
					left++;
			} else if (right >= N)
				break;
			else {
				if (arr[right] == 1) {
					cnt++;
					right++;
				} else
					right++;
			}
			if (cnt == K) {
				min = Math.min(min, right - left);
			}
		}
		if (min == Integer.MAX_VALUE) {
			System.out.println(-1);
		} else
			System.out.println(min);
	}
}
