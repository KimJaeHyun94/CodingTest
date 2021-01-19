package M0229;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA수영장 {
	static int day,mon,three,year;
	static int map[];
	static int cost[];
	static int min;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
		for (int tc = 1; tc <= T; tc++) {
			min=Integer.MAX_VALUE;
			st = new StringTokenizer(br.readLine());
			cost=new int[4];
			for (int i = 0; i < cost.length; i++) {
				cost[i]=Integer.parseInt(st.nextToken());
			}
			map=new int[13];
			st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= 12; i++) {
				map[i]=Integer.parseInt(st.nextToken());
			}
			DFS(1,0);
			System.out.println("#"+tc+" "+min);
		}
	}
	private static void DFS(int month, int money) {
		if(month>=13) {
			min=Math.min(min, money);
			return;
		}else {
			if(map[month]!=0) {
				DFS(month+1, money+cost[0]*map[month]);
				DFS(month+1, money+cost[1]);
				DFS(month+3, money+cost[2]);
				DFS(month+12, money+cost[3]);
			}
			else {
				DFS(month+1,money);
			}
		}
	}
}
