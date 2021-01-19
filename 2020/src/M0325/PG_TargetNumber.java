package M0325;

public class PG_TargetNumber {
	static int answer = 0;

	public static int solution(int[] numbers, int target) {
		DFS(numbers, target, 0, new int[numbers.length]);
		return answer;
	}

	private static void DFS(int[] numbers, int target, int cnt, int[] temp) {
		if (cnt == numbers.length) {
			int sum = 0;
			for (int num : temp) {
				sum += num;
			}
			if (sum == target) {
				answer++;
			}
			return;
		}
		temp[cnt]=numbers[cnt];
		DFS(numbers, target, cnt+1, temp);
		temp[cnt]=numbers[cnt]*-1;
		DFS(numbers, target, cnt+1, temp);
	}
}
