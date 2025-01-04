package TestngSequence;

import org.testng.annotations.Test;

public class priority {

    @Test (priority = 3)
    public void one(){
        System.out.println("test1");
    }

    @Test (priority = 2)
    public void two(){
        System.out.println("test2");
    }

    @Test (priority = 1)
    public void three(){
        System.out.println("test3");
    }

}
