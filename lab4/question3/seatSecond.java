import java.util.concurrent.locks.ReentrantLock;
import uk.ac.soton.ecs.comp1206.labtestlibrary.interfaces.threading.Seat;

public class seatSecond implements Seat {
  private ReentrantLock leftFork;
  private ReentrantLock rightFork;

  @Override
  public void assignForks(ReentrantLock reentrantLock, ReentrantLock reentrantLock1) {
    leftFork = reentrantLock;
    rightFork = reentrantLock1;

  }
  @Override
  public  void askFork1() {
    rightFork.lock();
  }
  @Override
  public  void askFork2() {
    leftFork.lock();
  }







}
