package M0408;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class 두배열의합 {
	static long T;
	static int N, M;
	static int A[], B[];
	static ArrayList<Long> sumA, sumB;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Long.parseLong(br.readLine());
		N = Integer.parseInt(br.readLine());
		A = new int[N];
		sumA = new ArrayList<>();
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}
		M = Integer.parseInt(br.readLine());
		B = new int[M];
		sumB = new ArrayList<>();
		
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < M; i++) {
			B[i] = Integer.parseInt(st.nextToken());
		}
		
		for (int i = 0; i < N; i++) {
			long sum = 0;
			for (int j = i; j < N; j++) {
				sum += A[j];
				sumA.add(sum);
			}
		}

		for (int i = 0; i < M; i++) {
			long sum = 0;
			for (int j = i; j < M; j++) {
				sum += B[j];
				sumB.add(sum);
			}
		}

		Collections.sort(sumA);
		Collections.sort(sumB);

		long cnt = 0;
		for (int i = 0; i < sumA.size(); i++) {
			long target = T - sumA.get(i);
			cnt += upper_bound(0, target) - lower_bound(0, target);
		}
		System.out.println(cnt);
	}

	private static int lower_bound(int left, long target) {
		int right = sumB.size();
		while (left < right) {
			int mid = (left + right) >> 1;
			if (sumB.get(mid) < target) {
				left = mid + 1;
			} else {
				right = mid;
			}
		}
		return right;
	}

	private static int upper_bound(int left, long target) {
		int right = sumB.size();
		while (left < right) {
			int mid = (left + right) >> 1;
			if (sumB.get(mid) <= target) {
				left = mid + 1;
			} else {
				right = mid;
			}
		}
		return right;
	}
}
