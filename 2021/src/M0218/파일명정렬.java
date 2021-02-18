package M0218;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class 파일명정렬 {
	static String[] files = { "img12.png", "img10.png", "img02.png", "img1.png", "IMG01.GIF", "img2.JPG" };
	static String[] files2 = { "F-5 Freedom Fighter", "B-50 Superfortress", "A-10 Thunderbolt II", "F-14 Tomcat" };

	public static void main(String[] args) {
		System.out.println(Arrays.toString(solution(files)));
		System.out.println(Arrays.toString(solution(files2)));
	}

	public static String[] solution(String[] files) {
		ArrayList<File> list = new ArrayList<>();
		int index = 0;
		for (String str : files) {
			StringBuilder sb = new StringBuilder();
			int i = 0;
			for (i = 0; i < str.length(); i++) {
				if ('0' <= str.charAt(i) && str.charAt(i) <= '9') {
					break;
				} else {
					sb.append(str.charAt(i));
				}
			}
			String head = sb.toString();
			sb = new StringBuilder();
			for (; i < str.length(); i++) {
				if (!('0' <= str.charAt(i) && str.charAt(i) <= '9')) {
					break;
				} else {
					sb.append(str.charAt(i));
				}
			}
			String number = sb.toString();
			sb = new StringBuilder();
			for (; i < str.length(); i++) {
				sb.append(str.charAt(i));
			}
			String tail = sb.toString();
			list.add(new File(head, number, tail));
		}

		Collections.sort(list, new Comparator<File>() {
			@Override
			
			public int compare(File f1, File f2) {
				int head = f1.head.toLowerCase().compareTo(f2.head.toLowerCase());
				if (head == 0) {
					return (Integer.parseInt(f1.number) - Integer.parseInt(f2.number));
				} else {
					return head;
				}
			}
		});

		String[] answer = new String[list.size()];
		int idx = 0;
		StringBuilder sb = new StringBuilder();

		for (File file : list) {
			sb = new StringBuilder();
			sb.append(list.get(idx).head).append(list.get(idx).number).append(list.get(idx).tail);
			answer[idx++] = sb.toString();
		}

		return answer;
	}

	static class File {
		String head;
		String number;
		String tail;

		public File(String head, String number, String tail) {
			this.head = head;
			this.number = number;
			this.tail = tail;
		}

	}

}
