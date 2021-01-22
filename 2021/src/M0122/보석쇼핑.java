package M0122;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class 보석쇼핑 {
	public int[] solution(String[] gems) {
		
		HashSet<String> set = new HashSet<>();
		for (int i = 0; i < gems.length; i++) {
			set.add(gems[i]);
		}
		HashMap<String, Integer> map = new HashMap<>();
		Queue<String> q = new LinkedList<>();
		int start = 0, end=gems.length, startp=0;
		for (int i = 0; i < gems.length; i++) {
			if(map.containsKey(gems[i])) {
				map.put(gems[i], map.get(gems[i])+1);
			}
			else {
				map.put(gems[i], 1);
			}
			
			q.add(gems[i]);
			while(true) {
				String check = q.peek();
				if(map.get(check)>1) {
					map.put(check, map.get(check)-1);	//1개 줄임
					q.poll();	//삭제
					start++;	//한 칸 앞으로 전진
				}
				else {
					break;
				}
			}
			
			if(map.size()==set.size() && end>q.size()) {
				end = q.size();
				startp = start;
			}
		}
		int[] answer = new int[] {startp+1, startp+end};
		
		return answer;
	}

}
