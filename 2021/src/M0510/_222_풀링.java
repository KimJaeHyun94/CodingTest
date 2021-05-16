package M0510;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

public class _222_풀링 {
	static int N;
	static int map[][];

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		map = new int[N][N];

		for (int r = 0; r < N; r++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int c = 0; c < N; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		while (N > 1) {
			for (int i = 0; i < N; i += 2) { // 2칸씩
				for (int j = 0; j <N; j += 2) {
					map[i/2][j/2] = pulling(i,j);
				}
			}
			N/=2;
		}
		
		System.out.println(map[0][0]);
	}

	private static int pulling(int x, int y) {
		ArrayList<Integer> list = new ArrayList<>();
		for (int i = x; i < x+2; i++) {
			for (int j = y; j < y+2; j++) {
				list.add(map[i][j]);
			}
		}
		Collections.sort(list);
		return list.get(2);
	}

}
