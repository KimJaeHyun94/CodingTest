package M0228;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ1019책페이지 {
	static long[] ans=new long[10];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		long N=Long.parseLong(st.nextToken());
		go(1,N,1);
		for (int i = 0; i < 10; i++) {
			System.out.print(ans[i]+" ");
		}
	}
	private static void go(long start, long end, long ten) {
		while(start%10!=0 && start<=end) {
			calc(start,ten);
			start++;
		}
		if(start>end)return;
		while(end%10!=9 && start<=end) {
			calc(end,ten);
			end-=1;
		}
		long cnt=(end/10-start/10+1);
		for (int i = 0; i <= 9; i++) {
			ans[i]+=cnt*ten;
		}
		go(start/10, end/10, ten*10l);
	}
	private static void calc(long n, long ten) {
		while(n>0) {
			ans[(int)(n%10)]+=ten;
			n/=10;
		}
	}
}
