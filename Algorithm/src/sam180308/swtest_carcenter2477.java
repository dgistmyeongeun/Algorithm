package sam180308;

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

	public static int find() {
		int ans = 0;
		boolean check = false;

		for (Person p : ti) {
			if (p.getReceptionNum() == a && p.getRepairNum() == b) {
				ans += p.getNumber() + 1;
				check = true;
			}
		}
		if (check)
			return ans;
		else
			return -1;
	}

	public static void repair() {
		PriorityQueue<Person> repairPQue = new PriorityQueue<>();
		Person p1 = repairWaitQue.poll();
		p1.setRepairNum(0);
		p1.setCalTime(p1.getArrive() + bi[repairque.poll()]);
		repairPQue.offer(p1);
		int nowtime = repairPQue.peek().calTime;

		while (!repairWaitQue.isEmpty()) {

			while (!repairWaitQue.isEmpty() && nowtime > repairWaitQue.peek().arrive  && !repairque.isEmpty()) {
				Person nowp = repairWaitQue.poll();
				int numRepair = repairque.poll();
				nowp.setRepairNum(numRepair);
				nowp.setCalTime(bi[numRepair] + nowp.getArrive());
				repairPQue.offer(nowp);
				nowtime = repairPQue.peek().calTime;
			}

			if (repairWaitQue.isEmpty() && !repairPQue.isEmpty()) {
				while (!repairPQue.isEmpty()) {
					Person nextp = repairPQue.poll();
				}
				return;
			}

			Person nextp = repairPQue.poll();
			repairque.add(nextp.getRepairNum());

			if (repairPQue.isEmpty())
				nowtime = Integer.MAX_VALUE;
			else
				nowtime = repairPQue.peek().calTime;

		}

	}

	public static void repairWait(Person person) {
		repairWaitQue.offer(person);
	}

	public static void reception() {
		PriorityQueue<Person> receptionPQue = new PriorityQueue<Person>();
		int realTime = 0;
		Person p1 = ti[0];
		p1.setReceptionNum(0);
		p1.setCalTime(p1.getArrive() + ai[receptionque.poll()]);
		receptionPQue.offer(p1);
		int nowtime = receptionPQue.peek().calTime;

		int i = 1;
		while (i < k) {
			while (i < k && nowtime > ti[i].arrive  && !receptionque.isEmpty()) {
				Person nowp = ti[i];
				int numRecept = receptionque.poll();
				nowp.setReceptionNum(numRecept);
				nowp.setCalTime(ai[numRecept] + nowp.getArrive() + nowp.getWaiting());
				receptionPQue.offer(nowp);
				nowtime = receptionPQue.peek().calTime;
				i++;
			}

			if (i == k && !receptionPQue.isEmpty()) {
				while (!receptionPQue.isEmpty()) {
					Person nextp = receptionPQue.poll();
					nextp.setArrive(ai[nextp.getReceptionNum()] + nextp.getArrive() + nextp.getWaiting());
					repairWait(nextp);
				}
				break;
			}

			Person nextp = receptionPQue.poll();
			realTime = nextp.getCalTime();
			ti[i].setWaiting(realTime - ti[i].getArrive());
			nextp.setArrive(ai[nextp.getReceptionNum()] + nextp.getArrive() + nextp.getWaiting());
			repairWait(nextp);
			receptionque.offer(nextp.getReceptionNum());

			if (receptionPQue.isEmpty() || receptionque.size()==n)
				nowtime = Integer.MAX_VALUE;
			else
				nowtime = receptionPQue.peek().calTime;

		}
		repair();

	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		tc = sc.nextInt();
		int testcase = 1;
		while (tc-- > 0) {
			n = sc.nextInt();
			m = sc.nextInt();
			k = sc.nextInt();
			a = sc.nextInt() - 1;
			b = sc.nextInt() - 1;
			rp = 0;

			sc.nextLine();
			ai = new int[n];
			bi = new int[m];
			ti = new Person[k];
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
				repairque.add(i);
			}

			line = sc.nextLine().split(" ");
			for (int i = 0; i < k; i++) {
				int temp = Integer.parseInt(line[i]);
				Person p = new Person(i, temp);
				ti[i] = p;
			}

			reception();
			System.out.println("#" + (testcase++) + " " + find());
		}
	}
}

class Person implements Comparable<Person> {
	int number;
	int arrive;
	int receptionNum;
	int RepairNum;
	int calTime;
	int waiting;

	public Person(int number, int arrive) {
		this.number = number;
		this.arrive = arrive;
		this.waiting = 0;
	}

	public int getWaiting() {
		return waiting;
	}

	public void setWaiting(int waiting) {
		this.waiting = waiting;
	}

	public void setArrive(int arrive) {
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