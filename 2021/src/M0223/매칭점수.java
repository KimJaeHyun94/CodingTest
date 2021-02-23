package M0223;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class 매칭점수 {
	public static void main(String[] args) {
		System.out.println(solution("blind", new String[] {
				"<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://a.com\"/>\n</head>  \n<body>\nBlind Lorem Blind ipsum dolor Blind test sit amet, consectetur adipiscing elit. \n<a href=\"https://b.com\"> Link to b </a>\n</body>\n</html>",
				"<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://b.com\"/>\n</head>  \n<body>\nSuspendisse potenti. Vivamus venenatis tellus non turpis bibendum, \n<a href=\"https://a.com\"> Link to a </a>\nblind sed congue urna varius. Suspendisse feugiat nisl ligula, quis malesuada felis hendrerit ut.\n<a href=\"https://c.com\"> Link to c </a>\n</body>\n</html>",
				"<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://c.com\"/>\n</head>  \n<body>\nUt condimentum urna at felis sodales rutrum. Sed dapibus cursus diam, non interdum nulla tempor nec. Phasellus rutrum enim at orci consectetu blind\n<a href=\"https://a.com\"> Link to a </a>\n</body>\n</html>" }));

		System.out.println(solution("Muzi", new String[] {
				"<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://careers.kakao.com/interview/list\"/>\n</head>  \n<body>\n<a href=\"https://programmers.co.kr/learn/courses/4673\"></a>#!MuziMuzi!)jayg07con&&\n\n</body>\n</html>",
				"<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://www.kakaocorp.com\"/>\n</head>  \n<body>\ncon%\tmuzI92apeach&2<a href=\"https://hashcode.co.kr/tos\"></a>\n\n\t^\n</body>\n</html>" }));
	}

	public static int solution(String word, String[] pages) {
		int idx = 0;
		word = word.toLowerCase(); // 소문자로 바꿔준다
		ArrayList<Page> list = new ArrayList<Page>();
		for (String page : pages) {
			String text = page.toLowerCase();
			String url = getUrl(text);
			double normal = getNormal(text, word);
			ArrayList<String> links = getLinks(text);

			list.add(new Page(idx, normal, url, links));
			idx++;
		}

		for (int i = 0; i < list.size(); i++) {
			ArrayList<String> temp = list.get(i).links;
			for (int j = 0; j < list.size(); j++) {
				if (i != j) {
					if (temp.contains(list.get(j).url)) {
						list.get(j).total += list.get(i).normal / list.get(i).links.size();
					}
				}
			}
		}

		Collections.sort(list, new Comparator<Page>() {
			@Override
			public int compare(Page a0, Page a1) {
				if (a0.total != a1.total) {
					if(a0.total>a1.total) {
						return -1;
					}else {
						return 0;
					}
				} else {
					return a0.index - a1.index;
				}
			}

		});
		return list.get(0).index;
	}

	private static ArrayList<String> getLinks(String text) {
		String check = "<a href=\"";
		ArrayList<String> links = new ArrayList<String>();
		int cnt = 0;
		while (true) {
			int start = text.indexOf(check, cnt) + check.length();
			int end = text.indexOf("\"", start);

			if (start == check.length() - 1) {
				break;
			}
			cnt = start;
			links.add(text.substring(start, end));
		}
		return links;
	}

	private static double getNormal(String text, String word) {
		String check = "<body>";
		double score = 0;
		int start = text.indexOf(check) + check.length();
		int end = text.indexOf("</body>", start);
		String body = text.substring(start, end); // 바디 안까지 잘라준다.
		for (int i = 0; i < body.length(); i++) {
			char ch = body.charAt(i);
			if ('a' > ch || ch > 'z') {
				body=body.replace(ch, ' '); // 단어 단위로 잘라야 되기 때문에
			}
		}
		for (String str : body.split(" ")) {
			if (str.equals(word)) {
				score++;
			}
		}
		return score;
	}

	private static String getUrl(String text) {
		String check = "<meta property=\"og:url\" content=\"";
		int start = text.indexOf(check) + check.length(); // url 시작 위치
		int end = text.indexOf("\"", start);
		return text.substring(start, end);
	}

	static class Page {
		int index;
		double normal;
		String url;
		ArrayList<String> links;
		double total;

		public Page(int index, double normal, String url, ArrayList<String> links) {
			this.index = index;
			this.normal = normal;
			this.url = url;
			this.links = links;
			this.total = normal;
		}
	}
}
