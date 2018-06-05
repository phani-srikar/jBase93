// https://leetcode.com/problems/longest-palindromic-substring/solution/
public class LongestPalindrome {
   public String longestPalindrome(String s){
      int n = s.length();
      int i,j;
      String longest = "";
      boolean[][] matrix = new boolean[n][n]; // defaults to false
      for(i=n-1;i>=0;i--){
         for(j=i;j<n;j++){
            if(i==j || ((s.charAt(i)==s.charAt(j)) && (matrix[i+1][j-1] || j-i<2))){
               matrix[i][j] = true;
               if(longest.length() <= j-i+1){
               longest = s.substring(i,j+1);
               }
            }            
         }
      }
      return longest;
   }
   
   public static void main(String[] args){
      String test = "babad";
      LongestPalindrome lp = new LongestPalindrome();
      System.out.println(lp.longestPalindrome(test));
   }
}
