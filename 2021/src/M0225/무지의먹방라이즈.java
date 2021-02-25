package M0225;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;

public class 무지의먹방라이즈 {
	public static void main(String[] args) {
		System.out.println(solution(new int[] { 4, 2, 3, 6, 7, 1, 5, 8 }, 16));
		System.out.println(solution(new int[] { 3, 1, 2 }, 5));
	}

	public static int solution(int[] food_times, long k) {
		long sum = 0;
		LinkedList<Food> foodlist = new LinkedList<>();
		for (int i = 0; i < food_times.length; i++) {
			foodlist.add(new Food(i + 1, food_times[i]));

			sum += food_times[i];

		}
		if (sum <= k)
			return -1;
		Collections.sort(foodlist, new Comparator<Food>() { // 시간 순서대로 정렬해놓는다.
			@Override
			public int compare(Food o1, Food o2) {
				return o1.time - o2.time;
			}
		});
		long diff = 0; // 전까지 기억해냐아 할 길이
		long remove = 0; // 지워야 할 길이
		long n = foodlist.size();

		while (true) {
			remove = (foodlist.get(0).time - diff) * n;   //지워야 하는 길이는 지금에서부터 그 전까지 지웟던 길이를 뺀 것을 남아있는 것에 곱하면 된다. 
			if (k < remove) {
				break;
			}
			k-=remove;  //앞에서부터 지워나가기 때문에 
			n--;  //list의 사이즈를 하나 줄인다.
			diff = foodlist.get(0).time;   //지금 지웠던 길이를 저장해둔다. 
			foodlist.remove(0);   //리스트 첫번째를 줄인다.
		}
		Collections.sort(foodlist, new Comparator<Food>() { // 시간 순서대로 정렬해놓는다.
			@Override
			public int compare(Food o1, Food o2) {
				return o1.idx - o2.idx;
			}
		});
		
		k%=n;
		
		return foodlist.get((int) k).idx;
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
