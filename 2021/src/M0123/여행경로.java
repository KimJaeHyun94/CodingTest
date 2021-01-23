package M0123;

import java.util.*;

public class 여행경로 {
	static ArrayList<String> Route;
	static String[][] ticket;
	static boolean[] visited;
	static String [][] sol =  { { "ICN", "JFK" }, { "HND", "IAD" }, { "JFK", "HND" } };
	public static void main(String[] args) {
		System.out.println(
				Arrays.toString(solution(sol)));
	}

	public static String[] solution(String[][] tickets) {
		String[] answer = {};
		ticket = tickets;
		visited = new boolean[tickets.length];

		Route = new ArrayList<>(); // 경로
		DFS(0, "ICN");
		Collections.sort(Route); // 알파벳 순서대로 정렬
		answer = Route.get(0).split(","); // 첫번째 경로(가장 알파벳 순서 앞에있는것)
		return answer;
	}

	private static void DFS(int cnt, String road) {
		if (cnt == ticket.length) // 모든 곳을 방문하였다면
		{
			Route.add(road); // 경로를 저장해둔다
			return;
		}

		for (int i = 0; i < ticket.length; i++) {
			String str = road.substring(road.length() - 3, road.length()); // 뒤에서부터 3개 자르기
			if (str.equals(ticket[i][0]) && !visited[i]) // 출발점과 일치하거나 방문하지 않은 곳이면
			{
				visited[i] = true;
				DFS(cnt + 1, road + "," + ticket[i][1]);
				visited[i] = false;
			}
		}
	}
}
