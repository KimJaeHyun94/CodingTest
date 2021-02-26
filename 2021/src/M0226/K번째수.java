package M0226;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class K번째수 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int K = Integer.parseInt(br.readLine());
		
		long left = 0;
		long right = K;
		
		while(left<=right) {
			long mid = (left+right)>>1;
			int cnt = 0;
			
			for (int i =1; i <= N; i++) {
				cnt+=Math.min(mid/i, N);  //mid보다 작거나 같은 수 
			}
			
			if(cnt<K) {
				left = mid+1;
			}else {
				right = mid-1;
			}
			
		}
		System.out.println(right+1);
	}
}
