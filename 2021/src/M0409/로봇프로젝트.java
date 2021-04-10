package M0409;

import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class 로봇프로젝트 {
	static int N;
	static long X;
	static int arr[];

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);

		while (sc.hasNext()) {
			X = sc.nextLong();
			N = sc.nextInt();
			arr = new int[N];
			boolean flag = false;
			for (int i = 0; i < N; i++) {
				arr[i] = sc.nextInt();
			}
			X = X * 10000000;
			Arrays.sort(arr);

			int left = 0;
			int right = N - 1;

			while (left < right) {
				long sum = arr[left] + arr[right];

				if (sum == X) {
					flag = true;
					System.out.println("yes"+" "+arr[left] + " " + arr[right]);
					break;
				} else if (sum < X) {
					left++;
				} else {
					right--;
				}
			}
			if(!flag) {
				System.out.println("danger");
			}
		}
	}
}
