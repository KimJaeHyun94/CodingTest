package M0401;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class 회장뽑기 {
	static int N;
	static int arr[][];
	static int INF = 987654321;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new int[N + 1][N + 1];

		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				if (i == j)
					continue;
				arr[i][j] = INF;
			}
		}
	
		while (true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			if(a==-1 && b==-1) break;
			arr[a][b] = 1;
			arr[b][a] = 1;
			
		}
		FloydWarshall();
		int leader = INF;
		ArrayList<Integer> list = new ArrayList<>();
		for (int i = 1; i <= N; i++) {
			int friend=0;
			for (int j = 1; j <= N; j++) {
				friend = Math.max(friend, arr[i][j]);
			}
			if(leader>friend) {
				leader = friend;
				list = new ArrayList<>();
				list.add(i);
			}else if(leader==friend) {
				list.add(i);
			}
		}
		
		
		
		
		System.out.println(leader+" "+list.size());
		for (Integer candidate : list) {
			System.out.print(candidate+" ");
		}
		System.out.println();
		
	}
	
	private static void FloydWarshall() {
		for (int k = 1; k <= N; k++) {
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					arr[i][j] = Math.min(arr[i][j], arr[i][k] + arr[k][j]);
				}
			}
		}
	}
}
