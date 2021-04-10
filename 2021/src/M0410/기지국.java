package M0410;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;
/**
 * 
 * @author 김재현
 * @See https://akim9905.tistory.com/60
 */
public class 기지국 {
	static int N;
	static int dp[];
	static int INF = 1000000;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		Node arr[] = new Node[N + 1];

		for (int i = 1; i <= N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			arr[i] = new Node(x, Math.abs(y));
		}
		dp = new int[N + 1];
		Arrays.fill(dp, INF);
		Arrays.sort(arr, 1, N+1);
		dp[0] = 0;

		for (int i = 1; i <= N; i++) {
			int check = 0;
			for (int j = i; j >= 1; j--) {
				check = Math.max(check, arr[j].y);
				int square = Math.max(arr[i].x - arr[j].x, check * 2);
				dp[i] = Math.min(dp[i], dp[j - 1] + square);
			}
		}

		System.out.println(dp[N]);

	}

	static class Node implements Comparable<Node> {
		int x;
		int y;

		public Node(int x, int y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public int compareTo(Node o) {
			if (this.x == o.x)
				return this.y - o.y;
			return this.x - o.x;

		}
	}

}