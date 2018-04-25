package main.aop;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

@Component
public class MyObject {
    public static void main(String[] args) {
        NewImmutableClass name = new NewImmutableClass("Name");
        name.setName("LastName");
        System.out.println(name.getName());
        Integer first = 0;
        Integer second = 2;
        System.out.println(first.compareTo(second));
        System.out.println("Hello ");
        Scanner in = new Scanner(System.in);
        final String s = in.nextLine();
        System.out.println(s);
        List<String> list = new ArrayList<>();
        for (String element : list) {
            System.out.println(Arrays.toString(element.getBytes()));
        }
        Integer[] massive = new Integer[10];
        final Integer[] integers = Arrays.copyOf(massive, 5);
//        ==
//        =
//        Arrays.sort();
        Arrays.binarySearch(massive, 1);

    }
}
