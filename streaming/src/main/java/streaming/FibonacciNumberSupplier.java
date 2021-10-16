package streaming;

import java.math.BigInteger;

public class FibonacciNumberSupplier {
    private int i = 2;
    private BigInteger n1 = BigInteger.ONE, n2 = BigInteger.ONE;
    private BigInteger fibonacciNumber = BigInteger.ZERO;


    public BigInteger get() {
        i++;
        fibonacciNumber = n1.add(n2);
        n2 = n1;
        n1 = fibonacciNumber;
        return fibonacciNumber;
    }

    public int getI() {
        return i;
    }
}
