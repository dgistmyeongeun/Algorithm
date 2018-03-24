package sam180308;

import java.io.*;
import java.util.*;

class People{
	int no;
	int cept = -1;
	int pair = -1;
	
	public People(int no) {
		this.no = no;
	}
	
	public void chk(int i) {
		if(cept == -1)
			this.cept = i;
		else if(pair == -1)
			this.pair = i;
	}
}

class Space{
	int spaceSize;
	ArrayList<Integer> wList;
	Queue<People> inque;
	Queue<People> outque;
	
	int[] state;
	People[] seat;
	
	public Space(ArrayList<Integer> wList, Queue<People> inque, Queue<People> outque) {
		this.spaceSize = wList.size();
		
		this.wList = wList;
		this.inque = inque;
		this.outque = outque;
		
		state = new int[spaceSize];
		seat = new People[spaceSize];
		
		for(int i=0; i<spaceSize; i++) {
			state[i] = wList.get(i);
		}
	}
	
	public void goingTime() {
		for(int i=0; i<spaceSize; i++) {
			if(seat[i] != null && state[i] != 0)
				state[i]--;
		}
	}
	
	public void Out() {
		for(int i=0; i<spaceSize; i++) {
			if(seat[i]!=null && state[i] == 0) {
				outque.offer(seat[i]);
				seat[i]=null;
				state[i] = wList.get(i);
			}
		}
	}
	
	public void In() {
		for(int i=0; i<spaceSize; i++) {
			if(!inque.isEmpty() && state[i] == wList.get(i)) {
				seat[i] = inque.poll();
				seat[i].chk(i+1);
			}
		}
	}
	
}

class swtest_carcenter_ans {
	static int T;
	
	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int TestSize = Integer.parseInt(br.readLine());
		for (T = 1; T <= TestSize; T++) {

			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
		
			st = new StringTokenizer(br.readLine());
			ArrayList<Integer> ceptList = new ArrayList<>();
			for(int i=0; i<N; i++) 
				ceptList.add(Integer.parseInt(st.nextToken()));
			
			st = new StringTokenizer(br.readLine());
			ArrayList<Integer> pairList = new ArrayList<>();
			for(int i=0; i<M; i++)
				pairList.add(Integer.parseInt(st.nextToken()));
			
			st = new StringTokenizer(br.readLine());
			ArrayList<Integer> peoList = new ArrayList<>();
			for(int i=0; i<K; i++)
				peoList.add(Integer.parseInt(st.nextToken()));
			
			
			Queue<People> Robby1 = new LinkedList<>();
			Queue<People> Robby2 = new LinkedList<>();
			Queue<People> Robby3 = new LinkedList<>();
			
			Space ceptSpace = new Space(ceptList, Robby1, Robby2);
			Space pairSpace = new Space(pairList, Robby2, Robby3);
			
			boolean isend = false;
			int timer = -1;
			
			while(true) {
				timer++;
				
				for(int i=0; i<K; i++) {
					if(peoList.get(i) == timer) {
						Robby1.offer(new People(i+1));
					}
				}
				
				ceptSpace.goingTime();
				pairSpace.goingTime();
				
				ceptSpace.Out();
				pairSpace.Out();
				
				ceptSpace.In();
				pairSpace.In();
				
				if(Robby3.size() == K)
					break;
			}
			
			int ans=0;
			People person;
			
			while(!Robby3.isEmpty()) {
				person = Robby3.poll();
				
				if(person.cept == A && person.pair == B)
					ans += person.no;
			}
			
			System.out.println(String.format("#%d %d", T, ans>0?ans:-1));
			
		}		
	}

}