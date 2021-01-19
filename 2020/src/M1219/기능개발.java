package M1219;

import java.util.LinkedList;
import java.util.Queue;

public class 기능개발 {

	public static void main(String[] args) {
		solution(new int[] {95, 90, 99, 99, 80, 99}, new int[] {1, 1, 1, 1, 1, 1});
	}

	public static int[] solution(int[] progresses, int[] speeds) {
		int[] answer = {};
		int n = speeds.length;
		int[] time = new int[n];

		for (int i = 0; i < n; i++) {
			if ( (100 - progresses[i]) % speeds[i] == 0) {
				time[i] = (100 - progresses[i]) / speeds[i];
			} else {
				time[i] = (100 - progresses[i]) / speeds[i] + 1;
			}
		}
		
		//System.out.println(Arrays.toString(time));
		Queue<Integer> q = new LinkedList<>();
		
		int idx = 1;
		int cnt = time[0];
		for (int i = 1; i < n; i++) {
			if(cnt>=time[i]) {
				idx++;
			}
			else {
				q.add(idx);
				cnt = time[i];
				idx=1;
			}
		}
		q.add(idx);
		int i =0;
		answer = new int[q.size()];
		for (Integer integer : q) {
			answer[i++] = integer;
		}
		
		return answer;
	}
}
