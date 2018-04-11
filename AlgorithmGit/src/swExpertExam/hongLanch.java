package swExpertExam;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class hongLanch {
	
	static int T,N;
	static int arr[][];
	static int min =Integer.MAX_VALUE;
	static int personCnt =0;
	static int stairCnt = 0;
	static int stairArr[][];
	
	static ArrayList<Person> total;
	static PriorityQueue<Person> q;
	static Queue<Person> firstStair;
	static Queue<Person> firstTmp;
	static Queue<Person> secondStair;
	static Queue<Person> secondTmp;
	static Queue<Person> finish;
	
	public static void main(String[]args) throws NumberFormatException, IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		T = Integer.parseInt(br.readLine());

		for(int t=1;t<=T;t++){
			N= Integer.parseInt(br.readLine());
			arr = new int[N][N];
			stairArr = new int[2][3];
			total = new ArrayList<Person>();
			q = new PriorityQueue<Person>();
			firstStair = new LinkedList<Person>();
			secondStair = new LinkedList<Person>();
			firstTmp = new LinkedList<Person>();
			secondTmp = new LinkedList<Person>();
			secondStair = new LinkedList<Person>();
			finish = new LinkedList<Person>();

			for(int i=0;i<N;i++){
				st= new StringTokenizer(br.readLine());

				for(int j=0;j<N;j++){
					arr[i][j] = Integer.parseInt(st.nextToken());
					if(arr[i][j]==1){
						total.add(new Person(i,j,(personCnt+1)));
						personCnt ++;
					}
					if(arr[i][j]>=2){
						stairArr[stairCnt][0]=i;
						stairArr[stairCnt][1]=j;
						stairArr[stairCnt][2] = arr[i][j];
						stairCnt++;
					}
				}
			}

			solve();

			System.out.println("#"+t+" "+min);
			min =Integer.MAX_VALUE;
			stairCnt =0;
			personCnt =0;
		}
	}
	public static void solve(){
		for(int i=0;i<(1<<personCnt);i++){
			for(int j=0;j<personCnt;j++){
				if((i & (1<<j))>0){
					total.get(j).stair =1;
				}
				else{
					total.get(j).stair =2;
				}
			}

			//도착 시간 계산 
			for(int k=0;k<total.size();k++){
				if(total.get(k).stair == 1){
					total.get(k).moveTime=Math.abs(total.get(k).y-stairArr[0][0])+Math.abs(total.get(k).x-stairArr[0][1]);
					total.get(k).stairTime = stairArr[0][2];
					q.add(total.get(k));
				}
				else{
					total.get(k).moveTime=Math.abs(total.get(k).y-stairArr[1][0])+Math.abs(total.get(k).x-stairArr[1][1]);
					total.get(k).stairTime = stairArr[1][2];
					q.add(total.get(k));
				}
			}

			int time = 0;
			
			while(finish.size()!=personCnt){
				
				if(!firstStair.isEmpty()){
					int size = firstStair.size();
					for(int k=0;k<size;k++){
						Person tmp = firstStair.poll();
						tmp.stairTime -= 1;
						if(tmp.stairTime ==0){
							finish.add(tmp);
							if(!firstTmp.isEmpty()){
								firstStair.add(firstTmp.poll());
							}
						}
						else{
							firstStair.add(tmp);
						}
					}
				}
	
				if(!secondStair.isEmpty()){
					int size = secondStair.size();
					for(int k=0;k<size;k++){
						Person tmp = secondStair.poll();
						tmp.stairTime -= 1;
						if(tmp.stairTime ==0){
							finish.add(tmp);
							if(!secondTmp.isEmpty()){
								secondStair.add(secondTmp.poll());
							}
						}
						else{
							secondStair.add(tmp);
						}
					}
				}
	
				if(!q.isEmpty()){
					while(!q.isEmpty() && q.peek().moveTime==time){
						Person tmp = q.poll();
						if(tmp.stair == 1){ //1번 계단으로
							if(firstStair.size()<3){
								firstStair.add(tmp);
							}
							else{
								firstTmp.add(tmp);
							}
						}
						else{ // 2번 계단으로 
							if(secondStair.size()<3){
								secondStair.add(tmp);
							}
							else{
								secondTmp.add(tmp);
							}
						}
					}
				}
				time++;
			}
			if(min>time){
				min = time;
			}
			finish.clear();
		}
	}
}
class Person implements Comparable<Person>{
	
	int num;
	int y;
	int x;
	int stair;
	int moveTime;
	int stairTime;
	
	Person(int y, int x, int num){
		this.y = y;
		this.x = x;
		this.num = num;
	}

	@Override
	public int compareTo(Person o) {
		// TODO Auto-generated method stub
		if(this.moveTime >= o.moveTime){
			return 1;
		}
		else{
			return -1;
		}
	}
}