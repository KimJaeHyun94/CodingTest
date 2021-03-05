package M0305;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 두용액 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int arr[] = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(arr);
		int left = 0;
		int right = N - 1;

		int first = 0;
		int second = 0;
		int check = Integer.MAX_VALUE;

		while (left < right) {
			int sum = arr[left] + arr[right];

			if (check > Math.abs(sum)) {
				check = Math.abs(sum);
				first = left;
				second = right;
			}
			if (sum > 0) {
				right--;
			} else if (sum < 0) {
				left++;
			} else
				break;
		}
		System.out.println(arr[first] + " " + arr[second]);
	}
}
