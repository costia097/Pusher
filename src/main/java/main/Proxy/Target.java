package main.Proxy;

public class Target implements TargetInterface{
    private String name;

    public Target(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String doSome() {
        System.out.println();
        return name;
    }
}
