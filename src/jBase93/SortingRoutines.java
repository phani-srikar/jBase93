package jBase93;

public class SortingRoutines{
   public int[] insertionSort(int[] arr, int n){
      for(int i=1; i<n; i++){
         int key = arr[i];
         int j;
         for(j=i-1; j>=0; j--){
            if(key < arr[j])
               arr[j+1] = arr[j];
            else{
               break;
            }
         }
         arr[j+1] = key;
      }
      return arr;
   }
   
   private void merge(int[] arr, int p, int q, int r){
      int n1 = q-p+1;
      int n2 = r-q;
      int[] L = new int[n1];
      for(int i=0; i<n1; i++)
         L[i] = arr[i+p];
      int[] R = new int[n2];
      for(int i=0; i<n2; i++)
         R[i] = arr[i+q+1];
      int i=0,j=0,k=p;
      while(i<n1 && j<n2){
         if(L[i]<=R[j]){
            arr[k] = L[i];
            i++;
         }
         else{
            arr[k] = R[j];
            j++;
         }
         k++;
      }
      
      while(i<n1){
         arr[k] = L[i];
         i++;
         k++;
      }
      
      while(j<n2){
         arr[k] = R[j];
         j++;
         k++;
      }   
   }
   
   public void mergeSort(int[] arr, int p, int r){
      if(p<r){
         int q = (p+r)/2;
         mergeSort(arr,p,q);
         mergeSort(arr,q+1,r);
         merge(arr,p,q,r);
      }
   }
   
   public static void main(String[] args){
      int[] test = new int[]{2,1,5,4,3};
      SortingRoutines sr = new SortingRoutines();
      int[] sorted;
      //sorted = sr.insertionSort(test,test.length);
      sorted = test;
      sr.mergeSort(sorted,0,test.length-1);
      for(int i=0;i<sorted.length;i++){
         System.out.println(sorted[i]);
      }
   }
}