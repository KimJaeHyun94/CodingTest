package M0309;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 행렬제곱 {
	static int N;
	static int map[][];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		long B = Long.parseLong(st.nextToken());

		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		int[][] A = sol(map, B);

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++)
				System.out.print(A[i][j] % 1000 + " ");
			System.out.println();
		}
	}

	private static int[][] sol(int[][] a, long b) {
		if (b == 1) {
			return a;
		} else if (b % 2 == 0) {
			int[][] temp = sol(a, b / 2);
			return square(temp, temp);
		}
		return square(sol(a, b - 1), a);
	}

	private static int[][] square(int[][] a, int[][] A) {
		int[][] temp = new int[N][N];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				int sum = 0;
				for (int k = 0; k < N; k++) {
					sum += a[i][k] * A[k][j];
				}
				temp[i][j] = sum % 1000;
			}
		}

		return temp;
	}
}
