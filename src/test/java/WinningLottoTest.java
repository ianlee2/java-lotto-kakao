import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class WinningLottoTest {

    @Test
    @DisplayName("당첨 번호와 보너스 번호로 WinningLotto를 정상 생성한다")
    void createsWinningLottoCorrectly() {
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6);
        int bonusNumber = 9;

        assertDoesNotThrow(() -> new WinningLotto(numbers, bonusNumber));
    }
}