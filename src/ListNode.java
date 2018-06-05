public class ListNode<T>{
   private T data;
   private ListNode<T> next;
   
   /*
   public ListNode(){
      this(0);
   }*/
   
   public ListNode(T d){
      this(d,null);
   }
   public ListNode(T d, ListNode<T> L){
      this.data = d;
      this.next = L;
   }
   
   public void setData(T d){
      this.data = d;
   }
   public void setNext(ListNode<T> L){
      this.next = L;
   }
   public T getData(){
      return this.data;
   }
   public ListNode<T> getNext(){
      return this.next;
   }
}