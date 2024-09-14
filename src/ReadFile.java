import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ReadFile {
  private String content;

  public ReadFile(String path) {
    try {
      this.content = readPath(path);
    } catch (FileNotFoundException e) {
      System.out.println("File not found");
    } catch (IOException e) {
      System.out.println("Error reading file");
    }
  }

  public String readPath(String path) throws IOException, FileNotFoundException {
    FileReader fileReader = new FileReader(path);
    BufferedReader bufferedReader = new BufferedReader(fileReader);

    StringBuilder stringBuilder = new StringBuilder();
    String line = bufferedReader.readLine();

    while (line!= null) {
      stringBuilder.append(line);
      line = bufferedReader.readLine();
    }

    bufferedReader.close();
    return stringBuilder.toString();
  }

  public String getContent() {
    return content;
  }
}
