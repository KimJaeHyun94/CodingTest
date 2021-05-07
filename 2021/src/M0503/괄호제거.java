package M0503;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Stack;

public class 괄호제거 {
	static int arr[];
	static HashSet<String> set;
	static String str;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		str = br.readLine();

		arr = new int[str.length()];
		int idx = 1;
		Stack<Integer> stack = new Stack<>();

		for (int i = 0; i < str.length(); i++) {
			if (str.charAt(i) == '(') {
				arr[i] = idx;
				stack.push(idx++);
			} else if (str.charAt(i) == ')') {
				arr[i] = stack.pop();
			}
		}

		set = new HashSet();
		comb(0, new HashSet<Integer>(), "");
		ArrayList<String> list = new ArrayList<>(set);
		Collections.sort(list);
		
		StringBuilder sb = new StringBuilder();
		for (String st : list) {
			sb.append(st).append("\n");
		}
		System.out.println(sb);
	}

	private static void comb(int idx, HashSet<Integer> s, String ns) {
		if (idx == str.length()) {
			if (!ns.equals(str)) {
				if (!set.contains(ns)) {
					set.add(ns);
				}
			}
			return;
		}

		if (str.charAt(idx) == '(') {
			comb(idx + 1, s, ns + '(');		//선택
			s.add(arr[idx]);
			comb(idx + 1, s, ns);		//비선택
			s.remove(arr[idx]);
		}else if(str.charAt(idx)==')' && s.contains(arr[idx])) {
			comb(idx+1,s,ns);			//괄호를 닫는 경우 
		}else {
			comb(idx+1,s,ns+str.charAt(idx));
		}

	}
}
