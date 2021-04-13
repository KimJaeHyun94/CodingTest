package M0413;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 소수구하기 {
	static int MAX = 1000000;
	
	public static void main(String[] args) throws IOException {
		boolean prime[] = new boolean[MAX+1];
		prime[0] = true;
		prime[1] = true;

		for (int i = 2; i*i <= MAX; i++) {
			if (!prime[i]) {
				for (int j = i * i; j <= MAX; j += i) {
					prime[j] = true;
				}
			}
		}
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		for (int i = N; i <= M; i++) {
			if(!prime[i]) {
				System.out.println(i);
			}
		}
	}
}
