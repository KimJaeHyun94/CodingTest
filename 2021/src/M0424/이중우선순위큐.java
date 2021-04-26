package M0424;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.util.StringTokenizer;
import java.util.TreeMap;

public class 이중우선순위큐 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();

		for (int tc = 0; tc < T; tc++) {
			int N = Integer.parseInt(br.readLine());
			TreeMap<Integer, Integer> treeMap = new TreeMap<>();

			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				char command = st.nextToken().charAt(0);
				int n = Integer.parseInt(st.nextToken());

				if (command == 'I') {
					treeMap.put(n, treeMap.getOrDefault(n, 0) + 1);
				} else {
					if (!treeMap.isEmpty()) {
						if (n == 1) {
							int num = treeMap.lastKey();
							if (treeMap.get(num) == 1) {
								treeMap.remove(num);
							} else {
								treeMap.put(num, treeMap.get(num) - 1);
							}

						} else {
							int num = treeMap.firstKey();
							if (treeMap.get(num) == 1) {
								treeMap.remove(num);
							} else {
								treeMap.put(num, treeMap.get(num) - 1);
							}
						}
					}
				}
			}
			if(treeMap.isEmpty()) {
				sb.append("EMPTY").append("\n");
			}else
				sb.append(treeMap.lastKey()).append(" ").append(treeMap.firstKey()).append("\n");
		}
		System.out.println(sb);
	}
}
