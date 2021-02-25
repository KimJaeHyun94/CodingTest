package M0225;

import java.util.Collections;

import java.util.Comparator;
import java.util.LinkedList;

public class 무지의먹방라이즈sublist {
	public static void main(String[] args) {
		System.out.println(solution(new int[] { 4, 2, 3, 6, 7, 1, 5, 8 }, 16));
		System.out.println(solution(new int[] { 3, 1, 2 }, 5));
	}

	public static int solution(int[] food_times, long k) {
		int answer = 0;
		LinkedList<Food> foodlist = new LinkedList<>();
		for (int i = 0; i < food_times.length; i++) {
			foodlist.add(new Food(i + 1, food_times[i]));
		}
		Collections.sort(foodlist, new Comparator<Food>() { // 시간 순서대로 정렬해놓는다.
			@Override
			public int compare(Food o1, Food o2) {
				return o1.time - o2.time;
			}
		});
		long diff = 0; // 전까지 기억해냐아 할 길이
		long remove = 0; // 지워야 할 길이
		long n = foodlist.size();
		boolean flag = false;
		int idx = 0;
		for (Food food : foodlist) {
			remove = food.time - diff;
			if (remove != 0) {
				remove = remove * n;
				if (remove > k) {
					k %= n;
					flag = true;
					foodlist.subList(idx, food_times.length).sort(new Comparator<Food>() {
						@Override
						public int compare(Food o1, Food o2) {
							return o1.idx - o2.idx;
						}
					});
					return foodlist.get((int) k + idx).idx;
				} else {
					k -= remove;
					diff = food.time;
				}
			}
			idx++;
			n--;
		}
		if (!flag)
			return -1;
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
