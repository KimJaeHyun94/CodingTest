package M0108;

import java.util.ArrayList;

public class 하노이의탑 {
	static int idx = 0;
	static ArrayList<int[]> list;
	public static void main(String[] args) {
		solution(2);
	}
	public static int[][] solution(int n) {
		int[][] answer = {};
		list = new ArrayList<>();
		hanoi(n, 1, 2, 3, answer);
		answer = new int[list.size()][2];
		for (int[] arr : list) {
			answer[idx][0] = arr[0];
			answer[idx++][1] = arr[1];
		}
		return answer;
	}

	private static void hanoi(int cnt, int from, int temp, int to, int[][] answer) {
		if (cnt == 0)
			return;
		System.out.println(from+" "+to);
		hanoi(cnt - 1, from, to, temp, answer);
		list.add(new int[] { from, to });
		hanoi(cnt - 1, temp, from, to, answer);
	}
}
