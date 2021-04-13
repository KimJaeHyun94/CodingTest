package M0412;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 게으른백곰 {
	static int N, K;
	static int dp[];
	static int MAX = 1000001;
	static int ans = Integer.MIN_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		int arr[] = new int[MAX];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int g = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
			arr[x] = g;
		}

		K = K * 2 + 1;

		int s = 0;
		for (int i = 0; i < MAX; i++) {
			if (i >= K)
				s -= arr[i - K];
			s += arr[i];
			ans = Math.max(ans, s);
		}

		System.out.println(ans);
	}
}
