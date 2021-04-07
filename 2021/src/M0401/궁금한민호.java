package M0401;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 궁금한민호 {
	static int arr[][];
	static int dist[][];
	static int N;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new int[N + 1][N + 1];
		dist = new int[N + 1][N + 1];
		for (int r = 1; r <= N; r++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int c = 1; c <= N; c++) {
				arr[r][c] = Integer.parseInt(st.nextToken());
				dist[r][c] = arr[r][c];
			}
		}
		FloydWarshall();
		int max = 0;

		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				max += dist[i][j];
			}
		}
		System.out.println(max/2);

	}

	private static void FloydWarshall() {
		for (int k = 1; k <= N; k++) {
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					if(i==k || j==k) continue;
					if (arr[i][j] == arr[i][k] + arr[k][j]) {
						dist[i][j] = 0;
					}
					if (arr[i][j] > arr[i][k] + arr[k][j]) {	//경로가 거쳐서가는 것이 더 짧다면 모순
						System.out.println("-1");
						System.exit(0);
					}
				}
			}
		}

	}
}
