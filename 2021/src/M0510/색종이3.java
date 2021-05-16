package M0510;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 색종이3 {
	static int N;
	static int paper[][] = new int[100][100];

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());

			draw(r, c);
		}

		for (int i = 1; i < 100; i++) {
			for (int j = 0; j < 100; j++) {
				if (paper[i][j] == 1) {
					paper[i][j] = paper[i - 1][j] + 1; // 누적합
				}
			}
		}

		System.out.println(Solution());

	}

	private static int Solution() {
		int max = 0;
		int height = 0;
		for (int i = 0; i < 100; i++) {
			for (int j = 0; j < 100; j++) {

				height = Integer.MAX_VALUE;
				for (int k = j; k < 100; k++) {
					if (height > paper[i][k])
						height = paper[i][k];
					if (height == 0)
						break;
					int area = height * (k - j + 1);		//넓이
					max = Math.max(max, area);
				}
			}
		}

		return max;
	}

	private static void draw(int r, int c) {
		for (int i = r; i < r + 10; i++) {
			for (int j = c; j < c + 10; j++) {
				paper[i][j] = 1;
			}
		}

	}

}
