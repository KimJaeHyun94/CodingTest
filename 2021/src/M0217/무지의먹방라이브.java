package M0217;

import java.util.Arrays;

public class 무지의먹방라이브 {
	public static void main(String[] args) {
		System.out.println(solution(new int[] { 4, 2, 3, 6, 7, 1, 5, 8 }, 16));
	}

	public static int solution(int[] food_times, long k) {
		int answer = 0;
		int idx = 0;
		long sum = 0;
		for (int i : food_times) {
			sum += i;
		}
		if (sum <= k)
			return -1;
		while (k > 0) {
			if (food_times[idx] != 0) {
				food_times[idx]--;
				k--;
				idx++;
			} else {
				idx++;

			}
			if (idx == food_times.length) {
				idx = 0;
			}
		}
		while (true) {
			if (idx == food_times.length) {
				idx = 0;
			} else if (food_times[idx] == 0) {
				idx++;
			} else
				break;
		}
		answer = idx + 1;
		return answer;
	}
}
