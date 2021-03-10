package M0309;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 피보나치6곱셈 {
	static long mod = 1000000007;

	public static void main(String[] ar) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		long n = Long.parseLong(br.readLine());

		long[][] ansMatrix = { { 1, 0 }, { 0, 1 } };
		long[][] matrix = { { 1, 1 }, { 1, 0 } };

		if (n <= 1) {
			System.out.println(n);
			System.exit(0);
		}
		while (n > 0) {
			if (n % 2 == 1)
				ansMatrix = matrixMultiply(ansMatrix, matrix);
			matrix = matrixMultiply(matrix, matrix);
			n /= 2;
		}

		System.out.print(ansMatrix[0][1]);

	}

	public static long[][] matrixMultiply(long[][] matrix1, long[][] matrix2) {
		int m1 = matrix1.length;
		int n1 = matrix1[0].length;
		int n2 = matrix2[0].length;
		long[][] temp = new long[m1][n2];

		for (int i = 0; i < m1; i++) {
			for (int j = 0; j < n2; j++) {
				for (int k = 0; k < n1; k++) {
					temp[i][j] += matrix1[i][k] * matrix2[k][j];
				}
				temp[i][j] %= mod;
			}
		}
		return temp;
	}
}
