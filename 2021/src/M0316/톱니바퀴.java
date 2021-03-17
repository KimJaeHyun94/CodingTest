package M0316;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 톱니바퀴 {
	static int arr[][];
	static int sum = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		arr = new int[4][8];
		for (int r = 0; r < 4; r++) {
			String str = br.readLine();
			for (int c = 0; c < 8; c++) {
				arr[r][c] = str.charAt(c) - '0';
			}
		}
		int K = Integer.parseInt(br.readLine());

		for (int i = 0; i < K; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken())-1;
			int dir = Integer.parseInt(st.nextToken());
			turnLeft(s - 1, -dir);
			turnRight(s + 1, -dir);
			Rotate(s, dir);
		}
		sol();
		System.out.println(sum);
	}

	private static void sol() {
		int d = 1;
		for (int i = 0; i < 4; i++) {
			sum += arr[i][0] * d;
			d*=2;
		}
	}

	private static void Rotate(int s, int dir) {

		if (dir == 1) {
			int temp = arr[s][7];
			for (int i = 7; i >= 1; i--) {
				arr[s][i] = arr[s][i - 1];
			}
			arr[s][0] = temp;
		} else {
			int temp = arr[s][0];
			for (int i = 0; i < 7; i++) {
				arr[s][i] = arr[s][i + 1];
			}
			arr[s][7] = temp;
		}

	}

	private static void turnRight(int s, int dir) {
		if (s > 3)
			return;

		if (arr[s][6] != arr[s - 1][2]) {
			turnRight(s + 1, -dir);
			Rotate(s, dir);
		}
	}

	private static void turnLeft(int s, int dir) {
		if (s < 0)
			return;

		if (arr[s][2] != arr[s + 1][6]) {
			turnLeft(s - 1, -dir);
			Rotate(s, dir);
		}
	}
}
