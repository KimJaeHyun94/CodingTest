package M0325;

public class PG_Network {
	static boolean visited[][];
	public int solution(int n, int[][] computers) {
		int answer = 0;
		visited = new boolean[n][n];
		for (int i = 0; i < n; i++) {
			if(!visited[i][i]) {
				dfs(computers, i, n);
				answer++;
			}
		}
		return answer;
	}
	private void dfs(int[][] computers, int idx, int n) {
		for (int j = 0; j < n; j++) {
			if(computers[idx][j] == 1 && !visited[idx][j]) {
				visited[idx][j]=true;
				visited[j][idx]=true;
				dfs(computers, j, n);
			}
		}
	}
}
