package M0324;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 랭퍼든수열쟁이야 {
	static int n, x, y;
	static int arr[];
	static boolean visited[];
	static int ans = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		x = Integer.parseInt(st.nextToken());
		y = Integer.parseInt(st.nextToken());

		arr = new int[2 * n + 1];
		visited = new boolean[n + 1];
		arr[x] = y - x - 1;
		arr[y] = y - x - 1;
		visited[y - x - 1] = true;

		DFS(1);
		System.out.println(ans);
	}

	private static void DFS(int cnt) {
		if (cnt == 2 * n) {
			ans++;
			return;
		}

		if (arr[cnt] == 0) {
			for (int i = 1; i <= n; i++) {
				if (!visited[i]) {
					if (cnt + i + 1 <= 2 * n && arr[i + cnt + 1] == 0) {
						arr[cnt] = i;
						arr[cnt + i + 1] = i;
						visited[i] = true;

						DFS(cnt + 1);

						arr[cnt] = 0;
						arr[cnt + i + 1] = 0;
						visited[i] = false;
					}
				}
			}
		}else
			DFS(cnt+1);
	}

}
