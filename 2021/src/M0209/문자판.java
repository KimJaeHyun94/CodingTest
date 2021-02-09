package M0209;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
/**
 * 
 * @author AKKabiyo
 * @see https://soboruya.tistory.com/38
 */
public class 문자판 {
	static int N, M, K, L;
	static char map[][];
	static int memo[][][];
	static char Route[];
	static int[][] dirs = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		map = new char[N][M];
		for (int r = 0; r < N; r++) {
			String line = br.readLine();
			for (int c = 0; c < M; c++) {
				map[r][c] = line.charAt(c);
			}
		}

		String line = br.readLine();
		Route = line.toCharArray();
		L = Route.length;
		memo = new int[N][M][Route.length];

		for (int r = 0; r < N; r++) { // 메모라이즈 초기화
			for (int c = 0; c < M; c++) {
				Arrays.fill(memo[r][c], -1);
			}
		}

		int ans = 0;
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				if (map[r][c] == Route[0]) {   //출발 지점이 같아야 출발한다. 
					ans += DFS(r, c, 0);
				}
			}
		}
		System.out.println(ans);
	}

	private static int DFS(int r, int c, int cnt) {
		if (cnt == L - 1) {
			memo[r][c][L - 1] = 1; // 맨 마지막은 1
			return 1;
		}
		if (memo[r][c][cnt] >= 0) {
			return memo[r][c][cnt]; // 만약에 방문한 적이 있다면 리턴
		}
		memo[r][c][cnt] = 0;
		for (int d = 0; d < dirs.length; d++) {
			for (int k = 1; k <= K; k++) {
				int dr = r + dirs[d][0] * k;
				int dc = c + dirs[d][1] * k;

				if (isOK(dr, dc) && Route[cnt + 1] == map[dr][dc]) { // 범위안에 있고 다음 문자가 가는 길이 맞다면
					memo[r][c][cnt] += DFS(dr, dc, cnt + 1);
				}
			}
		}
		return memo[r][c][cnt];
	}

	private static boolean isOK(int dr, int dc) {
		return dr >= 0 && dr < N && dc >= 0 && dc < M;
	}
}
