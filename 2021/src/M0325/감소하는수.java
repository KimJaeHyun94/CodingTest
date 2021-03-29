package M0325;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class 감소하는수 {
	static int idx;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		idx = Integer.parseInt(br.readLine());
		if (idx > 1022) {
			System.out.println(-1);
			System.exit(0);
		}

		ArrayList<Integer> list = new ArrayList<>();

		for (int i = 0; i < 10; i++) {
			DFS(i, 1, list);
		}
		Collections.sort(list);

		System.out.println(list.get(idx));
	}

	private static ArrayList DFS(long num, int temp, ArrayList list) {
		if (temp > 10)
			return list;
	
		list.add(num);

		for (int i = 0; i < 10; i++) {
			if (num % 10 > i) {
				DFS((num * 10) + i, temp + 1, list);
			}
		}
		return list;
	}
}
