package main.inheritancce;

public class Pet implements Animal {
    public Pet(String name) {
    }

    private String name;

    public void say() {
        System.out.println(name);
    }

    @Override
    public void run() {
        System.out.println("Running");
    }

    @Override
    public void doSome() {
        System.out.println("A");
    }
}
