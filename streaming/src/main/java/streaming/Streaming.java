package streaming;

import java.math.BigInteger;
import java.util.*;
import java.util.stream.*;

public class Streaming {


    public static int[] a() {
        return IntStream.range(1, 20)
                .filter(value -> value % 2 != 0)
                .map(s -> s * s)
                .toArray();
    }

    public static double b() {
        return IntStream.range(1, 101)
                .mapToDouble(i -> 1.0 / ((i + 1.0) * (i + 2.0)))
                .sum();
    }

    public static int[] c() {
        return new Random().ints(1, 46)
                .limit(6)
                .sorted().toArray();
    }

    public static long d() {
        return LongStream.range(1, 21)
                .reduce(1, (r, e) -> (r * e));
    }

    public static long e() {
        return IntStream.range(1,1001)
                .flatMap(s -> String.valueOf(s).chars())
                .filter(s -> s == '1')
                .count();
    }

    public static BigInteger f() {
        FactorialSupplier supplier= new FactorialSupplier();
        return Stream.iterate(supplier.get(),i -> supplier.get())
                .filter(integer -> integer.toString().length()>20)
                .findFirst()
                .get();

    }

    public static int g() {
        FactorialSupplier supplier= new FactorialSupplier();
        Stream.iterate(supplier.get(),i -> supplier.get())
                .filter(s -> s.toString().length()>=10_000)
                .findFirst();
        return supplier.getI();
    }

    public static int h() {
        FibonacciNumberSupplier supplier=new FibonacciNumberSupplier();
        Stream.iterate(supplier.get(),i -> supplier.get())
                .filter(bigInteger -> bigInteger.toString().length()>=1000)
                .peek(bigInteger -> System.out.println("number: " +bigInteger+" "+bigInteger.toString().length()))
                .findFirst();
        return supplier.getI();
    }


    public static void main(String[] args) {
        System.out.println(Stream.of("1","1d","233")
                .mapToInt(s -> s.length())
                .average()
                .orElseThrow()
        );
        var hallo=new Random().ints(20,1,100)
                .boxed()
                .collect(Collectors.groupingBy(i -> i/10,TreeMap::new,Collectors.toList()));
        System.out.println(hallo);
    }

}
