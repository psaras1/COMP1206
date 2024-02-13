import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import uk.ac.soton.ecs.comp1206.labtestlibrary.interfaces.io.ConcatenateJavaFiles;

public  class JavaFileUtil implements ConcatenateJavaFiles {


  public void concatenateJavaFiles(String dirName, String fileName) {
    try {

      File f = new File(dirName, fileName);

      Path dirPath = Paths.get(dirName);
      if (!Files.isDirectory(dirPath)) {
        throw new IllegalArgumentException("Provided path bot a directory");
      }

      List<Path> javaFiles = Files.list(Paths.get(dirName))
          .filter(file -> Files.isRegularFile(file) && file.toString().endsWith(".java"))
          .collect(Collectors.toList());

      try (BufferedWriter writer = new BufferedWriter(new FileWriter(f))) {
        for (Path x : javaFiles) {
          String toWrite = new String(Files.readAllBytes(x));
          writer.write(toWrite);
          writer.newLine();
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}