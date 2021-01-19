package M0311;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ14499주사위굴리기 {
	static int N, M, R, C, K;
	static int map[][];
	static int dice[] = new int[7];
	static int dirs[][] = { { 0, 1 }, { 0, -1 }, { -1, 0 }, { 1, 0 } };
	static int row;
	static int col;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		st = new StringTokenizer(br.readLine());
		row=R;
		col=C;
		for (int i = 0; i < K; i++) {
			int dir = Integer.parseInt(st.nextToken());
			Solution(dir);
		}
	}

	private static void Solution(int dir) {
		int nrow=row+dirs[dir-1][0];
		int ncol=col+dirs[dir-1][1];
		if(isOK(nrow,ncol)) {
			
			int[] temp=Arrays.copyOf(dice, dice.length);
			if(dir==1) {
				dice[1]=temp[4];
				dice[3]=temp[1];
				dice[4]=temp[6];
				dice[6]=temp[3];
				
			}
			else if(dir==2) {
				dice[1]=temp[3];
				dice[3]=temp[6];
				dice[4]=temp[1];
				dice[6]=temp[4];
				
			}
			else if(dir==3) {
				dice[1]=temp[5];
				dice[2]=temp[1];
				dice[5]=temp[6];
				dice[6]=temp[2];
			}
			else {
				dice[1]=temp[2];
				dice[2]=temp[6];
				dice[5]=temp[1];
				dice[6]=temp[5];
			}
			if(map[nrow][ncol]==0) {
				map[nrow][ncol]=dice[6];
				
			}else
			{
				dice[6]=map[nrow][ncol];
				map[nrow][ncol]=0;
			}
			System.out.println(dice[1]);
			row=nrow;
			col=ncol;
		}
		
	}

	private static boolean isOK(int nrow, int ncol) {
		return nrow >= 0 && nrow < N && ncol >= 0 && ncol < M;
	}
}
