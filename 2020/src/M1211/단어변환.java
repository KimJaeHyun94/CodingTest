package M1211;

public class 단어변환 {
	static int ans = Integer.MAX_VALUE;
	static boolean visited[];

	public static void main(String[] args) {
		String[] words = {"hot", "dot", "dog", "lot", "log", "cog"};
		System.out.println(solution("hit", "cog", words));
	}

	public static int solution(String begin, String target, String[] words) {

		visited = new boolean[words.length];
		DFS(0, begin, target, words);
		if (ans == Integer.MAX_VALUE) {
			return 0;
		} else
			return ans;
	}

	private static void DFS(int r, String begin, String target, String[] words) {
		if (begin.equals(target)) {
			ans = Math.min(ans, r);
			return;
		}

		for (int i = 0; i < words.length; i++) {
			int flag = 0;
			for (int j = 0; j < begin.length(); j++) {
				if (begin.charAt(j) != words[i].charAt(j)) {
					flag++;
				}
			}
			if (!visited[i] && flag == 1) {
				//System.out.println(begin + " " +words[i] +" "+flag);
				visited[i] = true;
				DFS(r + 1, words[i], target, words);
				visited[i] = false;
			}
		}
	}
}
