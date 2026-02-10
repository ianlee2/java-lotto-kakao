public class Price {
    private final Integer value;
    private static final int PRICE_PER_LOTTO = 1000;

    public Price(Integer price) {
        this.value = price;
    }

    public Integer getLottoCount() {
        return value / PRICE_PER_LOTTO;
    }

    public Integer getValue() {
        return value;
    }
}
