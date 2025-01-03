package GoogleWalkthrough;

import java.io.*;
import java.util.Properties;

public class googleSearch_properties {
    public static void main(String[] args) throws IOException {
        setProperties();
        getProperties();

    }

    public static void getProperties() {
      try{
         Properties properties = new Properties();
         InputStream stream = new FileInputStream("C:\\Program Files\\Intellij\\IdeaProjects\\Web_Automation_2\\src\\test\\java\\GoogleWalkthrough\\config.properties");
         properties.load(stream);
         String browser = properties.getProperty("browser");
         String path = properties.getProperty("path");

         System.out.println(browser + "\n"+ path);
      } catch (Exception e){
          e.printStackTrace();
      }
    }

    public static void setProperties() throws IOException {
        Properties properties = new Properties();
        OutputStream outputStream = new FileOutputStream("C:\\Program Files\\Intellij\\IdeaProjects\\Web_Automation_2\\src\\test\\java\\GoogleWalkthrough\\config.properties");
        properties.setProperty("browser", "Chrome");
        properties.setProperty("path", "C:\\Program Files\\Intellij\\IdeaProjects\\Web_Automation_2\\chromedriver.exe");
        properties.store(outputStream,null);
    }
}
