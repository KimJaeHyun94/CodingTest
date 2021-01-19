package M0429;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class SWEA5658_보물상자비밀번호 {
	static int N, K;
	static char map[];
	char check[];
	static Set<Integer> set = new TreeSet<>(Collections.reverseOrder());

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());

			map = br.readLine().toCharArray();
			set = new TreeSet<>(Collections.reverseOrder());
			for (int k = 0; k < N / 4; k++) {
				for (int i = 0; i < N; i += N / 4) {
					String temp = "";
					for (int j = 0; j < N / 4; j++) {
						temp = temp.concat(Character.toString(map[i + j]));
					}
					set.add(hexToDec(temp));
				}
				swap();
			}
			ArrayList<Integer>list = new ArrayList<>();
			list.addAll(set);
			System.out.println("#"+tc+" "+list.get(K-1));
		}
	}

	private static void swap() {
		char fin = map[N - 1];
		char check[]= map.clone();
		
		for (int i = 1; i <= N - 1; i++) {
			check[i] = map[i-1];
		}
		check[0] = fin;
		map = check.clone();
	}

	private static int hexToDec(String hex) {
		int dec = Integer.parseInt(hex, 16);
		return dec;
	}
}
