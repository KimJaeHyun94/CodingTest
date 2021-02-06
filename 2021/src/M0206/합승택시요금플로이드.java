package M0206;

public class 합승택시요금플로이드 {
	static int[][] distance;
	static private int INF = 1000000000;
	static int N;

	public static void main(String[] args) {
		System.out.println(solution(6, 4, 6, 2, new int[][] { { 4, 1, 10 }, { 3, 5, 24 }, { 5, 6, 2 }, { 3, 1, 41 },
				{ 5, 1, 24 }, { 4, 6, 50 }, { 2, 4, 66 }, { 2, 3, 22 }, { 1, 6, 25 } }));

		System.out.println(
				solution(7, 3, 4, 1, new int[][] { { 5, 7, 9 }, { 4, 6, 4 }, { 3, 6, 1 }, { 3, 2, 3 }, { 2, 1, 6 } }));


	}

	public static int solution(int n, int s, int a, int b, int[][] fares) {
		distance = new int[n + 1][n + 1];
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				if (i == j)
					continue;
				distance[i][j] = INF;
			}
		}
		N = n;

		for (int i = 0; i < fares.length; i++) {
			int start = fares[i][0];
			int end = fares[i][1];
			int w = fares[i][2];

			distance[start][end] = w;
			distance[end][start] = w;
		}
		floydWarshall();
		int min = INF;

		for (int i = 1; i <= N; i++) {
			int route = distance[s][i] + distance[i][a] + distance[i][b];
			if (route > 0 && route < INF)
				min = Math.min(min, route);
		}
		return min;
	}

	public static void floydWarshall() {
		for (int k = 1; k <= N; k++) {
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					distance[i][j] = Math.min(distance[i][k] + distance[k][j], distance[i][j]);
				}
			}
		}
	}
}
