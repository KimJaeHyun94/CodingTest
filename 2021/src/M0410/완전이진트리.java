package M0410;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 완전이진트리 {
	static int K;
	static int order[], ans[];
	static int count = 0;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		K = Integer.parseInt(br.readLine());
		int n = (1<<K)-1;
		order = new int[n+1];
		ans = new int[n+1];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			order[i] = Integer.parseInt(st.nextToken());
		}
		
		DFS(1,1);
		
		int idx =1;
		for (int i = 0; i < K; i++) {
			for (int j = 0; j < 1<<i; j++) {
				System.out.print(ans[idx++]+" ");
			}
			System.out.println();
		}
	
	}
	private static void DFS(int idx, int depth) {
		if(depth==K) {
			ans[idx] = order[count++];
			return;
		}
		
		DFS(idx*2, depth+1);
		ans[idx] = order[count++];
		DFS(idx*2+1, depth+1);
	}



}
