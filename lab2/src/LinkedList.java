import java.util.List;

public class LinkedList {

  public static void main(String[] args) {
    List<Integer> ll = new java.util.LinkedList<>();
    ll.add(5);
    ll.add(10);
    ll.add(15);
    int toRemove = 50;
    for (int i = 0; i < ll.size(); i++) {
      if (ll.get(i) == toRemove) {
        ll.remove(i);
      }
    }
  }

}

