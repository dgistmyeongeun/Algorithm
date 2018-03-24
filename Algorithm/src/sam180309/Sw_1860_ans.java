package sam180309;

import java.util.Scanner;

public class Sw_1860_ans {
   static int day;
   static int arr[];
   public static long find(int sidx) {
	   long sale = 0;
	   int maxidx = sidx;
	   
	   for(int i=sidx; i<day; i++) {
		   if(arr[i] > arr[maxidx])
			   maxidx = i;
	   }

	   for(int i=sidx; i<maxidx; i++) {
		   sale += arr[maxidx]-arr[i];
	   }
	   
	   int checkEnd = (day-1) - maxidx;
	   if(checkEnd>=0) {
		   sale += find(maxidx+1);
	   }
	   return sale;
   }
   
   public static void main(String[] args) {
      Scanner sc = new Scanner(System.in);
      int TestCase = sc.nextInt();
      
      for(int tc=1; tc<=TestCase; tc++) {
         day = sc.nextInt();
         arr = new int[day];
         long ans=0;
         
         sc.nextLine();
         String temp[] = sc.nextLine().split(" ");
         for(int i=0; i<day; i++) {
            arr[i] = Integer.parseInt(temp[i]);
         }
      
         ans = find(0);
         
         System.out.println("#"+ tc + " " + ans);
      }
   
   }
}