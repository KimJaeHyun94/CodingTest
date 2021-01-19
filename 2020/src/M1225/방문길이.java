package M1225;

public class 방문길이 {

	static boolean visited[][][][];
	static int[][] dir = { { -1, 0 }, { 1, 0 }, { 0, 1 }, { 0, -1 } };

	public static void main(String[] args) {
		System.out.println(solution("ULURRDLLU"));
	}

	public static int solution(String dirs) {
		int answer = 0;
		visited = new boolean[11][11][11][11];

		int r = 5, c = 5;
		int idx = 0;
		for (int d = 0; d < dirs.length(); d++) {
			if (dirs.charAt(d) == 'U')
				idx = 0;
			else if (dirs.charAt(d) == 'D')
				idx = 1;
			else if (dirs.charAt(d) == 'R')
				idx = 2;
			else if (dirs.charAt(d) == 'L')
				idx = 3;
			int dr = r + dir[idx][0];
			int dc = c + dir[idx][1];

			if (isOK(dr, dc)) {
				if (!visited[r][c][dr][dc] && !visited[dr][dc][r][c]) {
					visited[r][c][dr][dc] = true;
					visited[dr][dc][r][c] = true;
					answer++;
				}
				r = dr;
				c = dc;
			}
		}
		return answer;
	}

	private static boolean isOK(int dr, int dc) {
		return dr >= 0 && dr <= 10 && dc >= 0 && dc <= 10;
	}
}
