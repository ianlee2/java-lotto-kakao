public class Price {
    private final Integer value;
    private static final int PRICE_PER_LOTTO = 1000;

    public Price(String price) {
        int parsedValue;
        try {
            parsedValue = Integer.parseInt(price);

            if (parsedValue < PRICE_PER_LOTTO) {
                throw new IllegalArgumentException("구입 금액은 1000원 이상이어야 합니다. 다시 입력해주세요.");
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("구입 금액은 숫자여야 합니다. 다시 입력해주세요.");
        }



        this.value = parsedValue;
    }

    public Integer getLottoCount() {
        return value / PRICE_PER_LOTTO;
    }

    public Integer getValue() {
        return value;
    }


}
