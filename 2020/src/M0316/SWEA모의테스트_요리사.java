package M0316;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA모의테스트_요리사 {
	static int N;
	static int map[][];
	static boolean visited[];
	static int min=Integer.MAX_VALUE;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int T=Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			N=Integer.parseInt(br.readLine());
			map=new int[N][N];
			min=Integer.MAX_VALUE;
			for (int i = 0; i < N; i++) {
				StringTokenizer st= new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j]=Integer.parseInt(st.nextToken());
				}
			}
			visited=new boolean[N];
			permutation(1,0);
			System.out.println("#"+tc+" "+min);
		}
	}
	private static void permutation(int start, int r) {
		if(r==N/2) {
			min=Math.min(check(), min);
			return;
		}
		else {
			for (int i = start; i < N; i++) {
				if(!visited[i]) {
					visited[i]=true;
					permutation(i+1,r+1);
					visited[i]=false;
				}
			}
		}
	}
	private static int check() {
		int start=0;
		int link=0;
		for (int i = 0; i < N; i++) {
			for (int j = i+1; j < N; j++) {
				if(visited[i] && visited[j]) {
					start+=map[i][j]+map[j][i];
				}
				if(!visited[i] && !visited[j]) {
					link+=map[i][j]+map[j][i];
				}
			}
		}
		return Math.abs(start-link);
	}
}
