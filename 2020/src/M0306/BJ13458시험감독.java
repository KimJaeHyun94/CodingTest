package M0306;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ13458시험감독 {
	static int arr[];
	public static void main(String[] args) throws IOException {
		 BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		 StringTokenizer st=new StringTokenizer(br.readLine());
		 int N=Integer.parseInt(st.nextToken());
		 st=new StringTokenizer(br.readLine());
		 arr=new int[N];
		 for (int i = 0; i <N; i++) {
			arr[i]=Integer.parseInt(st.nextToken());
		}
		 st=new StringTokenizer(br.readLine());
		 int B=Integer.parseInt(st.nextToken());
		 int C=Integer.parseInt(st.nextToken());
		 
		 long cnt=0;
		 
		 for (int i = 0; i < N; i++) {
			arr[i]-=B;
			cnt++;
			
			if(arr[i]>0) {
				if(arr[i]%C==0) {
					cnt+=arr[i]/C;
				}
				else {
					cnt+=arr[i]/C+1;
				}
			}
		}
		System.out.println(cnt);
	}

}
