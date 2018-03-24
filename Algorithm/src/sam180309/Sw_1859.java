package sam180309;

import java.util.Scanner;

public class Sw_1859 {
   
   
   public static void main(String[] args) {
      Scanner sc = new Scanner(System.in);
      int TestCase = sc.nextInt();
      for(int tc=1; tc<=TestCase; tc++) {
         int day = sc.nextInt();
         int arr[] = new int[day];
         
         sc.nextLine();
         String temp[] = sc.nextLine().split(" ");
         for(int i=0; i<day; i++) {
            arr[i] = Integer.parseInt(temp[i]);
         }
      
         int i=1;
         int now = arr[0];
         int next ;
         long buy=0;
         long sale=0;
         long cnt = 0;
         long tsale=0;
         long ttsale=0;
         long tcnt=0;
         long tbuy=0;
         long ans=0;
         
         while(i<day) {
            next = arr[i++];
            if(now<=next) {
               buy += now;
               tbuy += now;
               cnt++;
               tcnt++;
               System.out.println(buy+":::"+tbuy+"::::"+cnt+":::"+tcnt);
            }else if(now>next) {
               cnt++;
               tcnt++;
               System.out.println(now+"°öÇÒ°Å¾ß");
               sale = now*cnt-buy;
               ttsale = tsale + (now*tcnt-tbuy);
               
               System.out.println(tc+":::"+sale+">>>"+ttsale);
               if(ttsale <= sale) {
                  ans += sale-tsale;
                  tsale = sale;
                  tcnt = 0;
                  tbuy=0;
               }else if(ttsale > sale) {
                  ans += next*tcnt-tbuy;
                  cnt=0;
                  buy=0;
               }
               
               
            }

            if(i==day&&now<=next) {
               sale = next*cnt-buy;
               ttsale = tsale + (next*tcnt-tbuy);
               if(ttsale <= sale) {
                  ans += sale-tsale;
               }else if(ttsale > sale) {
                  ans += next*tcnt-tbuy;
               }
               break;
            }
            now = next;
         }
         System.out.println("#"+ tc + " " + ans);
      }
   
   }
}