import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PriceTest {
    @Test
    @DisplayName("가격이 들어왔을때 로또 몇개 반환하는지 반환.")
    void returns_lotto_count () {
        Price price = new Price(12000);

        assertThat(price.getLottoCount()).isEqualTo(12);

    }

    @Test
    @DisplayName("1000원 이하면 0개 반환")
    void returns_zero_lotto_count () {
        Price price = new Price(500);

        assertThat(price.getLottoCount()).isEqualTo(0);

    }

    @Test
    @DisplayName("1000원 단위 이하는 버림")
    void returns_floor_lotto_count () {
        Price price = new Price(12500);

        assertThat(price.getLottoCount()).isEqualTo(12);

    }
}
