package M0409;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class 꼬인전깃줄 {
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
				int left = 1;
				int right = list.size() - 1;

				while (left <= right) {
					int mid = (left + right) >> 1;
					if (list.get(mid) < value) {
						left = mid + 1;
					} else {
						right = mid - 1;
					}
				}
				list.set(left, value);
			}
		}
		System.out.println(N-list.size()+1);
	}
}
