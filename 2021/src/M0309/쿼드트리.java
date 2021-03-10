package M0309;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 쿼드트리 {
	private static int M, N;
	private static int map[][];

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		map = new int[N][N];
		int[] num = new int[N];
		
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < N; j++) {
				map[i][j] = str.charAt(j) - '0';
			}
		}

		divide(0, 0, N);

	}

	private static void divide(int r, int c, int n) {
		if (check(r, c, n)) {
			System.out.print(M);
		}else {
			System.out.print("(");
			int mid = n/2;
			for (int i = 0; i < 2; i++) {
				for (int j = 0; j < 2; j++) {
					divide(r+mid*i, c+mid*j, mid);
				}
			}
			System.out.print(")");
		}
	}

	private static boolean check(int r, int c, int n) {
		int num = map[r][c];
		for (int i = r; i < r + n; i++) {
			for (int j = c; j < c + n; j++) {
			
				if (num != map[i][j]) {
					return false;
				}
			}
		}
		M = num;
		return true;
	}

}
