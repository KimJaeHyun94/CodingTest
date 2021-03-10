package M0310;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Stack;
import java.util.StringTokenizer;

public class 줄서기 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		ArrayList<String> list = new ArrayList<>();
		ArrayList<String> origin = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 5; j++) {
				String str = st.nextToken();
				list.add(str);
				origin.add(str);
			}
		}
		
		
		Collections.sort(list, new Comparator<String>() {
			@Override
			public int compare(String a0, String a1) {
				String s1[] = a0.split("-");
				String s2[] = a1.split("-");

				if (s1[0].equals(s2[0])) {
					int a = Integer.parseInt(s1[1]);
					int b = Integer.parseInt(s2[1]);
					
					return a-b;
				}
				return s1[0].compareTo(s2[0]);
			}
		});
		Stack<String> stack = new Stack<>();
		int i = 0;
		int j = 0;

		while (i < 5 * N && j < 5 * N) {
			if (origin.get(i).equals(list.get(j))) {
				i++;
				j++;
			} else if (!stack.isEmpty() && stack.peek().equals(list.get(j))) {
				stack.pop();
				j++;
			} else {
				stack.add(origin.get(i++));
			}
		}

		while (!stack.isEmpty()) {
			if (stack.peek() != list.get(j++)) {
				System.out.println("BAD");
				System.exit(0);
			}
			stack.pop();
		}
		System.out.println("GOOD");

	}
}
