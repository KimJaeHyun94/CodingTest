package M0404;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 숫자카드 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int arr[] = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {

			arr[i] = Integer.parseInt(st.nextToken());
		}
		int M = Integer.parseInt(br.readLine());
		int check[] = new int[M];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < M; i++) {

			check[i] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(arr);

		for (int i = 0; i < check.length; i++) {
			System.out.print(binarySearch(check[i], arr) + " ");

		}

	}

	private static int binarySearch(int i, int[] arr) {
		int left =0;
		int right = arr.length-1;
		
		while (left <= right) {
			int mididx = (left + right) >> 1;
			
			if (arr[mididx] == i)
				return 1;
			else if (arr[mididx] > i)
				right = mididx - 1;

			else
				left = mididx + 1;
		}

		return 0;
	}

}
