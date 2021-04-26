package M0426;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class 두배열의합 {
	static long T;
	static int N, M;
	static int A[], B[];
	static ArrayList<Long> partA, partB;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Long.parseLong(br.readLine());
		N = Integer.parseInt(br.readLine());
		A = new int[N];

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}

		partA = new ArrayList<>(); // 집합 A의 모든 부분 덧셈 집합

		for (int i = 0; i <= N - 1; i++) {
			long sum = A[i];
			partA.add(sum);
			for (int j = i + 1; j < N; j++) {
				sum += A[j];
				partA.add(sum);
			}
		}
		M = Integer.parseInt(br.readLine());
		B = new int[M];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < M; i++) {
			B[i] = Integer.parseInt(st.nextToken());
		}

		partB = new ArrayList<>(); // 집합 A의 모든 부분 덧셈 집합

		for (int i = 0; i <= M - 1; i++) {
			long sum = B[i];
			partB.add(sum);
			for (int j = i + 1; j < M; j++) {
				sum += B[j];
				partB.add(sum);
			}
		}
		Collections.sort(partA);
		Collections.sort(partB);

		long cnt = 0;
		for (Long child : partA) { // A에서 하나씩 빼서 맞는 B를 구한다.
			long target = T - child;
			cnt += upper_bound(target)-lower_bound(target);
		}
		System.out.println(cnt);
	}

	private static int upper_bound(long target) {
		int left = 0;
		int right = partB.size();

		while (left < right) {
			int mid = (left + right) >> 1;

			if (partB.get(mid) <= target)
				left = mid + 1;
			else
				right = mid;
		}
		return right;
	}

	private static int lower_bound(long target) {
		int left = 0;
		int right = partB.size();

		while (left < right) {
			int mid = (left + right) >> 1;

			if (partB.get(mid) < target)
				left = mid + 1;
			else
				right = mid;
		}
		return right;
	}
}
