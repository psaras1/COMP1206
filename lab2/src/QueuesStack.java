import java.util.LinkedList;
import java.util.Queue;

public class QueuesStack {

  public static void main(String[] args) {
    QueuesStack qs = new QueuesStack();
    qs.add(5);
    qs.add(6);
    qs.add(10);
    qs.print();
    qs.remove();
    qs.print();
  }

  private Queue<Integer> q1;
  private Queue<Integer> q2;

  public QueuesStack() {
    this.q1 = new LinkedList<>();
    this.q2 = new LinkedList<>();
  }

  public void add(int x) {
    this.q1.add(x);
  }

  public void remove() {
    if (this.q1.isEmpty()) {
      System.out.println("Stack empty");
    } else {
      while (q1.size() > 1) {
        this.q2.add(q1.poll());
      }
      q1 = q2;
    }
  }

  public void print() {
    System.out.println(q1);
  }


}
