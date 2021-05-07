package 두포인터;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class ListofUniqueNumbers2 {
	static int N;
	static int arr[];
	static int visited[];

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		arr = new int[N+1];
		visited = new int[100001];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		int left = 1;
		int right = 0;
		long result = 0;
		
		while(left<=N) {
			while(right+1<=N && visited[arr[right+1]]==0) {
				right++;
				visited[arr[right]]++;
			}
			result+=right-left+1;
			visited[arr[left++]]--;
		}
		
		
		
		System.out.println(result);
	}
}
