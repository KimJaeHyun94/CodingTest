package M0421;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 후보추천하기 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());

		PriorityQueue<Student> pq = new PriorityQueue<>();
		List<Student> list = new ArrayList<>();
		StringTokenizer st = new StringTokenizer(br.readLine());
		 int order = 1;
		for (int i = 0; i < M; i++) {
			
			int n = Integer.parseInt(st.nextToken());

			if (!pq.contains(new Student(n, 0, 0))) {
				if (pq.size() < N) {
					pq.add(new Student(n, 1, order++));
				} else {
					pq.poll();
					pq.add(new Student(n, 1, order++));
				}
			} else {
				Student[] temp = new Student[pq.size()];
				for (int s = 0; s < temp.length; s++) {
					temp[s] = pq.poll();
				}
				pq.clear();
				for (Student s : temp) {
					if (s.n == n) {
						s.c++;
					}
					pq.offer(s);
				}
			}
		}

		Student[] result = new Student[N];
		int idx = 0;
		while (!pq.isEmpty()) {
			result[idx++] = pq.poll();
		}
		Arrays.sort(result, new Comparator<Student>() {

			@Override
			public int compare(Student s1, Student s2) {
				return s1.n - s2.n;
			}
		});
		StringBuilder sb = new StringBuilder();
		for (Student s : result) {
			sb.append(s.n + " ");
		}
		System.out.println(sb);
	}

	static class Student implements Comparable<Student> {
		int n;
		int c;
		int o;

		public Student(int n, int c, int o) {
			this.n = n;
			this.c = c;
			this.o = o;
		}

		@Override
		public boolean equals(Object o) {
			if (this == o)
				return true;
			if (o == null || getClass() != o.getClass())
				return false;
			Student student = (Student) o;
			return n == student.n;
		}

		@Override
		public int hashCode() {
			return Objects.hash(n);
		}

		@Override
		public int compareTo(Student o) {
			if (this.c != o.c) {
				return this.c - o.c;
			} else {
				return this.o - o.o;
			}
		}

	}
}
