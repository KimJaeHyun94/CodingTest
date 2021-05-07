package M0505;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 내선물을받아줘2 {
	static int N;
	static int map[];
	static int dirs[] = { -1, 1 };
	static int dp[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		map = new int[N];
		dp = new int[N];
		String line = br.readLine();
		for (int i = 0; i < N; i++) {
			map[i] = findDir(line.charAt(i));

		}

		int cnt = 1;
		for (int i = 0; i < N; i++) {
			if (cnt == dfs(i, cnt)) {
				cnt++;
			}
		}
		System.out.println(cnt - 1);

	}

	private static int dfs(int i, int cnt) {
		if (dp[i] != 0)
			return dp[i];
		dp[i] = cnt;

		int di = i + dirs[map[i]];
		dp[i] = dfs(di, cnt);
		return dp[i];
	}

	private static int findDir(char c) {
		switch (c) {
		case 'W':
			return 0;
		case 'E':
			return 1;
		}
		return -1;
	}
}
