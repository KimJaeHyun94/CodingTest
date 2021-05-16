package M0508;

public class 카카오1 {
	public static void main(String[] args) {
		System.out.println(solution("23four5six7"));
	}
	public static int solution(String s) {
		int answer = 0;
		StringBuilder sb = new StringBuilder();
		StringBuilder sc = new StringBuilder();
		for (int i = 0; i < s.length(); i++) {
			char ch = s.charAt(i);
			if (ch > '0' && ch < '9') {
				sb.append(ch);
			} else {
				sc = new StringBuilder();
				while (true) {
					if(i>=s.length())
						break;
					char ch2 = s.charAt(i);
					if (ch2 > '0' && ch2 < '9') {
						i-=1;
						break;
					} else {
						sc.append(ch2);
					}
					i++;
					if(check(sc)!=-1) {
						sb.append(check(sc));
						sc = new StringBuilder();
					}
				}
			}
		}
		sb.append(sc);
		return answer = Integer.parseInt(sb.toString());
	}

	private static int check(StringBuilder sc) {
		String str = sc.toString();
		switch (str) {
		case "zero":
			return 0;
		case "one":
			return 1;
		case "two":
			return 2;
		case "three":
			return 3;
		case "four":
			return 4;
		case "five":
			return 5;
		case "six":
			return 6;
		case "seven":
			return 7;
		case "eight":
			return 8;
		case "nine":
			return 9;
		}
		return -1;
	}
}
