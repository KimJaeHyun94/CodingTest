package M0227;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

public class 소마피시방 {
	static List<PC> list;
	static int graph[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int P = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		int H = Integer.parseInt(st.nextToken());

		list = new ArrayList<>();
		graph = new int[P+1];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			list.add(new PC(a, b));
		}
		Collections.sort(list, new Comparator<PC>() {
			@Override
			public int compare(PC a0, PC a1) {
				return a0.y - a1.y;
			}

		});

		for (int i = 0; i < list.size(); i++) {
			if (H >= list.get(i).y) {
				H -= list.get(i).y;
				graph[list.get(i).x]+=list.get(i).y;
			}
		}
		
		for (int i = 1; i <= P; i++) {
			System.out.println(i+" "+graph[i]*1000);
		}
	}

	static class PC {
		int x;
		int y;

		public PC(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}
