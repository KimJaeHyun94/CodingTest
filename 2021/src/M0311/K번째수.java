package M0311;

import java.util.Arrays;

public class K번째수 {

	public static void main(String[] args) {
		System.out.println(Arrays.toString(
				solution(new int[] { 1, 5, 2, 6, 3, 7, 4 }, new int[][] { { 2, 5, 3 }, { 4, 4, 1 }, { 1, 7, 3 } })));
	}

	public static int[] solution(int[] array, int[][] commands) {
		int[] answer = {};
		answer = new int[commands.length];
		for (int i = 0; i < commands.length; i++) {
			int s = commands[i][0];
			int e = commands[i][1];
			int d = commands[i][2];
			int len = e - s + 1;
			int arr[] = new int[len];
			int j = 0;
			for (int k = s-1; k < e; k++) {
				arr[j++] = array[k];
			}
			Arrays.sort(arr);
			answer[i] = arr[d - 1];
		}
		return answer;
	}
}
