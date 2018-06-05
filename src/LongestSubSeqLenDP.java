// 

public class LongestSubSeqLenDP{
   public int longestSubSeqLenDP(String s1, String s2, int m,int n){
      int[][] L = new int[m+1][n+1];
      /* Following steps build L[m+1][n+1] in bottom up fashion. Note
         that L[i][j] contains length of LCS of X[0..i-1] and Y[0..j-1] */
      for(int i=0; i<=m; i++){
         for(int j=0; j<=n; j++){
            if(i==0 || j==0)
               L[i][j] = 0;
            else if(s1.charAt(i-1)==s2.charAt(j-1))
               L[i][j] = 1 + L[i-1][j-1];
            else L[i][j] = Math.max(L[i-1][j],L[i][j-1]);
         }
      }
      return L[m][n];
   }
   
   public static void main(String[] args)
   {
    LongestSubSeqLenDP lcs = new LongestSubSeqLenDP();
    String s1 = "AGGTAB";
    String s2 = "GXTXAYB"; 
    System.out.println("Length of LCS is" + " " + lcs.longestSubSeqLenDP(s1,s2,s1.length(), s2.length()) );
   }
}