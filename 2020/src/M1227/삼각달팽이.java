package M1227;

import java.util.Arrays;

public class 삼각달팽이 {

	public static void main(String[] args) {
		System.out.println(Arrays.toString(solution(5)));
	}

	public static int[] solution(int n) {
		int[] answer = {};
		if (n == 1) {
			return new int[] { 1 };
		}
		int N = n;
		answer = new int[n * (n + 1) / 2]; // 삼각형의 갯수
		int[][] arr = new int[n][n];

		int x = -1;
		int y = 0;
		int s = 1;

		while (true) {
			for (int i = 0; i < n; i++) {

				arr[++x][y] = s++;

			}
			n--;

			if (n == 0)
				break;

			for (int i = 0; i < n; i++) {
				arr[x][++y] = s++;
			}
			n--;
			if (n == 0)
				break;

			for (int i = 0; i < n; i++) {
				arr[--x][--y] = s++;
			}
			n--;

			if (n == 0)
				break;
		}
		int idx = 0;

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < arr[i].length; j++) {

				if (arr[i][j] == 0)
					break;
				else
					answer[idx++] = arr[i][j];
			}
		}
		return answer;
	}
}
