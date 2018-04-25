package main.inheritancce;

public interface Animal {
     void run();


     default void doSome() {
          System.out.println("AA");
     }
}
