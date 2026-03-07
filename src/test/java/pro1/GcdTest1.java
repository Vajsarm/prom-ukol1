package pro1;
import org.junit.jupiter.api.Assertions;

class GcdTest1 {
    @org.junit.jupiter.api.Test
    void test() {
        Assertions.assertEquals(10, NumericUtils.gcd(20, 50));
        Assertions.assertEquals(1, NumericUtils.gcd(25, 13));
        Assertions.assertEquals(1, NumericUtils.gcd(1, 1));
        Assertions.assertEquals(55, NumericUtils.gcd(55, 5500));
    }
}