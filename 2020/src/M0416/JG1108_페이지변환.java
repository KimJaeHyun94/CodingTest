package M0416;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class JG1108_페이지변환 {

	public static int bfs(Queue<Integer> queue, int[][] array, int[] visited, int target, int moveCount) {

		int size = queue.size();

		while (size-- > 0) {

			int loc = queue.poll();

			if (loc == target)
				return moveCount;
			else {
				for (int i = 0; i < array.length; i++) {
					if (loc != i && visited[i] != 1 && array[loc][i] == 1) {
						visited[i] = 1;
						queue.add(i);
					}
				}
			}
		}
		return bfs(queue, array, visited, target, ++moveCount);
	}

	public static void main(String[] args) {

		int max = Integer.MIN_VALUE;
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[][] inputs = new int[n][2];
		for (int i = 0; i < n; i++) {
			inputs[i][0] = sc.nextInt();
			inputs[i][1] = sc.nextInt();

			max = (inputs[i][0] > max) ? inputs[i][0] : max;
			max = (inputs[i][1] > max) ? inputs[i][1] : max;
		}
		sc.close();

		int[][] array = new int[max + 1][max + 1];
		for (int i = 0; i < n; i++) {
			array[inputs[i][0]][inputs[i][1]] = 1;
		}

		Queue<Integer> queue = new LinkedList<>();

		float sum = 0;
		float count = 0;

		for (int start = 1; start < n; start++) {
			for (int i = 1; i < n; i++) {

				if (start == i)
					continue;

				queue.clear();
				queue.add(start);

				int[] visited = new int[max + 1];
				visited[start] = 1;
				int result = bfs(queue, array, visited, i, 0);
				sum += result;
				count++;
			}

		}
		System.out.printf("%.3f", sum / count);
	}

}
