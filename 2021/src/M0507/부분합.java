package M0507;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 부분합 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int S = Integer.parseInt(st.nextToken());

		int arr[] = new int[N];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		int left = 0;
		int right = 0;
		int sum = 0;
		int ans = Integer.MAX_VALUE;

		while (true) {
			if (right == N) {
				break;
			} else if (sum >= S) {
				sum -= arr[left++];
				ans = Math.min(ans, right-left+1);
			} else
				sum += arr[right++];
		}
		System.out.println(ans);
	}
}
