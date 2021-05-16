package M0507;

import java.util.Deque;
import java.util.LinkedList;

public class 징검다리건너기이분탐색 {
	public int solution(int[] stones, int k) {
		Deque<Integer> dq = new LinkedList<>();
		int dq_max = 0;

		for (int i = 0; i < k; i++) {
			dq.add(stones[i]);
			dq_max = Math.max(dq_max, stones[i]);
		}
		
		int ans = dq_max;	//가장 큰 넘
		
		for (int i = k; i < stones.length; i++) {	
			int first = dq.poll();
			
			dq.add(stones[i]);
			if(first==dq_max) {
				dq_max = 0;
				for (int value : dq) {
					dq_max = Math.max(dq_max, value);
				}
			}else {
				if(dq_max<dq.peekLast()) {
					dq_max = dq.peekLast();
				}
			}
			ans = Math.min(ans, dq_max);
		}
	
		return ans;

	}
}
