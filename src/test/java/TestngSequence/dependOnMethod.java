package TestngSequence;

import org.testng.annotations.Test;

public class dependOnMethod {

    @Test (dependsOnMethods = {"test2"})
    public void test1(){
        System.out.println("test1");
    }

    @Test (groups = {"UAT"})
    public void test2(){
        System.out.println("test2");
    }

    @Test (groups = {"UAT"})
    public void test3(){
        System.out.println("test3");
    }
}
