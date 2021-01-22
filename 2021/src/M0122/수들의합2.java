package M0122;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 수들의합2 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[] arr = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < arr.length; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		int start = 0, end = 0, sum = 0;
		int ans = 0;

		while (true) {
		
			if(sum>=M) {
				if (sum == M) {
					ans++;
				} 
				sum-=arr[start++];
			}
			else if (end == N)
				break;
			else {
				sum+=arr[end++];
			}
		}
		System.out.println(ans);
	}
}
