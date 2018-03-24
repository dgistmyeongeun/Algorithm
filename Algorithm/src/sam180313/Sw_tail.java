package sam180313;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Sw_tail {

   public static int M, cnt;
   public static int twopow[] = new int[32];
   public static List<Integer> list;
   public static ArrayList<Square> square;

   public static void Cutting(int len) {
      for (int i = 0; i < square.size(); i++) {
         if (square.get(i).x >= len && square.get(i).y >= len) {
            //System.out.println("µÎ¹øÂ°" + square.get(i).x+"////"+ square.get(i).y+" "+len);
            Square s = square.get(i);
            square.remove(i);
            
            if(s.x == len && s.y == len)
            	return;
            
            if (s.x - len != 0 )
               square.add(new Square(s.x - len, s.y));
            else if (s.x - len == 0 && s.y - len != 0) {
               square.add(new Square(s.x, s.y - len));
               return;
            }

            if (s.y - len != 0)
               square.add(new Square(len, s.y - len));
            else if (s.y - len == 0 && s.x - len != 0) {
               square.add(new Square(s.x - len, s.y));
               return;
            }
            return;
         }
      }

      cnt++;
     /* System.out.println(len);
      for (int i = 0; i < square.size(); i++) {
         System.out.println(square.get(i).toString());
      }*/
      square.add(new Square(M, M));
      
      Cutting(len);

   }

   public static void main(String[] args) throws IOException{
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      StringTokenizer st;
      int TestCase = Integer.parseInt(br.readLine());

      for (int i = 0; i <= 31; i++)
         twopow[i] = (int) Math.pow(2, i);
      for (int tc = 1; tc <= TestCase; tc++) {
         String[] ts = br.readLine().split(" ");
         int N = Integer.parseInt(ts[0]);
         M = Integer.parseInt(ts[1]);
         int[] arr = new int[N];
         cnt = 1;
         
         st = new StringTokenizer(br.readLine());
         for (int i = 0; i < N; i++) {
            arr[i] = twopow[Integer.parseInt(st.nextToken())];
         }
         Arrays.sort(arr);
         square = new ArrayList<>();
         square.add(new Square(M, M));
         for (int k = arr.length - 1; k >= 0; k--) {
            Cutting(arr[k]);
         }
         System.out.println("#" + tc + " " + cnt);
      }
   }

}

class Square implements Comparable<Square> {
   int x;
   int y;
   int s;

   public Square(int x, int y) {
      this.x = x;
      this.y = y;
      this.s = x * y;
   }

   @Override
   public int compareTo(Square o) {
      if (s < o.s)
         return -1;
      else if (s > o.s)
         return 1;
      else
         return 0;
   }

   @Override
   public String toString() {
      return ">>>>" + x + ", " + y + ", " + s;
   }

}