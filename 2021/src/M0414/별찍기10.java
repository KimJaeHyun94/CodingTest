package M0414;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class 별찍기10 {
	static char[][] star;
	static int N;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		star = new char[N][N];
		for (int i = 0; i < N; i++) {
			Arrays.fill(star[i], ' ');
		}
		draw(0, 0, N);
		for (int i = 0; i < N; i++) {
			System.out.println(star[i]);
		}
	}

	private static void draw(int r, int c, int n) {
		if (n == 1) {
			star[r][c] = '*';
			return;
		}

		int len = n / 3;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (i == 1 && j == 1)
					continue;

				draw(r + (i * len), c + (j * len), len);

			}
		}

	}

}
