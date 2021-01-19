package M0315;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class BJ17471게리맨더링 {
	static int N;
	static int population[];
	static int isLinked[][];
	static int answer = Integer.MAX_VALUE;
	static boolean visited[];
	static int team[];

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		population = new int[N + 1];
		visited = new boolean[N + 1];
		team = new int[N + 1];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			population[i] = Integer.parseInt(st.nextToken());
		}
		isLinked = new int[N + 1][N + 1];

		
		for (int i = 1; i <= N; i++) {
			String str = br.readLine();
			String[] temp = str.split(" ");
			for (int j = 1; j < temp.length; j++) {
				isLinked[i][Integer.parseInt(temp[j])] = 1;
				isLinked[Integer.parseInt(temp[j])][i] = 1;
			}
		}
		setTeam(team,1);
		if (answer == Integer.MAX_VALUE)
			System.out.println("-1");
		else
			System.out.println(answer);
	}

	public static void setTeam(int team[], int index) {
		if (index == N + 1) {
			if (checkLinked(team, 0) && checkLinked(team, 1))
				answer = Math.min(answer, solve(team));
			return;
		} 
		team[index] = 1;
		setTeam(team, index + 1);
		team[index] = 0;
		setTeam(team, index + 1);
	}

	public static boolean checkLinked(int team[], int team_idx) {
		boolean visited[] = new boolean[N + 1];

		LinkedList<Integer> list = new LinkedList<Integer>();
		for (int i = 1; i <= N; i++) {
			if (team[i] == team_idx) {
				list.addLast(i);
				visited[i] = true;
				break;
			}
		}
		while (!list.isEmpty()) {
			int now = list.pollFirst();
			for (int i = 1; i <= N; i++) {
				if (visited[i])
					continue;
				if (team[i] != team_idx)
					continue;
				if (isLinked[now][i] == 0)
					continue;
				list.addLast(i);
				visited[i] = true;
			}
		}

		for (int i = 1; i <= N; i++) {
			if (team[i] != team_idx)
				continue;
			if (visited[i] == false)
				return false;
		}

		return true;
	}
	public static int solve(int team[]) {
		int sum1 = 0;
		int sum2 = 0;
		for (int i = 1; i <= N; i++) {
			if (team[i] == 1)
				sum1 += population[i];
			else if (team[i] == 0)
				sum2 += population[i];
		}
		return Math.abs(sum1 - sum2);
	}
}