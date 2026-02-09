import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LottoNumberTest {

    @Test
    @DisplayName("로또 번호는 1~45 범위면 생성된다")
    void creates_whenInRange() {
        assertDoesNotThrow(() -> new LottoNumber(1));
        assertDoesNotThrow(() -> new LottoNumber(45));
        assertDoesNotThrow(() -> new LottoNumber(10));
    }

    @Test
    @DisplayName("로또 번호가 1 미만이면 예외가 발생한다")
    void throws_whenLessThanMin() {
        assertThrows(IllegalArgumentException.class, () -> new LottoNumber(0));
        assertThrows(IllegalArgumentException.class, () -> new LottoNumber(-1));
    }

    @Test
    @DisplayName("로또 번호가 45 초과면 예외가 발생한다")
    void throws_whenGreaterThanMax() {
        assertThrows(IllegalArgumentException.class, () -> new LottoNumber(46));
        assertThrows(IllegalArgumentException.class, () -> new LottoNumber(100));
    }

    @Test
    @DisplayName("같은 값을 가진 LottoNumber는 equals로 같다고 판단한다")
    void equals_byValue() {
        LottoNumber a = new LottoNumber(7);
        LottoNumber b = new LottoNumber(7);

        assertEquals(a, b);
        assertEquals(a.hashCode(), b.hashCode());
    }

    @Test
    @DisplayName("값이 다른 LottoNumber는 equals로 다르다고 판단한다")
    void notEquals_whenDifferentValue() {
        LottoNumber a = new LottoNumber(7);
        LottoNumber b = new LottoNumber(8);

        assertNotEquals(a, b);
    }
}