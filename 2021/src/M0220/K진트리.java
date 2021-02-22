package M0220;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/**
 * 
 * @author 김재현
 * @see https://manzoo.tistory.com/88
 */
public class K진트리 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		long N = Long.parseLong(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int Q = Integer.parseInt(st.nextToken());
		StringBuilder sb = new StringBuilder();
		
		for (int i = 0; i < Q; i++) {
			 st = new StringTokenizer(br.readLine());
			 long x = Long.parseLong(st.nextToken());
	         long y = Long.parseLong(st.nextToken());
	         
	         long cnt = 0;
	         if(K==1) {
	        	 cnt = Math.abs(x-y);
	         }
	         else {
	        	 while(x!=y) {
	        		 if(x<y) {
	        			 y=(y-2)/K+1;
	        		 }
	        		 else {
	        			 x=(x-2)/K+1;
	        		 }
	        		 cnt++;
	        	 }
	         }
	         sb.append(cnt).append("\n");
		}
		System.out.println(sb);
	}
}
