package M0507;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 동방프로젝트Large_2 {
	static int N;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		int arr[] = new int[N + 1];
		int M = Integer.parseInt(br.readLine());

		int ans = 0;
		while (M-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			arr[a]++;
			arr[b]--;
		}

		for (int i = 1; i <= N; i++) {
			arr[i] += arr[i - 1];

			if (arr[i] == 0)
				ans++;
		}

		System.out.println(ans);
	}

}
