package M0507;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class 수묶기 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		int arr[] = new int[N];
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}

		Arrays.sort(arr);

		int left = 0;
		int right = N - 1;

		int sum = 0;

		while (left < right) { // 음수
			if (arr[left] < 1 && arr[left + 1] < 1) {
				sum += arr[left] * arr[left + 1];
				left += 2;
			} else
				break;
		}

		while (right > 0) { // 양수
			if (arr[right] > 1 && arr[right - 1] > 1) {
				sum += arr[right] * arr[right - 1];
				right -= 2;
			} else
				break;
		}

		for (int i = left; i <= right; i++) {
			sum += arr[i];
		}
		System.out.println(sum);

	}
}
