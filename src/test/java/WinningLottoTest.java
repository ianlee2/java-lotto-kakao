import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class WinningLottoTest {

    @Test
    @DisplayName("당첨 번호와 보너스 번호로 WinningLotto를 정상 생성한다")
    void createsWinningLottoCorrectly() {
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6);
        int bonusNumber = 9;
        WinningLotto winningLotto = new WinningLotto(numbers);
        winningLotto.setBonusNumber(bonusNumber);

        assertDoesNotThrow(() -> winningLotto);
    }


    @Test
    @DisplayName("당첨 번호에 중복이 있으면 WinningLotto 생성에 실패한다")
    void failsWhenWinningNumbersDuplicated() {
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 5);
        assertThrows(IllegalArgumentException.class, () -> new WinningLotto(numbers));
    }

    @Test
    @DisplayName("보너스 번호가 당첨 번호와 같으면 WinningLotto 생성에 실패한다")
    void failsWhenBonusNumberDuplicated() {
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6);
        int bonusNumber = 6;
        WinningLotto winningLotto = new WinningLotto(numbers);
        assertThrows(IllegalArgumentException.class, () -> winningLotto.setBonusNumber(bonusNumber));
    }
}