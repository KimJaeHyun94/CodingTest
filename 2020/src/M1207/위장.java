package M1207;


import java.util.HashMap;
import java.util.Map.Entry;


public class 위장 {

	public static void main(String[] args)

	{
		String[][] clothes = { { "yellow_hat", "headgear" }, { "blue_sunglasses", "eyewear" },
				{ "green_turban", "headgear" } };
		solution(clothes);
	}

	public static int solution(String[][] clothes) {
		int answer = 0;
		HashMap<String, Integer> cnt = new HashMap<>();

		for (int i = 0; i < clothes.length; i++) {
			if (cnt.containsKey(clothes[i][1])) {
				cnt.put(clothes[i][1], cnt.get(clothes[i][1]) + 1);
			} else
				cnt.put(clothes[i][1], 1);
		}
		answer = 1;
		int arr[] = new int[cnt.keySet().size()];
		int cnt2 = 0;
		for (Entry<String, Integer> entry : cnt.entrySet()) {
			arr[cnt2] = entry.getValue();
			cnt2 += 1;
		}
		for (int i = 0; i < arr.length; i++) {
			answer*=arr[i]+1;
		}
		answer-=1;
		return answer;
	}
}
