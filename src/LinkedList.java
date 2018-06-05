public class LinkedList<T>{
   private int length;
   private ListNode<T> head;
   
   public LinkedList(){
      this.length = 0;
      this.head = null;
   }
   
   public void setHead(ListNode<T> h){
      this.head = h;
   }
   
   public ListNode<T> getHead(){
      return this.head;
   }

   public void add(T d){
      ListNode<T> L = new ListNode<T>(d);
      if(head == null)
         head = L;
      else{
         ListNode<T> temp = head;
         while(temp.getNext()!=null)
            temp = temp.getNext();
         temp.setNext(L);
      }
      length++;
   }
   
   public void print(){
      ListNode<T> temp = head;
      while(temp.getNext()!=null){
         System.out.print(temp.getData());
         System.out.print("-->");
         temp = temp.getNext();
      }
      System.out.println(temp.getData());
      return;
   }
   
   public ListNode<T> reverse(){
      return this.reverse(this.head, this.length);
   }
   
   public ListNode<T> reverse(ListNode head, int k)
   {
       ListNode<T> current = head;
       ListNode<T> nxt = null;
       ListNode<T> prev = null;
        
       int count = 0;
 
       /* Reverse first k nodes of linked list */
       while (count < k && current != null) 
       {
           nxt = current.getNext();
           current.setNext(prev);
           prev = current;
           current = nxt;
           count++;
       }
 
       /* next is now a pointer to (k+1)th node 
          Recursively call for the list starting from current.
          And make rest of the list as next of first node */
       if (current != null) 
          head.setNext(reverse(current, k));
 
       // prev is now head of input list
       return prev;
   }
       
   public static void main(String args[]){
      LinkedList ls = new LinkedList<Integer>();
      for(int i=1;i<=5;i++)
         ls.add(i);
      ls.print();
      ls.setHead(ls.reverse(ls.getHead(),3));
      ls.print();
   }
}