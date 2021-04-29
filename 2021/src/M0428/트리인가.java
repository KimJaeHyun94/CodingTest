package M0428;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;
import java.util.StringTokenizer;

public class 트리인가 {
	static HashSet<Integer> tree = new HashSet<>();
	static HashMap<Integer, Integer> map = new HashMap<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringBuilder sb = new StringBuilder();
		Scanner sc = new Scanner(System.in);
		int tc = 1;
		boolean flag = true;
		while (true) {
			int u = sc.nextInt();
			int v = sc.nextInt();
			if (u == -1 && v == -1) {
				break;
			} else if (u == 0 && v == 0) {
				if (tree.size() == 0) {
					sb.append("Case ").append(tc).append(" is a tree.").append("\n");
				} else if (!flag) {
					sb.append("Case ").append(tc).append(" is not a tree.").append("\n");
				} else {
					Iterator iter = tree.iterator();
					int cnt = 0;
					while (iter.hasNext()) {
						int num = (int) iter.next();
						if (!map.containsKey(num)) {
							cnt++;
						}
					}
					if (cnt != 1 || tree.size() - map.size() != 1) {
						sb.append("Case ").append(tc).append(" is not a tree.").append("\n");
					} else {
						sb.append("Case ").append(tc).append(" is a tree.").append("\n");
					}
				}
				tree.clear();
				map.clear();
				tc++;
				flag = true;
			} else {
				tree.add(u);
				tree.add(v);
				if (map.containsKey(v)) {
					flag = false;
				} else
					map.put(v, 1);
			}
		}
		System.out.println(sb);
	}

}
