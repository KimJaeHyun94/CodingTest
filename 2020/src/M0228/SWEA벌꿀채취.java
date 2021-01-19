package M0228;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA벌꿀채취 {
	static int N;
	static int M;
	static int C;
	static int[][] map;
	static int result;
	static int max;
	static boolean[] status;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());

		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			map = new int[N][N];
			status = new boolean[M];
			result = 0;
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			dfs(0, 0, 0, 0);
			System.out.println("#"+tc+" "+result);
		}
	}

	private static void dfs(int r, int i, int j, int value) {
		if (r == 2) {
			result = Math.max(result, value);
			return;
		}
		for (int k = 0; k < N; k++) {
			if (k < i)
				continue;
			for (int c = 0; c < N; c++) {
				if (k == i && c < j)
					continue;
				if (c + (M - 1) < N) {
					max=Integer.MIN_VALUE;
					subset(k,c,0);
					dfs(r + 1, k, c + M, value + max);
				}
			}
		}
	}
	
	   private static void subset(int i, int j, int r) {
		      if(r==M) {
		         int sum=0; int value=0;
		         for (int k = 0; k < M; k++) {
		        	 if(!status[k]) {
		        		 sum+=map[i][j+k];
		        		 value+=map[i][j+k]*map[i][j+k];
		        	 }
		         }
		         if(sum<=C) {
		            max=Math.max(max, value);
		         }
		      }
		      else {
		               status[r]=true;
		               subset(i,j,r+1);
		               status[r]=false;
		               subset(i,j,r+1);
		         }
		   }
}
