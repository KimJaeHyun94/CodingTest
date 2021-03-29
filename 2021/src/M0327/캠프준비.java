package M0327;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 캠프준비 {
	static int N, L, R, X;
	static int A[];
	static int ans;
	static boolean visited[];
	static int MAX = 0, MIN = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());

		A = new int[N];
		visited = new boolean[N];
		st = new StringTokenizer(br.readLine());

		for (int i = 0; i < N; i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(A);

		DFS(0, 0, 0, new int[N]);
		System.out.println(ans);
	}

	private static void DFS(int idx, int sum, int cnt, int temp[]) {

		if (sum > R)
			return;

		if (cnt >= 2) {
			if (sum >= L && sum <= R) {
				if (temp[cnt - 1] - temp[0] >= X)
					ans++;
			}
		}

		for (int i = idx; i < N; i++) {
			if (!visited[i]) {
				visited[i] = true;
				temp[cnt] = A[i];
				DFS(i, sum + A[i], cnt + 1, temp);
				visited[i] = false;
				temp[cnt] = 0;

			}
		}

	}

}
