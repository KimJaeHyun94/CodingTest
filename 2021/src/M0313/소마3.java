package M0313;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 소마3 {
	static int map[][];
	static int max = Integer.MIN_VALUE;
	static int N;

	public static void main(String args[]) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		sol(0, 0, N, N, 0, 0);
		System.out.println(max);

	}

	private static void sol(int r, int c, int r2, int c2, int s, int t) {
		if (t == N) {
			max = Math.max(max, s);
			return;
		}
		int sum = s;
		int maxing= 0;
		if (t == 0) {
			sum = 0;
		} else {
			for (int i = r; i < r2; i++) {
				for (int j = c; i < c2; i++) {
					maxing = Math.max(maxing, map[i][j]);
				}
			}
			sum += maxing;
		}

		sol(r, c, r2, (c + c2) / 2, sum, t + 1);
		sol(r, (c + c2) / 2, r2, c2, sum, t + 1);
		sol(r, c, (r + r2) / 2, c2, sum, t + 1);
		sol((r + r2) / 2, c, r2, c2, sum, t + 1);
	}
}
