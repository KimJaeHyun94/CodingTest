package M0428;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;

public class 생태학 {
	static HashMap<String, Integer> map;
	static HashSet<String> set;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		map = new HashMap<>();
		set = new HashSet<>();
		int cnt = 0;
		String strs = br.readLine();
		while (true) {
			cnt++;
			map.put(strs, map.getOrDefault(strs, 0) + 1);
			set.add(strs);
			strs = br.readLine();
			if (strs == null || strs.length() == 0) {
				break;
			}
		}

		ArrayList<String> list = new ArrayList<String>(set);

		Collections.sort(list);
		
		for (String str : list) {
			double per = (double)(map.get(str)*100)/cnt;
			System.out.print(str+" ");
			System.out.println(String.format("%.4f", per));
		}
	}
}
