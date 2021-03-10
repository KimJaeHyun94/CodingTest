package M0309;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 종이의개수 {
	static int arr[][];
	private static int result[] = new int[3];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());

		arr = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		solve(0, 0, N);
		for (int i = 0; i < result.length; i++) {
			System.out.println(result[i]);
		}
	}

	private static void solve(int r, int c, int n) {
		if (isSame(r, c, n)) {
			result[arr[r][c] + 1] += 1;
			return;
		}else {
			int s = n/3;
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 3; j++) {
					solve(r+s*i, c+s*j, s);
				}
			}
		}
		
	}

	private static boolean isSame(int r, int c, int n) {
		int num = arr[r][c];
		for (int i = r; i < r + n; i++) {
			for (int j = c; j < c + n; j++) {
				if (num != arr[i][j]) {
					return false;
				}
			}
		}
		return true;
	}

}
