package M0303;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/**
 * 
 * @author 김재현
 * @See : https://onsil-thegreenhouse.github.io/programming/problem/2018/03/29/problem_math_power/
 * 
 */
public class 곱셈 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		long A = Long.parseLong(st.nextToken());
		long B = Long.parseLong(st.nextToken());
		long C = Long.parseLong(st.nextToken());
		
		System.out.println((calc(A % C, B, C)));
		
	}

	private static long calc(long a, long b, long c) {   // [pow(a,n) = { 1, n=0
														 //				  pow(a,n/2)^2, if n is even		
													     //  			  pow(a,n/2)^2*a if n is odd
		if (b == 1) {
			return a;
		} else {
			long ans = calc(a, b / 2, c) % c;
			if (b % 2 == 0) {
				return (ans * ans) % c;
			} else {
				return ((ans * ans) % c * a) % c;
			}
		}
	}
}
