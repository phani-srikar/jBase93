package jBase93;

/* Longest Common SubSequence
The input sequences be X[0..m-1] and Y[0..n-1] of lengths m and n respectively. 
And let L(X[0..m-1], Y[0..n-1]) be the length of LCS of the two sequences X and Y. 
Following is the recursive definition of L(X[0..m-1], Y[0..n-1]).
If last characters of both sequences match (or X[m-1] == Y[n-1]) then
L(X[0..m-1], Y[0..n-1]) = 1 + L(X[0..m-2], Y[0..n-2])
If last characters of both sequences do not match (or X[m-1] != Y[n-1]) then
L(X[0..m-1], Y[0..n-1]) = MAX ( L(X[0..m-2], Y[0..n-1]), L(X[0..m-1], Y[0..n-2])*/

public class LongestCommonSubSeqLength{
   public int longestSubSeqLen(String s1, String s2, int m, int n){
      if(m==0 || n==0)
         return 0;
      if(s1.charAt(m-1) == s2.charAt(n-1))
         return 1+longestSubSeqLen(s1,s2,m-1,n-1);
      else{
         return Math.max(longestSubSeqLen(s1,s2,m-1,n),longestSubSeqLen(s1,s2,m,n-1));
      }
   }
   
   public static void main(String[] args){
      String test1 = "AGGTAB";
      String test2 = "GXTXAYB";
      LongestCommonSubSeqLength lcs = new LongestCommonSubSeqLength();
      int ans = lcs.longestSubSeqLen(test1,test2,test1.length(),test2.length());
      System.out.println(ans);
   }
}
