package M0228;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_D4_구간합 {
	static long[] ans=new long[10];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
		
		for (int tc = 1; tc <= T; tc++) {
			ans=new long[10];
			st = new StringTokenizer(br.readLine());
			long A=Long.parseLong(st.nextToken());
			long B=Long.parseLong(st.nextToken());
			long sum=0;
			if(A==0)
				A+=1;
			go(A,B,1);
			for (int i = 0; i < 10; i++) {
				sum+=i*ans[i];
			}
			System.out.println("#"+tc+" "+sum);
		}
	}
	private static void go(long start, long end, long ten) {
		while(start%10!=0 && start<=end) {
			calc(start,ten);
			start+=1;
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
			ans[(int) (n%10)]+=ten;
			n=n/10;
		}
	}
}
