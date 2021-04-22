package M0414;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 공약수 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int max = Integer.parseInt(st.nextToken());
		int min = Integer.parseInt(st.nextToken());
		
		min/=max;
		
		int x= 0;
		for (int i = 1; i*i <= min; i++) {
			if(min%i==0 && getGCD(i, min/i)==1) {
				x = i;
			}
		}
		
		int a = max*x;
		int b= max*(min/x);
		
		System.out.println(a+" "+b);
	}

	private static int getGCD(int a, int b) {
		if (b == 0)
			return a;
		return getGCD(b, a % b);
	}
}
