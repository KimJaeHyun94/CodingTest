package M0502;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 별찍기11 {
	static boolean board[][];
	static int N;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		board = new boolean[N][2 * N];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < 2 * N - 1; j++) {
				board[i][j] = false;
			}
		}

		sol(N, 0, N - 1);
		
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < 2 * N - 1; j++) {
				if(board[i][j])
					sb.append("*");
				else
					sb.append(" ");
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}

	private static void sol(int n, int r, int c) {
		if (n == 3) {
			board[r][c] = true;
			board[r + 1][c - 1] = true;
			board[r + 1][c + 1] = true;
			for (int i = 0; i < 5; i++) {
				board[r + 2][c - i + 2] = true;
			}
			return;
		}
		sol(n / 2, r, c);
		sol(n / 2, r + n / 2, c - n / 2);
		sol(n / 2, r + n / 2, c + n / 2);
	}
}
