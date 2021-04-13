package M0413;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class 소수찾기 {
	static int MAX = 1000;
	static boolean prime[];
	static ArrayList<Integer> list;
	static int N;
	public static void main(String[] args) throws NumberFormatException, IOException {
		prime = new boolean[MAX+1];
		list = new ArrayList<>();
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
		N = Integer.parseInt(br.readLine());
		int cnt = 0;
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			if(!prime[Integer.parseInt(st.nextToken())]) {
				cnt++;
			}
		}
		
		System.out.println(cnt);
	}
}
