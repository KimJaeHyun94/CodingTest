package M0317;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 야구 {
	static int N;
	static int order[] = new int[10];
	static int player[][];
	static boolean visited[] = new boolean[10];
	static int MAX = Integer.MIN_VALUE;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		player = new int[N + 1][10];
		for (int i = 1; i <= N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= 9; j++) {
				player[i][j] = Integer.parseInt(st.nextToken()); // 이닝에 타순 별로 친 것들을 넣어준다.
			}

		}

		order[4] = 1; // 4번째 타자는 1번이 친다
		visited[4] = true; // 4번째 자리는 정해짐

		DFS(2); // 일단 한명이 정해져 있으므로
		System.out.println(MAX);
	}

	private static void DFS(int cnt) {
		if (cnt == 10) {
			playGame(); // 다 뽑았으면
			return;
		}
		for (int i = 1; i <= 9; i++) {
			if (!visited[i] && order[i] == 0) {
				visited[i] = true;
				order[i] = cnt; // 2번부터 넣어준다.
				DFS(cnt + 1);
				order[i] = 0;
				visited[i] = false;
			}
		}

	}

	private static void playGame() {
		int batter = 1; // 1번부터 시작
		int sum = 0;
		for (int i = 1; i <= N; i++) { // 각 이닝마다
			int out = 0;
			boolean roo[] = new boolean[4]; // 1루, 2루, 3루
			while (out < 3) {
				if (batter > 9)
					batter = 1;
				int bat = player[i][order[batter]];

				if (bat == 0) { // 아웃
					out++;
				} else if (bat == 1) {
					if (roo[3]) {
						roo[3] = false;
						sum++;
					}
					for (int j = 2; j >= 1; j--) {
						if (roo[j]) {
							roo[j] = false;
							roo[j + 1] = true;
						}
					}
					roo[1] = true;
				} else if (bat == 2) {
					if (roo[3]) {
						roo[3] = false;
						sum++;
					}
					if (roo[2]) {
						roo[2] = false;
						sum++;
					}
					if (roo[1]) {
						roo[1] = false;
						roo[3] = true;
					}
					roo[2] = true;
				} else if (bat == 3) {
					for (int j = 1; j <= 3; j++) {
						if (roo[j]) {
							roo[j] = false;
							sum++;
						}
					}
					roo[3] = true;
				} else {
					for (int j = 1; j <= 3; j++) {
						if (roo[j]) {
							roo[j] = false;
							sum++;
						}
					}
					sum++;
				}
				batter++;
			}
		}
		MAX = Math.max(MAX, sum);
	}
}
