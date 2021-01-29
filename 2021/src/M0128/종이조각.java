package M0128;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 종이조각 {
	static int R, C, N;
	static boolean visited[][];
	static int[][] map;
	static int ans;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		visited = new boolean[R][C];
		map = new int[R][C];

		for (int r = 0; r < R; r++) {
			String line = br.readLine();
			for (int c = 0; c < C; c++) {
				map[r][c] = line.charAt(c) - '0';
			}
		}
		DFS(0, 0);
		System.out.println(ans);

	}

	private static void DFS(int r, int c) {
		if (r == R) {
			ans = Math.max(ans, sol());
			return;
		}

		if (c == C) {
			DFS(r + 1, 0); // 행을 다채우면 열을 올려준다
			return; // 밑에 것들을 수행하지 않는다
		}

		visited[r][c] = true;
		DFS(r, c + 1);
		visited[r][c] = false;
		DFS(r, c + 1);
	}

	private static int sol() {
		int sum = 0;
		for (int r = 0; r < R; r++) {
			int gsum = 0; // 가로 종이 모양 더하기
			for (int c = 0; c < C; c++) {
				if (visited[r][c]) {
					gsum *= 10; // 자릿수를 증가시키기 위해서
					gsum += map[r][c]; // true되어있는것들을 모두 더해준다
				} else {
					sum += gsum; // 지금까지 계산한것들을 더해놓는다
					gsum = 0; // 다음 줄로 넘어가기 위해 초기화해준다
				}
			}
			sum += gsum; // 맨마지막에 것을 더해준다(마지막 종료 조건이 없을 때를 대비)
		}

		for (int c = 0; c < C; c++) {
			int ssum = 0; // 세로 종이 모양 더하기
			for (int r = 0; r < R; r++) {
				if (!visited[r][c]) {
					ssum *= 10; // 자릿수를 증가시키기 위해서
					ssum += map[r][c]; // false되어있는것들을 모두 더해준다
				} else {
					sum += ssum; // 지금까지 계산한것들을 더해놓는다
					ssum = 0; // 다음 줄로 넘어가기 위해 초기화해준다
				}
			}
			sum += ssum; // 맨마지막에 것을 더해준다(마지막 종료 조건이 없을 때를 대비)
		}
		return sum;
	}

}
