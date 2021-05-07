package M0502;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 별찍기11_2 {

	static int N;
	static String map[];

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new String[N];
		map[0] = "  *  ";
		map[1] = " * * ";
		map[2] = "*****";

		for (int k = 1; 3 * (int) Math.pow(2, k) <= N; ++k) {
			makeBigStar(k);
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < N; i++) {
			sb.append(map[i]).append("\n");
		}
		System.out.println(sb);
	}

	private static void makeBigStar(int k) {
		int bottom = (int) (3 * Math.pow(2, k));
		int middle = bottom / 2;

		for (int i = middle; i < bottom; i++) {
			map[i] = map[i - middle] + " " + map[i - middle];
		}

		String space = ""; // 가운데 띄우는거
		for (int i = 0; i < middle; i++) {
			space += " ";
		}

		for (int i = 0; i < middle; i++) {
			map[i] = space + map[i] + space;
		}
	}

}
