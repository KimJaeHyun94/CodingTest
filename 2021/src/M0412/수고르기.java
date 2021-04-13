package M0412;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 수고르기 {
	static int N, M;
	static int arr[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		arr = new int[N];

		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		Arrays.sort(arr);

		int left = 0;
		int right = 0;

		int min = Integer.MAX_VALUE;
		while (right < N) {
			int sum = arr[right] - arr[left];
			if (sum == M) {
				min = sum;
				break;
			} else if (sum > M) {
				left++;
				min = Math.min(min, sum);
			} else {
				right++;
			}

		}
		System.out.println(min);
	}
}
