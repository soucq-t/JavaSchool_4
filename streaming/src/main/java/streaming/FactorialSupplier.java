package streaming;

import java.math.BigInteger;
import java.util.function.Supplier;

public class FactorialSupplier implements Supplier<BigInteger> {

    private int i = 0;
    private BigInteger factorial = BigInteger.ONE;

    public int getI() {
        return i;
    }

    @Override
    public BigInteger get() {
        i++;
        factorial = factorial.multiply(BigInteger.valueOf(i));
        return factorial;
    }

}
