import java.util.*;

public class ExpressionNotations{

   private int Precedence(Character c){
      switch(c){
         case '+':
         case '-':
            return 1;
         case '*':
         case '/':
            return 2;
         case '^':
            return 3;
         default: return -1;
      }
   }
   
   public String InToPost(String s){
      /* 1. Scan the infix expression from left to right.
         2. If the scanned character is an operand, output it.
         3. Else,
         …..3.1 If the precedence of the scanned operator is greater than the precedence of the operator in the stack(or the stack is empty), push it.
         …..3.2 Else, Pop the operator from the stack until the precedence of the scanned operator is less-equal to the precedence of the operator residing on the top of the stack. Push the scanned operator to the stack.
         4. If the scanned character is an ‘(‘, push it to the stack.
         5. If the scanned character is an ‘)’, pop and output from the stack until an ‘(‘ is encountered.
         6. Repeat steps 2-6 until infix expression is scanned.
         7. Pop and output from the stack until it is not empty.
      */
      String res = "";
      Stack<Character> st = new Stack<Character>();
      for(int i=0; i<s.length(); i++){
         char c = s.charAt(i);
         
         if(Character.isLetterOrDigit(c))
            res = res + c;
         else if(c == '(')
            st.push(c);
         else if(c == ')'){
            while(!st.isEmpty() && st.peek()!='(')
               res = res + st.pop();
            // if no left paranthesis--invalid string else remove '('
            if(!st.isEmpty() && st.peek()!='(')
               return "Invalid Expression";
            else st.pop(); 
         }
         else {
            while(!st.isEmpty() && Precedence(st.peek()) >= Precedence(c)){
               res = res + st.peek();
               st.pop();
            }
            st.push(c);    
         }
      }
      
      while(!st.isEmpty())
         res = res + st.pop();
      return res;
      
   }
   
   // reverse and switch '(' and ')'
   private String reverseSwitch(String s){
      String res = "";
      for(int i=s.length()-1; i>=0; i--){
         Character c = s.charAt(i);
         if(c == '(')
            c = ')';
         else if(c == ')')
            c = '(';
         res = res + c;
      }
      return res;
   }
   
   public String InToPre(String s){
      s = reverseSwitch(s);
      s = InToPost(s);
      s = reverseSwitch(s);
      return s;
   }
   
   private String appendProper(String c1, String c2, Character o, int i){
      String res = "";
      switch(i){
         case 0: res = res+c1+c2+o; break;
         case 1: res = res+"("+c1+o+c2+")"; break;
         case 2: res = res+o+c2+c1; break;
         case 3: res = res+"("+c2+o+c1+")"; break;
      }
      return res;
   }
      
   private String PrePostIn(String s, int o){
      /* 0 --> PreToPost; 1 --> PreToIn;
         2 --> PostToPre; 3 --> PostToIn;*/
         
      Stack<String> st = new Stack<String>();
      int j=0;
      for(int i = 0; i<s.length(); i++){
         // if prefix is input, parse string from right end
         if(o < 2)
            j = s.length()-1-i;
         else j = i;
         Character c = s.charAt(j);
         if(Character.isLetterOrDigit(c))
            st.push(Character.toString(c));
         else // operator => pop 2 apply and push back
            st.push(appendProper(st.pop(), st.pop(), c, o));
            
         //System.out.println(st.size()+"next");
      }
      return st.pop();
   }
   
   public String PreToPost(String s){
      return PrePostIn(s,0);
   }
   
   public String PostToPre(String s){
      return PrePostIn(s,2);
   }
   
   public String PostToIn(String s){
      return PrePostIn(s,3);
   }
   
   public String PreToIn(String s){
      return PrePostIn(s,1);
   }
   
   public static void main(String[] args){
      ExpressionNotations en = new ExpressionNotations();
      String Infix = "a+b*(c^d-e)^(f+g*h)-i";
      String Postfix = "ABC/-AK/L-*";
      String Prefix = "*-A/BC-/AKL";
      System.out.println("Infix: " + Infix + " to PostFix: " + en.InToPost(Infix));
      System.out.println("Infix: " + Infix + " to PreFix: " + en.InToPre(Infix));
      System.out.println("Prefix: " + Prefix + " to PostFix: " + en.PreToPost(Prefix));
      System.out.println("Prefix: " + Prefix + " to InFix: " + en.PreToIn(Prefix));
      System.out.println("Postfix: " + Postfix + " to PreFix: " + en.PostToPre(Postfix));
      System.out.println("Postfix: " + Postfix + " to InFix: " + en.PostToIn(Postfix));
   }
   
}