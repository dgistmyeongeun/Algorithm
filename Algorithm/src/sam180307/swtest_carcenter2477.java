package sam180307;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

public class swtest_carcenter2477 {
	static int n, m, k, a, b, tc, rp;
	static int[] ai, bi;
	static Person[] ti;
	static PriorityQueue<Integer> receptionque;
	static PriorityQueue<Integer> repairque;
	static Queue<Person> repairWaitQue = new LinkedList<>();
	
	public static void repair() {
		
		while(!repairWaitQue.isEmpty()) {
			while(!repairque.isEmpty()) {
				Person repairP = repairWaitQue.poll();
				repairP.setRepairNum(repairque.poll());
				repairP.setCalTime(bi[repairP.getNumber()]);
			}
			
			
		}
	}
	public static void repairWait(Person person) {
		System.out.println("#" + (tc) + "::::::" + person.getNumber());
		repairWaitQue.offer(person);
	}

	public static void reception() {
		PriorityQueue<Person> receptionPQue = new PriorityQueue<Person>();
		Person p1 = ti[0];
		p1.setReceptionNum(0);
		p1.setCalTime(p1.getArrive() + ai[receptionque.poll()]);
		receptionPQue.offer(p1);
		int nowtime = receptionPQue.peek().calTime;

		int i = 1;
		while (i < k) {
			while (i < k && nowtime > ti[i].arrive && !receptionque.isEmpty()) {
				Person nowp = ti[i];
				int numRecept = receptionque.poll();
				nowp.setReceptionNum(numRecept);
				nowp.setCalTime(ai[numRecept] + nowp.getArrive());
				receptionPQue.offer(nowp);
				nowtime = receptionPQue.peek().calTime;
				i++;
			}

			if (i == k && !receptionPQue.isEmpty()) {
				while (!receptionPQue.isEmpty()) {
					Person nextp = receptionPQue.poll();
					repairWait(nextp);
				}
				continue;
			}

			Person nextp = receptionPQue.poll();
			repairWait(nextp);
			receptionque.offer(nextp.getReceptionNum());

			if (receptionPQue.isEmpty())
				nowtime = Integer.MAX_VALUE;
			else
				nowtime = receptionPQue.peek().calTime;

		}
		repair();

	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		tc = sc.nextInt();

		while (tc-- > 0) {
			n = sc.nextInt();
			m = sc.nextInt();
			k = sc.nextInt();
			a = sc.nextInt();
			b = sc.nextInt();
			rp = 0;
			
			sc.nextLine();
			ai = new int[n];
			bi = new int[m];
			ti = new Person[k + 1];
			receptionque = new PriorityQueue<>();
			repairque = new PriorityQueue<>();

			String[] line = sc.nextLine().split(" ");
			for (int i = 0; i < n; i++) {
				ai[i] = Integer.parseInt(line[i]);
				receptionque.add(i);
			}

			line = sc.nextLine().split(" ");
			for (int i = 0; i < m; i++) {
				bi[i] = Integer.parseInt(line[i]);
				repairque.offer(i);
			}

			line = sc.nextLine().split(" ");
			for (int i = 0; i < k; i++) {
				int temp = Integer.parseInt(line[i]);
				Person p = new Person(i, temp);
				ti[i] = p;
			}

			reception();

		}
	}
}

class Person implements Comparable<Person> {
	int number;
	int arrive;
	int receptionNum;
	int RepairNum;
	int calTime;

	public Person(int number, int arrive) {
		this.number = number;
		this.arrive = arrive;
	}

	public int getReceptionNum() {
		return receptionNum;
	}

	public void setReceptionNum(int receptionNum) {
		this.receptionNum = receptionNum;
	}

	public int getRepairNum() {
		return RepairNum;
	}

	public void setRepairNum(int repairNum) {
		RepairNum = repairNum;
	}

	public int getCalTime() {
		return calTime;
	}

	public void setCalTime(int calTime) {
		this.calTime = calTime;
	}

	public int getNumber() {
		return number;
	}

	public int getArrive() {
		return arrive;
	}

	@Override
	public int compareTo(Person target) {
		if (this.calTime > target.calTime)
			return 1;
		else if (this.calTime < target.calTime)
			return -1;
		return 0;
	}

}