package M1208;

import java.util.Arrays;

public class 줄서는방법EN {
	static int answer[], tmp[];
	static long K, R=0;

	public static void main(String[] args) {
		solution(3, 5);
	}

	public static int[] solution(int n, long k) {
		answer = new int[n];
		tmp = new int[n];
		for (int i = 0; i < n; i++) {
			answer[i] = i + 1;
		}
		K = k;
		int arr[] = new int[n];
		boolean visited[] = new boolean[n];
		permutation(n, 0, arr, visited);
		return tmp;
	}

	private static void permutation(int n, int r, int[] temp, boolean[] visited) {
		if(R>K)
			return;
		if (r == n) {
			R++;
			System.out.println(R);
			if(R==K) {
				System.out.println(Arrays.toString(temp));
				for (int i = 0; i < n; i++) {
					tmp[i]= temp[i];
				} 
				return;
			}
		} else {
			for (int i = 0; i < answer.length; i++) {
				if (!visited[i]) {
					visited[i] = true;
					temp[r] = answer[i];
					permutation(n, r + 1, temp, visited);
					visited[i] = false;
				}
			}
		}
	}
}
