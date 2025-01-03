package GoogleWalkthrough;

import java.io.*;
import java.util.Properties;

public class googleSearch_properties {
    public static void main(String[] args) throws IOException {
        setProp();
        getProp();
    }

    public static void getProp() throws IOException {
        Properties properties = new Properties();
        InputStream stream = new FileInputStream("C:\\Program Files\\Intellij\\IdeaProjects\\Web_Automation_2\\src\\test\\java\\GoogleWalkthrough\\config.properties");
        properties.load(stream);
        String browser = properties.getProperty("browser");
        String path = properties.getProperty("path");
        System.out.println(browser + "\n" + path);
    }

    public static void setProp() throws IOException{
        Properties properties = new Properties();
        OutputStream stream = new FileOutputStream("C:\\Program Files\\Intellij\\IdeaProjects\\Web_Automation_2\\src\\test\\java\\GoogleWalkthrough\\config.properties");
        properties.setProperty("browser", "Edge");
        properties.setProperty("path", "C:\\Program Files\\Intellij\\IdeaProjects\\Web_Automation_2\\chromedriver.exe");
        properties.store(stream,null);
    }
}
