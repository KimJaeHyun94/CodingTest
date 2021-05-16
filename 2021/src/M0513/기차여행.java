package M0513;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * @author 김재현
 * @see https://burningjeong.tistory.com/326
 */
public class 기차여행 {

	static int N, M;
	static int passport[];
	static int a, b, c;
	static long sum[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		passport = new int[M];
		sum = new long[N + 5];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < M; i++) {
			passport[i] = Integer.parseInt(st.nextToken());
		}

		for (int i = 0; i < M - 1; i++) {
			if (passport[i] < passport[i + 1]) {
				sum[passport[i]]++;
				sum[passport[i + 1]]--;
			} else {
				sum[passport[i]]--;
				sum[passport[i + 1]]++;
			}
		}

		for (int i = 1; i <= N; i++) {
			sum[i] = sum[i] + sum[i - 1];
		}

		long result = 0;
		for (int i = 1; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());

			if (sum[i] != 0) {
				result += Math.min(a * sum[i], b * sum[i] + c);
			}

		}

		System.out.println(result);

	}

}
