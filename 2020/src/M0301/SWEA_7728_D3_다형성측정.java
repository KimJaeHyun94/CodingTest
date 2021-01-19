package M0301;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_7728_D3_다형성측정 {

	static int arr[];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int T=Integer.parseInt(st.nextToken());
		for (int i = 1; i <= T; i++) {
			arr=new int[10];
			String str=br.readLine();
			for (int j = 0; j < str.length(); j++) {
				arr[str.charAt(j)-'0']++;
			}
			int cnt=0;
			for (int j = 0; j < arr.length; j++) {
				if(arr[j]!=0)
					cnt++;
			}
			System.out.println("#"+i+" "+cnt);
		}
	}

}
