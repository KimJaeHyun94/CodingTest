package M1224;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

public class 단어수학permutation {
	static int N;
	static HashMap<Character, Integer> map;
	static HashSet<Character> set;
	static ArrayList<Character> alphabet;
	static boolean visited[];
	static int ans;
	static String arr[];

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new String[N];
		set = new HashSet<>();
		map = new HashMap<>();
		for (int i = 0; i < N; i++) {
			arr[i] = br.readLine();
			for (int j = 0; j < arr[i].length(); j++) {
				set.add(arr[i].charAt(j));
			}
		}
		alphabet = new ArrayList<>(set);

		ans = Integer.MIN_VALUE;
		visited = new boolean[10];
		permutation(0);
		System.out.println(ans);

	}

	private static void permutation(int r) {
		if (r == set.size()) {
			ans = Math.max(sol(), ans);
			return;
		}
		for (int i = 0; i <= 9; i++) {
			if (!visited[i]) {
				visited[i] = true;
				map.put(alphabet.get(r), i);
				permutation(r + 1);
				visited[i] = false;
			}
		}
	}

	private static int sol() {
		int sum = 0;
		for (int i = 0; i < N; i++) {
			String line = arr[i];
			int temp = 0 ;
			for (int j = 0; j < line.length(); j++) {
				temp*=10;
				temp+=map.get(line.charAt(j));
			}
			sum+=temp;
			
		}
		return sum;
	}

}
