package GoogleWalkthrough;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class googleSearch_properties {
    public static void main(String[] args) {
        getProperties();
    }

    public static void getProperties() {
      try{
         Properties properties = new Properties();
         InputStream stream = new FileInputStream("C:\\Program Files\\Intellij\\IdeaProjects\\Web_Automation_2\\src\\test\\java\\GoogleWalkthrough\\config.properties");
         properties.load(stream);
         String browser = properties.getProperty("browser");
         System.out.println(browser);
         System.out.println();
      } catch (Exception e){
          e.printStackTrace();
      }
    }

}
