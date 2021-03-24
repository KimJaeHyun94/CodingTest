package M0322;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 게리맨더링2 {
	static int[][] map;
	static boolean[][] visited;
	static int N, ans;
	static int Sum = 0;
	static int min = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		ans = Integer.MAX_VALUE;
		map = new int[N + 1][N + 1];

		for (int i = 1; i <= N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				Sum += map[i][j];
			}
		}

		for (int x = 1; x <= N; x++) {
			for (int y = 1; y <= N; y++) {
				for (int d1 = 1; d1 <= N; d1++) {
					for (int d2 = 1; d2 <= N; d2++) {
						if (x + d1 + d2 > N)
							continue;
						if (y - d1 < 1 || y + d2 > N)
							continue;

						solution(x, y, d1, d2);
					}
				}
			}
		}
		System.out.println(min);
	}

	private static void solution(int x, int y, int d1, int d2) { // 기준점, d1,d2 를 가지고 구함
		// (x, y), (x+1, y-1), ..., (x+d1, y-d1)
		// (x, y), (x+1, y+1), ..., (x+d2, y+d2)
		// (x+d1, y-d1), (x+d1+1, y-d1+1), ... (x+d1+d2, y-d1+d2)
		// (x+d2, y+d2), (x+d2+1, y+d2-1), ..., (x+d2+d1, y+d2-d1)

		visited = new boolean[N + 1][N + 1];

		for (int i = 0; i <= d1; i++) {
			visited[x + i][y - i] = true;
			visited[x + d2 + i][y + d2 - i] = true;
		}

		for (int i = 0; i <= d2; i++) {
			visited[x + i][y + i] = true;
			visited[x + d1 + i][y - d1 + i] = true;
		}

		for (int i = x + 1; i < x + d1 + d2; i++) { // 안에 있는 5 체크
			for (int j = 1; j <= N; j++) {
				if (visited[i][j]) {
					while (true) {
						j++;
						if (j > N || visited[i][j])
							break;
						visited[i][j] = true;
					}
				}
			}
		}

		int sum[] = new int[5];

		for (int r = 1; r < x + d1; r++) {
			for (int c = 1; c <= y; c++) {
				if (visited[r][c])
					continue;
				sum[0] += map[r][c]; // 1번인 인구수 더하기
			}
		}

		for (int r = 1; r <= x + d2; r++) {
			for (int c = y + 1; c <= N; c++) {
				if (visited[r][c])
					continue;
				sum[1] += map[r][c]; // 2번인 인구수 더하기
			}
		}

		for (int r = x + d1; r <= N; r++) {
			for (int c = 1; c < y - d1 + d2; c++) {
				if (visited[r][c])
					continue;
				sum[2] += map[r][c]; // 3번인 인구수 더하기
			}
		}

		for (int r = x + d2 + 1; r <= N; r++) {
			for (int c = y - d1 + d2; c <= N; c++) {
				if (visited[r][c])
					continue;
				sum[3] += map[r][c]; // 4번인 인구수 더하기
			}
		}

		sum[4] = Sum - (sum[0] + sum[1] + sum[2] + sum[3]);
		Arrays.sort(sum);
		min = Math.min(min, sum[4] - sum[0]);

	}
}
