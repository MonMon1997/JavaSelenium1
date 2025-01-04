package TestngSequence;

import org.testng.annotations.Test;

@Test (groups = "IMA UAT")
public class grouping {

    @Test (groups = {"Sanity","Regression"})
    public void test1(){
        System.out.println("Test1");
    }

    @Test (groups = {"Regression"})
    public void test2(){
        System.out.println("Test2");
    }

    @Test (groups = {"UAT"})
    public void test3(){
        System.out.println("Test3");
    }

    @Test 
    public void test4(){
        System.out.println("Test4");
    }
}
