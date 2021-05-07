package M0505;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class 전설의JBNU {
	static int N, M, K;
	static TreeMap<Integer, Integer> tm = new TreeMap<>();
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		tm.put(-2000000000, -1);
		tm.put(2000000000, -1);

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int key = Integer.parseInt(st.nextToken());
			int value = Integer.parseInt(st.nextToken());

			tm.put(key, value);
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int k = Integer.parseInt(st.nextToken());
			int key = Integer.parseInt(st.nextToken());

			if (k == 1) {
				int value = Integer.parseInt(st.nextToken());
				tm.put(key, value);
			} else if (k == 2) {
				int value = Integer.parseInt(st.nextToken());
				changeValue(key, value);
			} else {
				findKey(key);
			}
		}
		System.out.println(sb);
	}

	private static void findKey(int key) {
		int rightKey = tm.ceilingKey(key);
		int leftKey = tm.floorKey(key);

		if (tm.get(rightKey) == tm.get(leftKey)) {
			sb.append(tm.get(key) + "\n");
		} else if (rightKey - key == key - leftKey && K >= rightKey - key) {
			sb.append("?\n");
		} else if (rightKey - key < key - leftKey && K >= rightKey - key) {
			sb.append(tm.get(rightKey) + "\n");
		} else if (rightKey - key > key - leftKey && K >= key - leftKey) {
			sb.append(tm.get(leftKey) + "\n");
		} else {
			sb.append("-1\n");
		}
	}

	private static void changeValue(int key, int value) {
		int rightKey = tm.ceilingKey(key);
		int leftKey = tm.floorKey(key);

		if (tm.get(rightKey) == tm.get(leftKey)) {
			tm.put(key, value);
		} else if (rightKey - key < key - leftKey && K >= rightKey - key) {
			tm.put(rightKey, value);
		} else if (rightKey - key > key - leftKey && K >= key - leftKey) {
			tm.put(leftKey, value);
		}
	}
}
