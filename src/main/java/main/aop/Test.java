package main.aop;

import java.util.Random;

public class Test {
    public static void main(String[] args) {
//        int a =  4 bytes
//        long l 8 bytes
//        byte a = -128;
//        byte b = 127; 1 byte
//
//        final Integer a;
//        a = 3;
//        System.out.println(a);

        Boolean flag = false;
//        flag |= methodBool();
        flag |= methodBool();
        flag |= methodBool();
        System.out.println(flag);
        int i = 0;
        int a= i++;
        int b = ++i;
        System.out.println(a);
        System.out.println(b);

//        &&
//        ||
//        !
//        System.out.println(testOr());
        TestEnum testEnum = TestEnum.NAVI;
        final String name = testEnum.name();
    }

    private static boolean methodBool() {
        final boolean b = new Random().nextBoolean();
        System.out.println(b);
        return b;
    }

    private Object ternar() {
        return new Random().nextBoolean() ? new Object() : null;
    }

    private static Boolean testOr() {
        return 1 > 2 || 3 < 2;
    }

    private enum TestEnum {
        NAVI,
        GAMBIT
    }

}
