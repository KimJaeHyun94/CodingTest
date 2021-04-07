package M0403;

import java.util.Arrays;

public class test3 {
	static int N;
	static int arr[][];
	static int INF = 987654321;

	public static void main(String[] args) {
		int[] passenger = { 1, 1, 1, 1, 1, 1 };
		int[] passenger2 = { 2, 1, 2, 2 };
		int[] passenger3 = { 1, 1, 2, 3, 4 };

		int[][] train = { { 1, 2 }, { 1, 3 }, { 1, 4 }, { 3, 5 }, { 3, 6 } };
		int[][] train2 = { { 1, 2 }, { 1, 3 }, { 2, 4 } };
		int[][] train3 = { { 1, 2 }, { 1, 3 }, { 1, 4 }, { 1, 5 } };
		System.out.println(Arrays.toString(solution(6, passenger, train)));
		System.out.println(Arrays.toString(solution(4, passenger2, train2)));
		System.out.println(Arrays.toString(solution(5, passenger3, train3)));
	}

	public static int[] solution(int n, int[] passenger, int[][] train) {
		N = n;
		arr = new int[N + 1][N + 1];
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				if (i == j)
					continue;
				arr[i][j] = INF;
			}
		}
		for (int i = 0; i < train.length; i++) {
			int a = train[i][0];
			int b = train[i][1];

			arr[a][b] = passenger[b - 1];
			arr[b][a] = passenger[a - 1];
		}

		FloydWarshall();
		int max = 0;
		int maxidx = 0;

		for (int i = 2; i <= N; i++) {
			if (arr[1][i] != INF && max < arr[1][i]) {
				max = arr[1][i];
				maxidx = i;
			} else if (max == arr[1][i]) {
				if (i > maxidx) {
					maxidx = i;
				}
			}
		}

		int[] answer = new int[2];
		answer[0] = maxidx;
		answer[1] = max + passenger[0];
		return answer;
	}

	private static void FloydWarshall() {
		for (int k = 1; k <= N; k++) {
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					arr[i][j] = Math.min(arr[i][j], arr[i][k] + arr[k][j]);
				}
			}
		}
	}
}
