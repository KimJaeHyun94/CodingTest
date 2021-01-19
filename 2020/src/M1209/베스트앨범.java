package M1209;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class 베스트앨범 {
	public static void main(String[] args) {
		String[] genres = { "classic", "pop", "classic", "classic", "pop" };
		int[] plays = { 500, 600, 150, 800, 2500 };
		System.out.println(Arrays.toString(solution(genres, plays)));
	}

	public static int[] solution(String[] genres, int[] plays) {
		HashMap<String, Integer> genmap = new HashMap<>();
		int[] answer = {};
		
		Node[] total;
		List<Node>[] list;
		int cnt = 0;
		
		for (String genre : genres) {
			if (!genmap.containsKey(genre)) {
				genmap.put(genre, cnt++);
			}
		}
		
		list = new List[genmap.size()];
		total = new Node[genmap.size()];

		for (int i = 0; i < list.length; i++) {
			list[i] = new ArrayList<>();
		}

		for (int i = 0; i < genres.length; i++) {
			int gnum = genmap.get(genres[i]);
			int play = plays[i];
			
	   		if (total[gnum] == null) {
				total[gnum] = new Node(gnum, play);
			} else {
				int temp = total[gnum].plays;
				total[gnum] = new Node(gnum, temp + play);
			}
			list[gnum].add(new Node(i, play));
		}
		
		Arrays.sort(total, new Comparator<Node>() {
			@Override
			public int compare(Node o1, Node o2) {
				return Integer.compare(o2.plays, o1.plays);
			}
		});
		
		 for (int i = 0; i < list.length; i++) {
	        	Collections.sort(list[i], new Comparator<Node>() {
					@Override
					public int compare(Node o1, Node o2) {
						if(o1.plays!=o2.plays) {
							return Integer.compare(o2.plays, o1.plays);
						}else return Integer.compare(o1.num, o2.num);
					}
				});
	        }
		
		
		ArrayList<Integer> selected_song = new ArrayList<>();
		for (int i = 0; i < total.length; i++) {
			Node no = total[i];
			int num = no.num;

			if (list[num].size() < 2) {
				for (int j = 0; j < list[num].size(); j++) {
					selected_song.add(list[num].get(j).num);
				}
			} else {
				for (int j = 0; j < 2; j++) {
					selected_song.add(list[num].get(j).num);
				}
			}
		}

	    answer = new int[selected_song.size()];
		for (int i = 0; i < answer.length; i++) {
			answer[i] = selected_song.get(i);
		}
		return answer;

	}

	static class Node {
		int num;
		int plays;

		public Node(int num, int plays) {
			this.num = num;
			this.plays = plays;
		}

	}
}
