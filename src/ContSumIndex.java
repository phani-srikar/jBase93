// https://www.programcreek.com/2013/02/leetcode-maximum-subarray-java/
public class ContSumIndex{
   public int contSumIndex(int[] a, int n){
      int max = a[0];
      int curr_max = a[0];
      int start = -1, end = -1;
      boolean flag = false;
      for(int i=1; i<n; i++){
         flag = false;
         if(a[i] + curr_max < a[i]){
            curr_max = a[i];
            flag = true;
         }
         else curr_max = a[i] + curr_max;
         if(curr_max > max){
            if(flag)
               start = i;
            else end = i;
            max = curr_max;
         }
      }
      System.out.print(start);
      System.out.print(" to ");
      System.out.println(end);
      return max;
   }
   
   public static void main(String args[]){
      int[] arr = new int[]{-2,1,-3,4,-1,2,1,-5,4};
      ContSumIndex csi = new ContSumIndex();
      System.out.println(csi.contSumIndex(arr, arr.length));
   }
}