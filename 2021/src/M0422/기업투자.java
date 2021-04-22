package M0422;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Stack;
import java.util.StringTokenizer;

public class 기업투자 {
	static int N, M;
	static int map[][];
	static int dp[][], path[][];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[M + 1][N + 1];
		dp = new int[M + 1][N + 1];
		path = new int[M + 1][N + 1];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken()); // 투자 금액
			for (int j = 1; j <= M; j++) {
				map[j][n] = Integer.parseInt(st.nextToken());
			}
		}
		
		for (int i = 0; i <= M; i++) {
			Arrays.fill(dp[i], -1);
		}

	/*	for (int i = 1; i <= M; i++) { // 기업			//바텀업
			for (int j = 0; j <= N; j++) { // 금액
				for (int c = 0; c <= j; c++) {
					int val = dp[i - 1][j - c] + map[i][c];
					if (dp[i][j] < val) {
						dp[i][j] = val;
						path[i][j] = c;
					}
				}
			}
		}
*/		
		System.out.println(DFS(M, N));
		Stack<Integer> stack = new Stack<>();
		int r = M;
		int c = N;
		while (r > 0) {
			int val = path[r][c];
			stack.add(val);

			r--;
			c -= val;
		}
		while (!stack.isEmpty()) {
			System.out.print(stack.pop() + " ");
		}

	}
	
	static int DFS(int com, int cos) {		//탑다운
		if(com==0) {
			return 0;
		}
		if(dp[com][cos]!=-1) {
			return dp[com][cos];
		}
		
		for (int i = 0; i <= cos; i++) {
			int val = DFS(com-1, cos-i) + map[com][i];
			if (dp[com][cos] < val) {
				dp[com][cos] = val;
				path[com][cos] = i;
			}
		}
		
		
		return dp[com][cos];
	}

}
