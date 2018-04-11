package baekPreTest;

import java.awt.Point;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Snake {

	private static int N, K, L, time;
	private static ArrayList<Point> apple;
	private static Queue<Direction> direction;
	private static int[][] map;
	private static int[][] dir = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };
	private static LinkedList<Point> snake;

	private static void go(int r, int c, int d) {
		if (r == 0 || c == 0 || r >= N + 1 || c >= N + 1) {
			return;
		}
		printArr();
		int nextR = r + dir[d][0];
		int nextC = c + dir[d][1];

		time++;
		if (!chkidx(nextR, nextC))
			return;

		if (time == direction.peek().sec) {
			Direction order = direction.poll();
			if(order.dir=='L') {
				d = (d+3)%4;
			}else {
				d = (d+1)%4;
			}
		}
		
		if (map[nextR][nextC] == 1) { // 사과가 있으면
			snake.addFirst(new Point(nextR, nextC));
			map[nextR][nextC] = 2;
			go(nextR, nextC, d);
		} else {
			Point rear = snake.getLast();
			snake.removeLast();
			snake.addFirst(new Point(nextR, nextC));
			map[nextR][nextC] = 2;
			map[rear.x][rear.y] = 0;
			go(nextR, nextC, d);
		}

	}

	private static boolean chkidx(int r, int c) {
		if (r <= 0 || c <= 0 || r > N || c > N || map[r][c] == 2)
			return false;
		return true;
	}

	private static void printArr() {
		System.out.println("-----------------------");
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				System.out.print(map[i][j]);
			}
			System.out.println();
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		K = sc.nextInt();
		map = new int[N + 2][N + 2];
		apple = new ArrayList<>();
		direction = new LinkedList<>();
		snake = new LinkedList<>();

		for (int i = 0; i < K; i++) {
			int row = sc.nextInt();
			int col = sc.nextInt();
			apple.add(new Point(row, col));
			map[row][col] = 1;
		}

		L = sc.nextInt();
		for (int i = 0; i < L; i++) {
			int sec = sc.nextInt();
			char dir = sc.next().charAt(0);
			direction.add(new Direction(sec, dir));
		}
		snake.offer(new Point(1, 1));
		time = 0;
		go(1, 1, 0);
		System.out.println(time);
	}

}

class Direction {
	int sec;
	char dir;

	public Direction(int sec, char dir) {
		this.sec = sec;
		this.dir = dir;
	}

}