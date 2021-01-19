package M1210;

public class 네트워크 {
	static boolean visited[][];

	public static void main(String[] args) {
		int [][] computers = {{1, 1, 0}, {1, 1, 0}, {0, 0, 1}};
		System.out.println(solution(3, computers));
		
	}

	public static int solution(int n, int[][] computers) {
		int answer = 0;
		visited = new boolean[n][n];
		for (int i = 0; i < n; i++) {
			if (!visited[i][i]) {
				dfs(computers, i, n);
				answer++;
			}
		}
		return answer;
	}

	private static void dfs(int[][] computers, int idx, int n) {
		for (int j = 0; j < n; j++) {
			if (computers[idx][j] == 1 && !visited[idx][j]) {
				visited[idx][j] = true;
				visited[j][idx] = true;
				dfs(computers, j, n);
			}
		}
	}
}
