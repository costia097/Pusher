package main.inheritancce;

import io.vavr.control.Try;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Start {
    public static void main(String[] args) {
        Cat cat = new Cat();
        cat.setMrr(true);
        recursive();
        Animal catSecond = new Cat();
        catSecond.run();
        Animal anyPet = new Pet("a");
        anyPet.run();
        if (catSecond instanceof Pet) {
            catSecond.run();
        }
        Object o = new Cat();
        Cat temp = null;
        if (o instanceof Cat) {
            temp = (Cat) o;
            temp.say();
        }
        final Class<? extends Cat> aClass = temp.getClass();
        Enumda navi = Enumda.NAVI;
        System.out.println(navi.getValue());
        final Enumda[] values = Enumda.values();
        System.out.println(Arrays.toString(values));
        anyPet.doSome();
        WorkerImp workerImp = new WorkerImp();
        Predicate<String> isEmpty = String::isEmpty;
        final List<String> a = Stream.of("A", "").filter(isEmpty).collect(Collectors.toList());
        final List<Pet> aa = Stream.of("AA").map(Pet::new).collect(Collectors.toList());
//        Stream.of("A","B").map(anyPet::doSome)
//        Stream.empty().collect(() -> )
//        Comparator.comparing(o1 -> o1.)
        final Map<Boolean, Integer> a1 = Stream.of("A").collect(Collectors.toMap(String::isEmpty, String::hashCode));
//        Try.run(Start::recursive).
    }

    private static class Inner<T, V>{
    }

//    private Collectors collectors() {
//    }

    private enum Enumda{
        NAVI("N"),
        GAMBIT("G");
        private String value;

        Enumda(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }

    private static void recursive() {
//        System.out.println("Start");
        if (new Random().nextBoolean()) {
            recursive();
//            System.out.println("A");
        }
//        System.out.println("End");
    }
}
