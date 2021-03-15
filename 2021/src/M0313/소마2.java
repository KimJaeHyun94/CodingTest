package M0313;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 소마2 {
	static int s;
	static int MAX = Integer.MIN_VALUE;
	static int Route[];
	static int N;
	static int start=0;
	static int end=-1;
	public static void main(String args[]) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		Route = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			Route[i] = Integer.parseInt(st.nextToken());
		}
		
			for(int i=0; i<N-1; i++) {
				start= i;
				DFS(i, i+Route[i], 1);
			}
			
		

		System.out.println(MAX);
		

	}

	private static void DFS(int c, int r, int cnt) {
		if(cnt>N) {
			return;
		}
		if(r==start) {
			MAX = Math.max(MAX, cnt);
			return;
		}
		DFS(r, r+Route[r], cnt+1);
		
		
		
	}

}
