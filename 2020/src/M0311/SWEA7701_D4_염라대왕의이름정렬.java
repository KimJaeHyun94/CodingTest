package M0311;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.TreeSet;

public class SWEA7701_D4_염라대왕의이름정렬 {
	static int N;
	static String map[];
	static TreeSet<String> tree;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			N=Integer.parseInt(br.readLine());
			map=new String[N];
			for (int i = 0; i <N; i++) {
				map[i]=br.readLine();
			}
			tree=new TreeSet<String>(new Comparator<String>() {

				@Override
				public int compare(String o1, String o2) {
					Integer len1=o1.length();
					Integer len2=o2.length();
					if(len1==len2) {
						return o1.compareTo(o2);
					}
					return len1.compareTo(len2);
				}
				
			});
			for (int i = 0; i < N; i++) {
				tree.add(map[i]);
			}
			
			System.out.println("#"+tc);
			for (String string : tree) {
				System.out.println(string);
			}
			
		}
	}
}
