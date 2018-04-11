package swExpertExam;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

class Space {
	int spaceSize;
	Queue<Integer> inque;
	Queue<Integer> outque;
	int[] state;
	int[] seat;

	public Space(int spaceSize, Queue<Integer> inque, Queue<Integer> outque) {
		this.spaceSize = spaceSize;
		this.inque = inque;
		this.outque = outque;
		state = new int[3];
		seat = new int[3];

		for (int i = 0; i < 3; i++)
			state[i] = spaceSize;
	}

	public void goingTime() {
		for (int i = 0; i < 3; i++) {
			if (seat[i] != 0 && state[i] != 0)
				state[i]--;
		}
	}

	public void Out() {
		for (int i = 0; i < 3; i++) {
			if (seat[i] != 0 && state[i] == 0) {
				outque.offer(seat[i]);
				seat[i] = 0;
				state[i] = spaceSize;
			}
		}
	}

	public void In() {
		for (int i = 0; i < 3; i++) {
			if (!inque.isEmpty() && state[i] == spaceSize) {
				seat[i] = inque.poll();
			}
		}
	}
}

public class Swtest_lanchtime2383 {

	private static int N, minTime;
	private static int[][] arr;
	private static Point[] stair = new Point[2];
	private static int[] stairVal = new int[2];

	private static int goDown(ArrayList<Integer> frist, ArrayList<Integer> second) {

		Queue<Integer> fque = new LinkedList<>();
		Queue<Integer> sque = new LinkedList<>();
		Queue<Integer> finish = new LinkedList<>();
		Queue<Integer> finish2 = new LinkedList<>();
		Space fristStair = new Space(stairVal[0], fque, finish);
		Space secondStair = new Space(stairVal[1], sque, finish2);

		int time=0;
		if (!frist.isEmpty()) {
			time = frist.get(0);

			while (true) {
				for (int i = 0; i < frist.size(); i++) {
					if (frist.get(i) == time) {
						fque.offer(i + 1);
					}
				}

				fristStair.goingTime();
				fristStair.Out();
				fristStair.In();

				if (finish.size() == frist.size())
					break;
				time++;
			}
		}

		int time2=0;
		if (!second.isEmpty()) {
			time2 = second.get(0);

			while (true) {

				for (int i = 0; i < second.size(); i++) {
					if (second.get(i) == time2) {
						sque.offer(i + 1);
					}
				}

				secondStair.goingTime();
				secondStair.Out();
				secondStair.In();

				if (finish2.size() == second.size())
					break;
				time2++;
			}
		}
		return time > time2 ? time : time2;
	}

	private static void StairFind(List<Point> person) {
		int psize = person.size();
		int pmax = 1 << psize;
		ArrayList<Integer> frist;
		ArrayList<Integer> second;
		int tmp;

		for (int i = 0; i < pmax; i++) {
			frist = new ArrayList<>();
			second = new ArrayList<>();
			// System.out.println(i+"===========");
			for (int j = 0; j < psize; j++) {
				if ((i & (1 << j)) != 0) { // 1¹ø °è´Ü
					int diff = Math.abs(person.get(j).x - stair[0].x) + Math.abs(person.get(j).y - stair[0].y);
					frist.add(diff);
				} else {
					int diff = Math.abs(person.get(j).x - stair[1].x) + Math.abs(person.get(j).y - stair[1].y);
					second.add(diff);
				}
			}
			Collections.sort(frist);
			Collections.sort(second);
			tmp = goDown(frist, second);
			if (tmp < minTime)
				minTime = tmp;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int TestCase = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= TestCase; tc++) {
			N = Integer.parseInt(br.readLine());
			arr = new int[N][N];
			int s = 0;
			List<Point> person = new LinkedList<>();
			minTime = Integer.MAX_VALUE;

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());

					if (arr[i][j] == 1)
						person.add(new Point(i, j));
					else if (arr[i][j] >= 2) {
						stairVal[s] = arr[i][j];
						stair[s++] = new Point(i, j);
					}
				}
			}

			StairFind(person);
			System.out.println("#"+tc+" "+(minTime+1));
		}
	}
}
