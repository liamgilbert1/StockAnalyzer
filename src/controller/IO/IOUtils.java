package controller.IO;

import java.io.File;
import java.net.URISyntaxException;

import main.Main;

public class IOUtils {
  /**
   * Gets the directory of the JAR file.
   * @return the directory of the JAR file.
   */
  private static String getJarDirectory() {
    try {
      String jarPath = Main.class.getProtectionDomain().getCodeSource().getLocation().toURI()
              .getPath();

      File jarFile = new File(jarPath);

      return jarFile.getParent();
    } catch (URISyntaxException e) {
      throw new RuntimeException("Could not get JAR directory", e);
    }
  }

  /**
   * Gets the file with the given name and suffix in the given directory. If the directory does not
   * exist, it will be created.
   * @param fileName the name of the file.
   * @param fileSuffix the suffix of the file.
   * @param directoryName the name of the directory.
   * @return the file with the given name and suffix in the given directory.
   */
  public static File getFile(String fileName, String fileSuffix, String directoryName) {
    fileName += fileSuffix;
    String jarDirectoryPath = IOUtils.getJarDirectory();
    String newDirectoryPath = jarDirectoryPath + "/" + directoryName;
    File newDirectory = new File(newDirectoryPath);

    if (!newDirectory.exists() && !newDirectory.mkdirs()) {
      throw new IllegalStateException("Could not create new directory.");
    }

    String filePath = newDirectory.getPath() + "/" + fileName;
    return new File(filePath);
  }
}
