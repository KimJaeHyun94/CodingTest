package M1221;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 랜선자르기 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int K = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		int[] arr = new int[K];
		
		for (int i = 0; i < arr.length; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		long max = Long.MIN_VALUE;
		long left = 0;
		long right =Long.MAX_VALUE;
		while(left<=right) {
			long cnt = 0;
			long mid = (left+right)/2;
			
			for (int i = 0; i < arr.length; i++) {
				cnt+=arr[i]/mid;
			}
			if(cnt>=N) {
				left = mid+1;
				max = Math.max(max, mid);
			}
			else {
				right = mid-1;
			}
			
		}
		
		System.out.println(max);
		
		
	}

}