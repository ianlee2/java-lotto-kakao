import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LottoTest {

    @Test
    @DisplayName("로또는 번호 6개로 생성되며 size는 6이다")
    void creates_withSixNumbers() {
        Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));

        assertEquals(6, lotto.size());
        assertEquals(6, lotto.getLottoNumbers().size());
    }

    @Test
    @DisplayName("로또 번호가 6개 미만이면 예외가 발생한다")
    void throws_whenLessThanSix() {
        assertThrows(IllegalArgumentException.class,
                () -> new Lotto(List.of(1, 2, 3, 4, 5)));
    }

    @Test
    @DisplayName("로또 번호가 6개 초과면 예외가 발생한다")
    void throws_whenMoreThanSix() {
        assertThrows(IllegalArgumentException.class,
                () -> new Lotto(List.of(1, 2, 3, 4, 5, 6, 7)));
    }

    @Test
    @DisplayName("로또 번호에 중복이 있으면 예외가 발생한다")
    void throws_whenDuplicated() {
        assertThrows(IllegalArgumentException.class,
                () -> new Lotto(List.of(1, 2, 3, 4, 5, 5)));
    }
}