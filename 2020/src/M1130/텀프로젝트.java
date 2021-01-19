package M1130;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 텀프로젝트 {
	static int T, count;
	static boolean visited[];
	static boolean finished[];
	static int arr[];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int tc = 0; tc < T; tc++) {
			int N = Integer.parseInt(br.readLine());
			arr = new int[N+1];
			visited = new boolean[N+1];
			finished = new boolean[N+1];
			count = 0;
			
 			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 1; i < N+1; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			
			for (int i = 1; i < N+1; i++) {
				dfs(i);
			}
			sb.append(N-count+"\n");
		}
		System.out.println(sb);
	}

	private static void dfs(int r) {
		if(visited[r])
			return;
		
		visited[r] = true;
		
		if(!visited[arr[r]]) {
			dfs(arr[r]);
		}
		else {
			if(!finished[arr[r]]) {
				count++;
				for (int i = arr[r]; i !=r; i=arr[i]) {
					count++;
				}
			}
		}
		finished[r] = true;
	}
}
