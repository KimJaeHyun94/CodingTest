package M0227;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ16937두스티커 {
	static int H, W, N;
	static int arr[][];
	static int color[][];
	static int max;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		H = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());

		arr = new int[H][W];
		color = new int[N][2];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			color[i][0] = Integer.parseInt(st.nextToken());
			color[i][1] = Integer.parseInt(st.nextToken());
		}
		DFS(0, 0, 0, 0, 0);
		System.out.println(max);
	}

	private static void DFS(int start, int cnt, int row, int col, int sub) {
		if (cnt == 2) {
			max = Math.max(max, sub);
		} else {
			if (row == 0 && col == 0) {
				for (int i = start; i < color.length; i++) {
					int a = color[i][0];
					int b = color[i][1];

					if (a <= H && b <= W) {
						DFS(i + 1, cnt + 1, a, b, a * b);
					}
					if (b <= H && a <= W) {
						DFS(i + 1, cnt + 1, b, a, a * b);
					}
				}
			} else {
				for (int i = start; i < color.length; i++) {
					int a = color[i][0];
					int b = color[i][1];
					if (a <= H - row && b <= W) {
						DFS(i + 1, cnt + 1, a, b, sub + a * b);
					} else if (a <= H && b <= W - col) {
						DFS(i + 1, cnt + 1, a, b, sub + a * b);
					}
					if (b <= H - row && a <= W) {
						DFS(i + 1, cnt + 1, b, a, sub + a * b);
					} else if (b <= H && a <= W - col) {
						DFS(i + 1, cnt + 1, b, a, sub + a * b);
					}
				}
			}
		}
	}
}
