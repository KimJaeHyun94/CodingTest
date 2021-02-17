package M0217;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;

public class 무지의먹방라이브_최종 {
	public static void main(String[] args) {
		System.out.println(solution(new int[] { 4, 2, 3, 6, 7, 1, 5, 8 }, 16));
		System.out.println(solution(new int[] { 3, 1, 2 }, 5));
	}

	public static int solution(int[] food_times, long k) {
		int answer = 0;
		long sum = 0;
		LinkedList<Food> foodlist = new LinkedList<>();
		for (int i = 0; i < food_times.length; i++) {
			foodlist.add(new Food(i + 1, food_times[i]));

			sum += food_times[i];

		}
		if (sum <= k)
			return -1;
		Collections.sort(foodlist, new Comparator<Food>() {
			@Override
			public int compare(Food o1, Food o2) {
				return o1.time - o2.time;
			}
		});

		long diff = 0; // 지울 수 있는 시간
		long n = food_times.length;
		long remove = 0;

		while (true) {
			remove = (foodlist.get(0).time - diff) * n;
			if (k < remove) {
				break;
			}
			k -= remove;
			diff = foodlist.get(0).time;
			foodlist.remove(0);
			n--;
		}
		Collections.sort(foodlist, new Comparator<Food>() {
			@Override
			public int compare(Food o1, Food o2) {
				return o1.idx - o2.idx;
			}
		});
		k %= n;
		answer = foodlist.get((int) k).idx;
		return answer;
	}

	static class Food {
		int idx;
		int time;

		public Food(int idx, int time) {
			this.idx = idx;
			this.time = time;
		}
	}
}