package M1217;

import java.util.LinkedList;
import java.util.Queue;

public class 다리를지나는트럭 {

	public static void main(String[] args) {
		int[] truck_weights =  {7,4,5,6};
		System.out.println(solution(2,10,truck_weights));
	}

	public static int solution(int bridge_length, int weight, int[] truck_weights) {
		int answer = 0;

		Queue<Integer> wait = new LinkedList<>();
		Queue<Truck> bridge = new LinkedList<>();

		for (int i = 0; i < truck_weights.length; i++) {
			wait.add(truck_weights[i]);
		}
		int current = 0; // 현재 다리의 무게
		while (true) {
			if (wait.isEmpty() && bridge.isEmpty()) { // 다 나갔으면 탈출
				break;
			}
			if (!bridge.isEmpty()) {
				if (answer - bridge.peek().time == bridge_length) {
					Truck temp = bridge.poll();
					current -= temp.w;
				}
			}
			
			if (!wait.isEmpty()) {
				if (current + wait.peek() <= weight) {
					int c = wait.poll();
					current += c; // 무게 증가
					bridge.add(new Truck(c, answer));
				}
			}
			answer++; // 시간 증가
		}
		return answer;
	}

	static class Truck {
		int w;
		int time;

		public Truck(int w, int time) {
			this.w = w;
			this.time = time;
		}

	}
}
