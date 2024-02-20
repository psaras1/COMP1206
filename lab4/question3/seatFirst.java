import java.util.concurrent.locks.ReentrantLock;
import uk.ac.soton.ecs.comp1206.labtestlibrary.interfaces.threading.Seat;

public class seatFirst implements Seat {
  private ReentrantLock leftFork;
  private ReentrantLock rightFork;

  @Override
  public void assignForks(ReentrantLock reentrantLock, ReentrantLock reentrantLock1) {
    leftFork = reentrantLock;
    rightFork = reentrantLock1;
  }
  @Override
  public  void askFork1() {
    leftFork.lock();

  }

  @Override
  public  void askFork2() {

    rightFork.lock();

  }


}
