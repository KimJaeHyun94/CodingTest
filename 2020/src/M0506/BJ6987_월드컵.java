package M0506;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ6987_월드컵 {
	public static int win[] = new int[6];
	public static int draw[] = new int[6];
	public static int lose[] = new int[6];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		for (int a = 0; a < 4; a++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 0; i < 6; i++) {
				win[i] = Integer.parseInt(st.nextToken());
				draw[i] = Integer.parseInt(st.nextToken());
				lose[i] = Integer.parseInt(st.nextToken());
			}
			int check = dfs(0, 1);
			if (check < 0) {
				System.out.print(0 + " ");
			} else
				System.out.print(1 + " ");
		}
		System.out.println();
	}

	private static int dfs(int a, int b) {
		if (a == 5) {
			int twin = 0, tlose = 0, tdraw = 0;
			for (int i = 0; i < 6; i++) {
				twin += win[i];
				tdraw += draw[i];
				tlose += lose[i];
			}
			if (twin + tlose + tdraw == 0) {
				return 1;
			} else
				return -1;
		}
		int ret = -1;
		if (win[a] > 0 && lose[b] > 0) {
			win[a] -= 1;
			lose[b] -= 1;

			if (b == 5)
				ret = dfs(a + 1, a + 2);
			else
				ret = dfs(a, b + 1);
			win[a] += 1;
			lose[b] += 1;
		}
		if (ret != 1) {
			if (win[b] > 0 && lose[a] > 0) {
				win[b] -= 1;
				lose[a] -= 1;

				if (b == 5)
					ret = dfs(a + 1, a + 2);
				else
					ret = dfs(a, b + 1);
				win[b] += 1;
				lose[a] += 1;
			}
		}
		if (ret != 1) {
			if (draw[b] > 0 && draw[a] > 0) {
				draw[b] -= 1;
				draw[a] -= 1;

				if (b == 5)
					ret = dfs(a + 1, a + 2);
				else
					ret = dfs(a, b + 1);
				draw[b] += 1;
				draw[a] += 1;
			}
		}

		return ret;
	}

}

