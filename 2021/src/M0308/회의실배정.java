package M0308;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class 회의실배정 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		int arr[][] = new int[N][2];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			arr[i][0] = start;
			arr[i][1] = end;
		}

		Arrays.sort(arr, new Comparator<int[]>() {
			@Override
			public int compare(int[] a, int[] b) {
				if (a[1] == b[1])
					return a[0] - b[0];
				return a[1] - b[1];
			}
		});

		int cnt = 0;
		int check = 0;
		for (int i = 0; i < N; i++) {
			if (check <= arr[i][0]) {
				check = arr[i][1];
				cnt++;
			}
		}
		System.out.println(cnt);
	}
}
