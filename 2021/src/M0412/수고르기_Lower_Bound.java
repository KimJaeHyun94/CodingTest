package M0412;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 수고르기_Lower_Bound {
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
		int min = Integer.MAX_VALUE;
		
		for (int i = 0; i < N; i++) {
			int left = i;
			int right = N;

			while (left < right) {
				int mid = (left + right) >> 1;
				if (arr[mid] - arr[i] < M) {
					left = mid + 1;
				} else {
					right = mid;
				}
			}
			if (right == N)
				continue;
			min = Math.min(min, arr[right] - arr[i]);
			if (min == M) {
				break;
			}
		}

		System.out.println(min);
	}
}
