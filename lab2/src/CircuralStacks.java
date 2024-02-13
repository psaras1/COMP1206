import java.util.Stack;

public class CircuralStacks {
  public static void main(String[]args){

    CircuralStacks cs = new CircuralStacks();
    System.out.println(cs.stack1);
    cs.enqueue(50);
    cs.enqueue(76);
    System.out.println(cs.stack1);
    cs.enqueue(10);
    System.out.println(cs.stack1);
    cs.dequeue();
    System.out.println(cs.stack1);


  }

  private Stack<Integer> stack1;
  private Stack<Integer> stack2;
  public CircuralStacks(){
    stack1 = new Stack<>();
    stack2 = new Stack<>();
  }



  public void enqueue(int a) {
    stack1.push(a);
  }

  public void dequeue() {
    if (stack1.isEmpty()) {
      throw new IllegalArgumentException("Stack empty");
    } else {
      while (!stack1.isEmpty()) {
        int elem = stack1.pop();
        stack2.push(elem);
      }
      stack2.pop();
      while (!stack2.isEmpty()) {
        int elem2 = stack2.pop();
        stack1.push(elem2);
      }
    }
  }

}
