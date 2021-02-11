package M0211;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
/**
 * 
 * @author AKKabiyo
 * @see https://codingjuny.tistory.com/39
 *
 */
public class 매칭점수 {
	static String sol[] = {
			"<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://a.com\"/>\n</head>  \n<body>\nBlind Lorem Blind ipsum dolor Blind test sit amet, consectetur adipiscing elit. \n<a href=\"https://b.com\"> Link to b </a>\n</body>\n</html>",
			"<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://b.com\"/>\n</head>  \n<body>\nSuspendisse potenti. Vivamus venenatis tellus non turpis bibendum, \n<a href=\"https://a.com\"> Link to a </a>\nblind sed congue urna varius. Suspendisse feugiat nisl ligula, quis malesuada felis hendrerit ut.\n<a href=\"https://c.com\"> Link to c </a>\n</body>\n</html>",
			"<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://c.com\"/>\n</head>  \n<body>\nUt condimentum urna at felis sodales rutrum. Sed dapibus cursus diam, non interdum nulla tempor nec. Phasellus rutrum enim at orci consectetu blind\n<a href=\"https://a.com\"> Link to a </a>\n</body>\n</html>" };

	public static void main(String[] args) {
		System.out.println(solution("blind", sol));
	}

	public static int solution(String word, String[] pages) {
		int answer = 0;
		int idx = 0;
		word = word.toLowerCase();
		ArrayList<Page> list = new ArrayList<Page>();
		for (String page : pages) {
			String text = page.toLowerCase(); // 일단 소문자로 다 바꾼다.
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
			public int compare(Page p0, Page p1) {
				if (p0.total == p1.total) {
					return p0.index - p1.index;
				} else {
					if (p0.total < p1.total) {
						return 1;
					} else {
						return -1;
					}
				}
			}
		});

		return list.get(0).index;
	}

	private static ArrayList<String> getLinks(String text) {
		ArrayList<String> links = new ArrayList<String>();
		String link = "<a href=\"";
		int st = 0;
		while (true) {
			int start = text.indexOf(link, st) + link.length();
			int end = text.indexOf("\"", start);

			if (start == link.length() - 1)    //링크들을 저장하는데 마지막까지 도달한다면 빠져나가게 한다. 
				break;
			st = start;
			links.add(text.substring(start, end));
		}
		return links;
	}

	private static double getNormal(String text, String word) {   //기본 점수 얻기 
		String body = "<body>";
		double score = 0;
		int start = text.indexOf(body) + body.length();
		int end = text.indexOf("</body>", start);
		String page = text.substring(start, end);
		for (int i = 0; i < page.length(); i++) {   //알파벳이 아닌 문자를 모두 띄어쓰기로 치환해둔다. 
			char ch = page.charAt(i);
			if (ch < 'a' || ch > 'z') {
				page = page.replace(ch, ' ');
			}
		}
		String[] temp = page.split(" ");
		for (String str : temp) {
			if (str.equals(word)) {
				score++;
			}
		}
		return score;
	}

	private static String getUrl(String text) {   //url자르기
		String check = "<meta property=\"og:url\" content=\"";
		int start = text.indexOf(check) + check.length(); // 시작 인덱스
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
