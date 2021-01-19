package M0119;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;


public class 베스트앨범 {
	ArrayList<Integer> best; // 뽑힌 앨범
	ArrayList<Song> list; // 노래 리스트
	HashMap<String, Integer> genreMap; // 장르
	HashMap<String, Integer> albumMap; // 앨범

	public int[] solution(String[] genres, int[] plays) {
		best = new ArrayList<>();
		list = new ArrayList<>();
		genreMap = new HashMap<>();
		albumMap = new HashMap<>();

		for (int i = 0; i < genres.length; i++) {
			int id = i; // 고유 번호
			int play = plays[i]; // 플레이 횟수
			String genre = genres[i]; // 장르

			list.add(new Song(id, play, genre)); // 음악 넣기

			if (genreMap.containsKey(genre)) { // 원래 있다면 추가해주기
				genreMap.put(genre, genreMap.get(genre) + play);
			} else {
				genreMap.put(genre, play); // 없다면 그냥
			}
		}

		Collections.sort(list, new Comparator<Song>() {
			@Override
			public int compare(Song o1, Song o2) {
				if (o1.genre.equals(o2.genre)) {
					return o1.compareTo(o2);
				} else {
					return genreMap.get(o2.genre) - genreMap.get(o1.genre); // 장르가 다르면 많이 재생된
				}
			}
		});
		for (Song song : list) {
			if (!albumMap.containsKey(song.genre)) { // 앨범에 저장
				albumMap.put(song.genre, 1);
				best.add(song.id); // 베스트 앨범에 저장
			} else {
				if (albumMap.get(song.genre)>1) {
					continue;
				}
				else {
					albumMap.put(song.genre, 2);
					best.add(song.id);
				}
			}
		}
		int[] answer = new int[best.size()];
		for (int i = 0; i < best.size(); i++) {
			answer[i] = best.get(i);
		}
		return answer;
	}

	static class Song implements Comparable<Song> {
		int id;
		int play;
		String genre;

		public Song(int id, int play, String genre) {
			this.id = id;
			this.play = play;
			this.genre = genre;
		}

		@Override
		public int compareTo(Song o) {
			if (this.play == o.play) { // 재생횟수가 같다면
				return this.id - o.id; // 고유번호 순서대로
			} else {
				return o.play - this.play; // 많은 것부터 쌓아야됨
			}
		}
	}
}
