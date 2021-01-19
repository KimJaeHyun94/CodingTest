package M0107;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 맞춰봐 {
	static int N;

	static char[][] map;
	static int[] arr;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		String line = br.readLine();
		arr = new int[N];
		map = new char[N][N];
		int idx = 0;
		
		for (int i = 0; i < N; i++) {
			for (int j = i; j < N; j++) {
				map[i][j] = line.charAt(idx++);
			}
		}

		dfs(0);

	}

	private static void dfs(int cnt) {
		if (cnt == N) {
			for (int i : arr) {
				System.out.print(i + " ");
			}
			System.exit(0);
		}

		for (int i = -10; i <= 10; i++) {
			arr[cnt] = i;
			if (check(cnt))
				dfs(cnt + 1);
		}
	}

	private static boolean check(int cnt) {
		int sum = 0;
		for (int i = cnt; i >= 0; i--) {
			sum += arr[i];
			if (map[i][cnt] == '+' && sum <= 0)
				return false;
			if (map[i][cnt] == '-' && sum >= 0)
				return false;
			if (map[i][cnt] == '0' && sum != 0)
				return false;
		}
		return true;
	}

}
