package M0403;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_D4_다항식계산 {
	static int N;
	static int M;
	static int fx[][];
	static int X[];
	static long sol[][];  
	static final int MOD = 998244353;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			fx = new int[N + 1][3];
			
			for (int i = 2; i <= N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < 3; j++) {
					fx[i][j]=Integer.parseInt(st.nextToken());
				}
			}
			M = Integer.parseInt(br.readLine());
			X = new int[M + 1];
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= M; i++) {
				X[i] = Integer.parseInt(st.nextToken());
			}
			sol = new long[N+1][M+1];
			for (int i = 1; i <= M; i++) {
				sol[0][i]=1;
				sol[1][i]=(long)X[i];
			}
			for (int i = 2; i <= N; i++) {
				int ti = fx[i][0];
				int ai = fx[i][1];
				int bi = fx[i][2];
				
				if(ti==1) {
					for (int j = 1; j <= M; j++) {
						sol[i][j] = (sol[ai][j]+sol[bi][j])%MOD;
					}
				}
				else if(ti==2) {
					for (int j = 1; j <= M; j++) {
						sol[i][j]=(ai*sol[bi][j])%MOD;
					}
				}
				else {
					for (int j = 1; j <= M; j++) {
						sol[i][j]=(sol[ai][j]*sol[bi][j])%MOD;
					}
				}
			}
			System.out.print("#"+tc+" ");
			for (int i = 1; i <=M; i++) {
				System.out.print(sol[N][i]+" ");
			}
			System.out.println();
 		}
	}
}


