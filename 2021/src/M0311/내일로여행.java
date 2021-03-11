package M0311;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class 내일로여행 {
	static int N, R, M, K;
	static HashMap<String, Integer> citymap;
	static double today[][], tomorrow[][];
	static int path[];
	static double INF = 987654321;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());

		citymap = new HashMap();
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			citymap.put(st.nextToken(), i);
		}

		today = new double[N][N];
		tomorrow = new double[N][N];

		M = Integer.parseInt(br.readLine());
		path = new int[M];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < M; i++) {
			path[i] = citymap.get(st.nextToken());
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (i == j)
					continue;
				today[i][j] = INF;
				tomorrow[i][j] = INF;
			}
		}
		K = Integer.parseInt(br.readLine());
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());

			String str = st.nextToken();
			int start = citymap.get(st.nextToken());
			int end = citymap.get(st.nextToken());
			double price = Double.parseDouble(st.nextToken());

			double discount = sale(str, price);
			today[start][end] = Math.min(today[start][end], price);
			tomorrow[start][end] = Math.min(tomorrow[start][end], discount);

			today[end][start] = Math.min(today[end][start], price);
			tomorrow[end][start] = Math.min(tomorrow[end][start], discount);
		}

		FloydWarshall();
		double first = 0;
		double second = R;  //R원 제출

		for (int i = 0; i < M - 1; i++) {
			int start = path[i];
			int end = path[i + 1];

			first += today[start][end];
			second += tomorrow[start][end];
		}
		if (first > second) {
			System.out.println("Yes");
		} else {
			System.out.println("No");

		}
	}

	private static void FloydWarshall() {
		for (int k = 0; k < N; k++) {
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					today[i][j] = Math.min(today[i][k] + today[k][j], today[i][j]);
					tomorrow[i][j] = Math.min(tomorrow[i][k] + tomorrow[k][j], tomorrow[i][j]);
				}
			}
		}
	}

	private static double sale(String str, double price) {
		switch (str) {
		case "ITX-Saemaeul":
		case "ITX-Cheongchun":
		case "Mugunghwa":
			return 0;
		case "S-Train":
		case "V-Train":
			return price * 0.5;
		default:
			return price;
		}
	}
}
