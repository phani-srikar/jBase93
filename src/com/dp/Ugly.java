package com.dp;

// https://www.geeksforgeeks.org/ugly-numbers/

/*	Ugly numbers are numbers whose only prime factors are 2, 3 or 5. 
 * 	The sequence 1, 2, 3, 4, 5, 6, 8, 9, 10, 12, 15
 * 	… shows the first 11 ugly numbers. By convention, 1 is included.
 * 
 */

public class Ugly{
   public int ugly(int n){
      int[] arr = new int[n];
      int i2=0,i3=0,i5=0;
      arr[0]=1;
      int mult_2 = arr[i2]*2;
      int mult_3 = arr[i3]*3;
      int mult_5 = arr[i5]*5;
      for(int i=1; i<n; i++){
         arr[i] = Math.min(Math.min(mult_5,mult_3),mult_2);
         if(arr[i] == mult_2){
            i2 = i2+1;
            mult_2 = arr[i2]*2;
         }
         if(arr[i] == mult_3){
            i3 = i3+1;
            mult_3 = arr[i3]*3;
         }
         if(arr[i] == mult_5){
            i5 = i5+1;
            mult_5 = arr[i5]*5;
         }
         System.out.println(arr[i]);
      }
      return arr[n-1];
   }
   
   public static void main(String[] args){
      Ugly u = new Ugly();
      //System.out.println(u.ugly(6));
      u.ugly(7);
   }
}