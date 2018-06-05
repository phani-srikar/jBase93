import java.util.*;
public class Fibonacci{
   HashMap<Integer,Integer> map = new HashMap<Integer,Integer>();
   public int fibonacci(int n){
      if(map.containsKey(n))
         return map.get(n);
      else{
         if(n<=1)
            map.put(n,n);
         else map.put(n,fibonacci(n-1) + fibonacci(n-2));
         return map.get(n);
      }
   }
   
   public static void main(String[] args){
      Fibonacci fb = new Fibonacci();
      System.out.println(fb.fibonacci(9));
   }
}