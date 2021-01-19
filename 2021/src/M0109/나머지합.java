package M0109;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * @author : AKKabiyo
 * @see https://www.crocus.co.kr/854
 */
public class 나머지합 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		int arr[] = new int[N];
		int mod[] = new int[M];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		int sum = 0;
		for (int i = 0; i < N; i++) {
			sum += arr[i];
			sum %= M;
			;
			mod[sum]++;
		}

		long ans = (long) mod[0];
		for (int i = 0; i < mod.length; i++) {
			ans += (long) mod[i] * ((long) mod[i] - 1) / 2;
		}
		System.out.println(ans);
	}

}
