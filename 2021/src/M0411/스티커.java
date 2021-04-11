package M0411;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 스티커 {
	static int map[][];
	static int memo[][];
	static int N;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T= Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		for (int tc = 0; tc < T; tc++) {
			N = Integer.parseInt(br.readLine());
			
			map = new int[2][N+1];
			memo = new int[2][N+1];
			
			for (int i = 0; i < 2; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 1; j <= N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			memo[0][1] = map[0][1];
			memo[1][1] = map[1][1];
			
			for (int i = 2; i <= N; i++) {
				memo[0][i] = Math.max(memo[1][i-1], memo[1][i-2])+map[0][i];
				memo[1][i] = Math.max(memo[0][i-1], memo[0][i-2])+map[1][i];
			}
			sb.append(Math.max(memo[0][N], memo[1][N])).append("\n");
		}
	System.out.println(sb);
	}
}
