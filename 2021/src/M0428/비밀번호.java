package M0428;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 비밀번호 {
	static int N, M;
	static int num[];
	static int selected[];
	static int ans;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		num = new int[10];
		selected = new int[M];
		ans = 0;
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < M; i++) {
			selected[i] = Integer.parseInt(st.nextToken());
		}
		dfs(0);
		System.out.println(ans);
	}

	private static void dfs(int idx) {
		if (idx == N) {
			check();
			return;
		}

		for (int i = 0; i < 10; i++) {
			num[i]++;
			dfs(idx + 1);
			num[i]--;
		}

	}

	private static void check() {
		for (int i = 0; i < M; i++) {
			if (num[selected[i]] == 0) {
				return;
			}
		}
		ans++;

	}
}
