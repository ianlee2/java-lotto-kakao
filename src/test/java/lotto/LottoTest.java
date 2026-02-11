package lotto;

import lotto.domain.Lotto;
import lotto.domain.LottoNumber;
import lotto.domain.WinningLotto;
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

    @Test
    @DisplayName("보너스 번호가 포함되어 있으면 true를 반환한다")
    void matchBonus_returnsTrue_whenBonusIncluded() {
        Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        WinningLotto winning = new WinningLotto(new Lotto(List.of(7, 8, 9, 10, 11, 12)), new LottoNumber(6));

        assertTrue(lotto.matchBonus(winning));
    }

    @Test
    @DisplayName("보너스 번호가 포함되어 있지 않으면 false를 반환한다")
    void matchBonus_returnsFalse_whenBonusNotIncluded() {
        Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        WinningLotto winning = new WinningLotto(new Lotto(List.of(7, 8, 9, 10, 11, 12)), new LottoNumber(13));

        assertFalse(lotto.matchBonus(winning));
    }

    @Test
    @DisplayName("일치하는 번호 개수를 정확히 반환한다")
    void matchNumbers_returnsCorrectCount() {
        Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        WinningLotto winning = new WinningLotto(new Lotto(List.of(1, 2, 7, 8, 9, 10)), new LottoNumber(11));

        assertEquals(2, lotto.matchNumbers(winning));
    }

    @Test
    @DisplayName("일치하는 번호가 없으면 0을 반환한다")
    void matchNumbers_returnsZero_whenNoMatch() {
        Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        WinningLotto winning = new WinningLotto(new Lotto(List.of(7, 8, 9, 10, 11, 12)), new LottoNumber(13));

        assertEquals(0, lotto.matchNumbers(winning));
    }

    @Test
    @DisplayName("6개 모두 일치하면 6을 반환한다")
    void matchNumbers_returnsSix_whenAllMatch() {
        Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        WinningLotto winning = new WinningLotto(new Lotto(List.of(1, 2, 3, 4, 5, 6)), new LottoNumber(7));

        assertEquals(6, lotto.matchNumbers(winning));
    }

}