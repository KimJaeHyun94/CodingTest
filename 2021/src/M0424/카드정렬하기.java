package M0424;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class 카드정렬하기 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		PriorityQueue<Integer> pq = new PriorityQueue<>();
		if (N == 1) {
			String line = br.readLine();
			System.out.println(0);
		} else {
			for (int i = 1; i <= N; i++) {
				pq.add(Integer.parseInt(br.readLine()));
			}
			int num = 0;
			while (pq.size() > 1) {
				int s = pq.poll();
				int e = pq.poll();

				num += s + e;
				pq.add(s + e);

			}
			System.out.println(num);
		}
	}
}
