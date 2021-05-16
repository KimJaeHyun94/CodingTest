package M0508;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class 카카오3 {
	public static void main(String[] args) {
		String[] cmd = { "D 2", "C", "U 3", "C", "D 4", "C", "U 2", "Z", "Z" };
		// System.out.println(solution(8, 2, cmd));
		String[] cmd2 = { "D 2", "C", "U 3", "C", "D 4", "C", "U 2", "Z", "Z", "U 1", "C" };
		System.out.println(solution(8, 2, cmd2));

	}

	public static String solution(int n, int k, String[] cmd) {
		String answer = "";

		ArrayList<Integer> list = new ArrayList<>();
		Stack<Integer> s = new Stack<>();
		for (int i = 0; i < n; i++) {
			list.add(i);
		}

		int sel = k;
		for (int i = 0; i < cmd.length; i++) {
			char ch = cmd[i].charAt(0);
			if (ch == 'U') {
				int id = cmd[i].charAt(2) - '0';
				sel -= id;
			} else if (ch == 'D') {
				int id = cmd[i].charAt(2) - '0';
				sel += id;
			} else if (ch == 'C') {
				s.add(list.get(sel));
				int del = sel;
				if (sel == list.size() - 1) {
					sel = del - 1;
				}
				list.remove(del);
			} else if (ch == 'Z') {
				int cur = s.pop();

				if (list.size() < cur) {
					list.add(cur);
				} else {
					list.add(cur, cur);
					if(cur<sel) {
						sel+=1;
					}
				}
			}
		}

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < n; i++) {
			boolean flag = false;
			for (Integer integer : list) {
				if (i == integer) {
					flag = true;
					break;
				}
			}
			if (flag) {
				sb.append("O");
			} else
				sb.append("X");
		}
		return sb.toString();
	}

}
