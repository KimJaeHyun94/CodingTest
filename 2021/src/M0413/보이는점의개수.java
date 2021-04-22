package M0413;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 보이는점의개수 {
	static int MAX = 1000;
	static int arr[] = new int[MAX+1];
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		
		for (int i = 0; i <= MAX; i++) {
			for (int j = 0; j <= MAX; j++) {
				if(gcd(i,j)==1) {
					arr[Math.max(i, j)]++;
				}
			}
		}
		for (int i = 1; i <= MAX; i++) {
			arr[i] +=arr[i-1];
		}
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		for (int tc = 0; tc < T; tc++) {
			int N = Integer.parseInt(br.readLine());
			System.out.println(arr[N]);
			
		}
	}
	private static int gcd(int i, int j) {
		if(j==0) return i;
		return gcd(j, i%j);
	}
}
