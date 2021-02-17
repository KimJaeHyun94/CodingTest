package M0217;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
/**
 * 
 * @author 김재현
 * @see https://tech.kakao.com/2018/09/21/kakao-blind-recruitment-for2019-round-1/, sublist
 */
public class 무지의먹방라이브_효율성 {
	public static void main(String[] args) {
		System.out.println(solution(new int[] { 4, 2, 3, 6, 7, 1, 5, 8 }, 16));
		System.out.println(solution(new int[] { 3, 1, 2 }, 5));
	}

	public static int solution(int[] food_times, long k) {
		int answer = 0;
		List<Food> foodlist = new LinkedList<>();
		for (int i = 0; i < food_times.length; i++) {
			foodlist.add(new Food(i + 1, food_times[i]));
		}

		Collections.sort(foodlist, new Comparator<Food>() {
			@Override
			public int compare(Food o1, Food o2) {
				return o1.time - o2.time;
			}
		});

		int idx = 0;
		long diff = 0; // 지울 수 있는 시간
		long n = food_times.length;
		boolean flag = false;
		long remove;

		for (Food food : foodlist) {
			remove = food.time - diff;
			if (remove != 0) {
				remove *= n;
				if (remove > k) {
					flag = true;
					k %= n;
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