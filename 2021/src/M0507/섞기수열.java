package M0507;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 섞기수열 {
	static int N, arr[];
	static boolean same[];

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new int[N + 1];
		same = new boolean[N + 1];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		int ans = 1;
		for (int i = 1; i <= N; i++) {
			if(same[i]) continue;
			ans = LCM(ans, check(i));
		}
		
		System.out.println(ans);
	}

	private static int check(int s) {
		int cnt = 1;
		int next = arr[s];
		
		while(true) {
			if(s==next) {
				return cnt;
			}
			same[next] = true;
			next = arr[next];
			cnt++;
		}
	}

	static int GCD(int B, int A) { // 최대 공약수
		int C;
		while (A != 0) {
			C = B % A;
			B = A;
			A = C;
		}
		return B;
	}

	static int LCM(int A, int B) { // 최소 공배수
		return A / GCD(A, B) * B;
	}

}
