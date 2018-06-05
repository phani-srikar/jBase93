public class kAryStrings{
   public void printkAryStrings(String s, String prefix, int k){
      if(k==0){
         System.out.println(prefix);
         return;
      }
      for(int i=0; i<s.length(); i++){
         String newprefix = prefix + s.charAt(i);
         printkAryStrings(s, newprefix, k-1);
      }
   }
   
   public static void main(String[] args){
      String test = "abc";
      kAryStrings ks = new kAryStrings();
      ks.printkAryStrings(test,"",2);
   }
}