package M1201;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.StringTokenizer;

public class 키순서 {

	static int N, M, ans;
	private static boolean map[][];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		ans = 0;
		map = new boolean[N + 1][N + 1];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			map[x][y] = true;
		}
		for (int k = 1; k <= N; k++)
		{
			for (int i = 1; i <= N; i++)
			{
				for (int j = 1; j <= N; j++)
				{
					if (map[i][k] && map[k][j])
						map[i][j] = true;
				}
			}
		}

		int ans = 0;
		for (int i = 1; i <= N; i++)
		{
			int cnt = 0;
			for (int j = 1; j <= N; j++)
			{
				if(i==j) continue;
				if(map[i][j] || map[j][i])
					cnt++;
			}
			if (cnt ==N-1)ans++;
		}
		System.out.println(ans);

	}
}
