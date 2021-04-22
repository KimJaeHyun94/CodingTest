package 이분탐색;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class BinarySearch {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		List<Integer> list = new ArrayList<>();
		list.add(0);
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			int value = Integer.parseInt(st.nextToken());
			if (list.get(list.size() - 1) < value) {
				list.add(value);
			} else {
				int idx = Collections.binarySearch(list, value);
				idx = (idx < 0) ? -idx - 1 : idx;

				list.set(idx, value);
			}
		}
		System.out.println(list.size() - 1);
	}
}

