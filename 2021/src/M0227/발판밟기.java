package M0227;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 발판밟기 {
	static int cnt = 0;
	static int news[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		int map[] = new int[N + 1];
		int check[] = new int[N + 1];
		int ans = 0;
		int minus = 0;
		int max = 0;
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			map[i] = Integer.parseInt(st.nextToken());
			if (check[i + map[i]] == 0) {
				check[i + map[i]] = 1;
			} else if (check[i + map[i]] == 1) {
				minus++;
				check[i + map[i]] += 1;
			} else
				continue;
		}

		max = N - minus;
		for (int i = 1; i <= 3; i++) {
			int start = map[i];
			news = new int[N + 1];
			cnt = 1;
			play(i, map);
			ans = Math.max(ans, cnt);
			if (cnt == max)
				break;
		}

		System.out.println(ans);
	}

	private static void play(int i, int[] map) {
		if (news[i + map[i]] != 0) {
			cnt++;
			return;
		} else {
			cnt++;
			news[i + map[i]] = 1;
			play(i + map[i], map);
		}
	}
}
