package M0412;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 주몽 {
	static int N, M;
	static int arr[];

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());

		arr = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {

			arr[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(arr);

		int left = 0;
		int right = N-1;

		int cnt = 0;
		while (left < right && right<N) {
			int sum = arr[left] + arr[right];
			if (sum <= M) {
				if (sum == M) {
					cnt++;
				}
				left++;
				
			} else {
				right--;
			}

		}
		System.out.println(cnt);
	}
}
