package pro1;

;

public class Fraction {
    private long n;
    private long d;

    public Fraction(long n, long d) {
        long gcd = NumericUtils.gcd(n, d);
        this.n = n / gcd;
        this.d = d / gcd;
    }

    public Fraction add(Fraction druhyZlomek) {
        long novyCitatel = (this.n * druhyZlomek.d) + (druhyZlomek.n * this.d);
        long novyJmenovatel = this.d * druhyZlomek.d;
        return new Fraction(novyCitatel, novyJmenovatel);
    }

    @Override
    public String toString() {
        return n + "/" + d;
    }
}