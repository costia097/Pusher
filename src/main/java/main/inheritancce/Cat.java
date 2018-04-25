package main.inheritancce;

public class Cat extends Pet{
    public Cat() {
        super("A");
    }

    private boolean isMrr;

    public boolean isMrr() {
        return isMrr;
    }

    public void setMrr(boolean mrr) {
        isMrr = mrr;
    }

    @Override
    public void run() {
        System.out.println("Laying");
    }

    @Override
    public void say() {
        System.out.println("May!!");
    }
}
