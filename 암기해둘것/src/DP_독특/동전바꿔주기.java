package DP_독특;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 동전바꿔주기 {
	static int dp[][];
	static Coin coin[];
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int bill = Integer.parseInt(br.readLine());
		int k = Integer.parseInt(br.readLine());
		coin= new Coin[k+1];

		for (int i = 1; i <= k; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int p = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			coin[i] = new Coin(p, c);
		}

		dp = new int[k + 1][bill + 1];
		dp[0][0] = 1;
		for (int i = 1; i <= k; i++) {
			Coin cur = coin[i];
			int p = cur.p;
			int c = cur.c;
			for (int j = 0; j <= c; j++) {
				for (int b = 0; b <= bill; b++) {
					if(p*j+b>bill) break;
					dp[i][b+p*j]+=dp[i-1][b];
				}
			}
		}
		System.out.println(dp[k][bill]);

	}

	static class Coin {
		int p;
		int c;

		public Coin(int p, int c) {
			this.p = p;
			this.c = c;
		}
	}
}
