package main.inheritancce;

@FunctionalInterface
public interface WorkerI {
    <I, U> U collec(I i);
}
