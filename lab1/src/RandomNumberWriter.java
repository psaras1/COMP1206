import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;
import java.nio.ByteBuffer;
import java.util.Random;
import uk.ac.soton.ecs.comp1206.labtestlibrary.interfaces.io.RandomIO;

public class RandomNumberWriter implements RandomIO {

  OutputStream byteWriter;
  Writer charWriter;
  File f;
  Random r;
  Integer randomNumber;
  byte[] b;


  public RandomNumberWriter(long x) {

    this.r = new Random(x);
  }

  public void writeRandomChars(String filename) throws IOException {
    this.f = new File(filename);
    this.charWriter = new FileWriter(f);
    for (int i = 0; i < 10000; i++) {
      this.randomNumber = r.nextInt(100000);
      charWriter.write(randomNumber + "\n");
    }
    charWriter.close();
  }

  public void writeRandomByte(String filename) throws IOException {
    this.f = new File(filename);
    this.byteWriter = new FileOutputStream(f);
    for (int i = 0; i < 10000; i++) {
      this.randomNumber = r.nextInt(100000);
      this.b = ByteBuffer.allocate(4).putInt(randomNumber).array();
      byteWriter.write(b);
    }
    byteWriter.close();
  }

}
