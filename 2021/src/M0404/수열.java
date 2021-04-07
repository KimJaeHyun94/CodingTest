package M0404;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 수열 {
	static int N, K;
	static int arr[];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		arr = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		int max = Integer.MIN_VALUE;
		for (int i = 0; i <= N-K; i++) {
			int sum = 0;
			for (int j = 0; j < K; j++) {
				sum+=arr[i+j];
			}
			max = Math.max(max, sum);
		}
		System.out.println(max);
	}
}
