package M0317;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class 마법사상어와파이어볼 {
	static int N, M, K;
	static List<Fireball> map[][];
	static int[][] dirs = { { -1, 0 }, { -1, 1 }, { 0, 1 }, { 1, 1 }, { 1, 0 }, { 1, -1 }, { 0, -1 }, { -1, -1 } };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		map = new List[N][N];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				map[i][j] = new ArrayList<>();
			}
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken()) - 1;
			int c = Integer.parseInt(st.nextToken()) - 1;
			int m = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			map[r][c].add(new Fireball(m, s, d)); // 각각의 파이어볼에 질량, 방향, 속력
		}
		for (int i = 0; i < K; i++) {
			Game();
		}

		int sum = 0;
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				for (Fireball fire : map[r][c]) {
					sum += fire.m;
				}
			}
		}
		System.out.println(sum);
	}

	private static void Game() {
		List<Fireball> temp[][] = new List[N][N];// copy

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				temp[i][j] = new ArrayList<>();
			}
		}

		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {

				int size = map[r][c].size(); // 파이어볼 안의 사이즈
				if (size > 0) { // 1개 이상인 것들에서
					for (int k = 0; k < size; k++) {
						Fireball cur = map[r][c].get(k); // 움직일 파이어볼

						int dr = r + dirs[cur.d][0] * (cur.s % N);
						int dc = c + dirs[cur.d][1] * (cur.s % N);

						if (dr >= N)
							dr %= N;
						else if (dr < 0)
							dr += N;

						if (dc >= N) {
							dc %= N;
						} else if (dc < 0) {
							dc += N;
						}
						temp[dr][dc].add(cur); // 원래 기존 정보들을 입력
					}
				}
			}
		}

		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				int size = temp[r][c].size(); // 파이어볼 안의 사이즈

				if (size > 1) { // 2개 이상의 파이어볼이 있는 칸에서는 다음과 같은 일이 일어난다.
					int m = 0; // 좌표 안에 있는 파아이볼 질량
					int s = 0; // 좌표 안에 있는 파이어볼 속력
					int odd = 0; // 홀수
					int even = 0; // 짝수

					for (int i = 0; i < temp[r][c].size(); i++) {

					}
					for (Fireball fire : temp[r][c]) {
						m += fire.m;
						s += fire.s;
						if (fire.d % 2 == 0) {
							even++;
						} else {
							odd++;
						}
					}

					temp[r][c] = new ArrayList<>();
					m /= 5; // 질량은 ⌊(합쳐진 파이어볼 질량의 합)/5⌋
					s /= size; // 속력은 ⌊(합쳐진 파이어볼 속력의 합)/(합쳐진 파이어볼의 개수)⌋

					if (m > 0) { // 질량이 0 이상이여야 입력한다
						if (even == size || odd == size) { // 둘 중 하나라도 가지고있다면
							for (int d = 0; d <= 6; d += 2) {
								temp[r][c].add(new Fireball(m, s, d));
							}
						} else {
							for (int d = 1; d <= 7; d += 2) {
								temp[r][c].add(new Fireball(m, s, d));
							}
						}
					}
				}
			}
		}
		map = temp;

	}

	static class Fireball {
		int m;
		int s;
		int d;

		public Fireball(int m, int s, int d) {
			this.m = m;
			this.s = s;
			this.d = d;
		}
	}
}
