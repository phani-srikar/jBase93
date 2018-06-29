package jBase93;
import java.util.*;

public class PrimeFactors{
   public Vector<Integer> primeFactors(int a, Vector<Integer> vec){
      for(int i=a/2; i>=1; i--){
         if(a%i==0){
            vec.addElement(a/i);
            primeFactors(i, vec);
            break;
         }
      }
      return vec;
   }
   
   public static void main(String[] args){
      Vector<Integer> vec = new Vector<Integer>();
      int a = 17;
      PrimeFactors pf = new PrimeFactors();
      System.out.println(pf.primeFactors(a,vec));
   }
}