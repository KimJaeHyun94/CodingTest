package M0423;

import java.util.Scanner;

public class BJ2631줄세우기 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int[] a = new int[N];
		for (int i = 0; i < N; i++) {
			a[i] = sc.nextInt();
		}
		int[] LIS = new int[a.length];
		LIS[0] = 0;
		for (int i = 0; i < LIS.length; i++) {
			LIS[i] = 1; // 초기값(1개짜리 수열)
			for (int j = 0; j <i; j++) { // 내 앞의 숫자 중에 나보다 낮은 숫자를 찾기
				if (a[j] < a[i] && LIS[i] < LIS[j] + 1) {
					LIS[i] = LIS[j] + 1;
				}
			}
		}
		int maxLISIndex = 0;
		for (int i = 1; i < N; i++) {
			if (maxLISIndex < LIS[i]) {
				maxLISIndex = LIS[i];
			}
		}
		System.out.println( N - maxLISIndex);
	}
}
