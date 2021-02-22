package M0220;

import java.util.LinkedList;
import java.util.List;

public class 캐시 {

	public static void main(String[] args) {
		System.out.println(solution(3, new String[] { "Jeju", "Pangyo", "Seoul", "NewYork", "LA", "Jeju", "Pangyo",
				"Seoul", "NewYork", "LA" }));
		System.out.println(solution(3, new String[] {"Jeju", "Pangyo", "Seoul", "Jeju", "Pangyo", "Seoul", "Jeju", "Pangyo", "Seoul" }));
		System.out.println(solution(2, new String[] {"Jeju", "Pangyo", "NewYork", "newyork" }));
		
	}

	public static int solution(int cacheSize, String[] cities) {
		int answer = 0;
		List<String> list = new LinkedList<>();
		if (cacheSize == 0)   //캐시 사이즈에 담을 수 없기 때문에 
			return cities.length * 5;   //아무것도 히트시킬 수 없다 

		for (String st : cities) {
			if (list.contains(st.toUpperCase())) {    //포함하면 히트시키고 기존꺼 삭제 + 새롭게 추가 (대소문자 구분 X)
				answer += 1;
				list.remove(st.toUpperCase());
				list.add(st.toUpperCase());
			} else {
				list.add(st.toUpperCase());
			}

			if (list.size() > cacheSize) {
				answer += 5;
				list.remove(0);
			}
		}
		if (list.size() != 0) {
			for (int i = 0; i < list.size(); i++) {
				answer += 5;
			}

		}
		return answer;
	}
}
