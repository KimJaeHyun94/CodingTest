package M0327;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 주사위윷놀이 {
	static int ch[], dice[], now[];
	static boolean[] visited;
	static Yut map[];
	static int max = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		dice = new int[10];

		for (int i = 0; i < 10; i++) {
			dice[i] = Integer.parseInt(st.nextToken()); // 주사위 받아오기
		}

		init();
		DFS(9, 0);
		System.out.println(max);
	}

	private static void init() {
		map = new Yut[43]; // 윳놀이 판 세팅
		ch = new int[10];

		for (int i = 0; i <= 40; i += 2) {
			map[i] = new Yut(i, i + 2); // 지금 현재 점수와 그 다음 말
		}

		map[10].isBlue = map[20].isBlue = map[30].isBlue = true; // 파란색으로 갈 수 있는 곳을 체크해둔다
		map[10].blue = 11; // 지름길은 11번
		map[20].blue = 17;
		map[30].blue = 31;

		map[11] = new Yut(13, 13);
		map[13] = new Yut(16, 15);
		map[15] = new Yut(19, 25);
		map[17] = new Yut(22, 19);
		map[19] = new Yut(24, 25);
		map[25] = new Yut(25, 37);
		map[31] = new Yut(28, 33);
		map[33] = new Yut(27, 35);
		map[35] = new Yut(26, 25);
		map[37] = new Yut(30, 39);
		map[39] = new Yut(35, 40);
		map[42] = new Yut(0, 42);

		DFS(9, 0);

	}

	private static void DFS(int n, int k) {
		if (n == k) {
			now = new int[4];
			visited = new boolean[43];
			sol();
			return;
		}

		for (int i = 0; i < 4; i++) {
			ch[k] = i;
			DFS(n, k + 1);
		}

	}

	private static void sol() {
		int score = 0;

		for (int i = 0; i < 10; i++) {
			int end = horse(ch[i], dice[i]);
			if (end == -1)
				return;
			now[ch[i]] = end;
			score += map[end].score;
		}
		if (max < score)
			max = score;
	}

	private static int horse(int i, int j) {
		int temp = now[i];

		for (int k = 0; k < j; k++) {
			if (k == 0 && map[temp].isBlue) {
				temp = map[temp].blue;
				continue;
			}
			temp = map[temp].red;
		}
		if (temp <= 40 && visited[temp]) {
			return -1;
		} else {
			visited[now[i]] = false;
			visited[temp] = true;
			return temp;
		}
	}

	static class Yut {
		int score, blue, red;
		boolean isBlue = false;

		public Yut(int score, int red) {
			this.score = score;
			this.red = red;
		}

	}

}
