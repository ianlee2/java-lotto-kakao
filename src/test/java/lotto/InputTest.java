package lotto;

import lotto.domain.Lotto;
import lotto.domain.Price;
import lotto.domain.WinningLotto;
import lotto.view.Input;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class InputTest {

    private Input inputWithLines(String... lines) {
        String joined = String.join("\n", lines) + "\n";
        ByteArrayInputStream in = new ByteArrayInputStream(joined.getBytes(StandardCharsets.UTF_8));
        return new Input(new Scanner(in));
    }

    @Test
    @DisplayName("구입 금액이 숫자면 Price로 반환한다")
    void inputPrice_returnsPrice_whenValidNumber() {
        Input input = inputWithLines("14000");

        Price price = input.inputPrice();

        assertEquals(14000, price.getValue());
        assertEquals(14, price.getLottoCount());
    }

    @Test
    @DisplayName("구입 금액이 음수면 예외가 발생한다")
    void inputPrice_throws_whenNegative() {
        Input input = inputWithLines("-1");

        assertThrows(NoSuchElementException.class, input::inputPrice);
    }

    @Test
    @DisplayName("구입 금액이 숫자가 아니면 예외가 발생한다")
    void inputPrice_throws_whenNotNumber() {
        Input input = inputWithLines("abc");

        assertThrows(NoSuchElementException.class, input::inputPrice);
    }

    @Test
    @DisplayName("당첨 번호와 보너스 번호를 입력하면 WinningLotto를 생성한다")
    void inputWinningNumbers_returnsWinningLotto_whenValid() {
        Input input = inputWithLines(
                "1, 2, 3, 4, 5, 6",
                "7"
        );

        WinningLotto winningLotto = input.inputWinningNumbersAndBonusNumber();

        // Lotto의 숫자 확인 (Lotto가 getLottoNumbers() 제공한다고 가정)
        assertEquals(6, winningLotto.getWinningLottoNumbers().size());
        assertEquals(7, winningLotto.getBonusNumber().getValue());
    }

    @Test
    @DisplayName("당첨 번호가 숫자가 아니면 예외가 발생한다")
    void inputWinningNumbers_throws_whenWinningNumbersNotNumeric() {
        Input input = inputWithLines(
                "1, 2, a, 4, 5, 6",
                "7"
        );

        assertThrows(NoSuchElementException.class, input::inputWinningNumbersAndBonusNumber);
    }

    @Test
    @DisplayName("보너스 번호가 숫자가 아니면 예외가 발생한다")
    void inputWinningNumbers_throws_whenBonusNotNumeric() {
        Input input = inputWithLines(
                "1, 2, 3, 4, 5, 6",
                "bonus"
        );

        assertThrows(NoSuchElementException.class, input::inputWinningNumbersAndBonusNumber);
    }

    // inputManualCount() 단위 테스트
    @Test
    @DisplayName("수동 구매 수가 0~maxCount면 해당 값을 반환한다 - 성공")
    void inputManualCount_returnsCount_whenValid() {
        Input input = inputWithLines("3");
        assertEquals(3, input.inputManualCount(13));
    }

    @Test
    @DisplayName("수동 구매 수가 숫자가 아니면 재입력 후 정상 입력을 반환한다")
    void inputManualCount_retries_whenNotNumber() {
        Input input = inputWithLines("asd", "3");
        assertEquals(3, input.inputManualCount(13));
    }

    @Test
    @DisplayName("수동 구매 수가 음수면 재입력 후 정상 입력을 반환한다")
    void inputManualCount_retries_whenNegative() {
        Input input = inputWithLines("-1", "3");
        assertEquals(3, input.inputManualCount(13));
    }

    @Test
    @DisplayName("수동 구매 수가 maxCount 초과면 재입력 후 정상 입력을 반환한다")
    void inputManualCount_retries_whenOverMaxCount() {
        Input input = inputWithLines("14", "13");
        assertEquals(13, input.inputManualCount(13));
    }

    // inputManualLotto() 단위 테스트
    @Test
    @DisplayName("수동 로또 구매 수 만큼 Lotto를 생성한다")
    void inputManualLotto_returnsListOfLotto_whenValid() {
        Input input = inputWithLines(
                "1, 2, 3, 4, 5, 6",
                "7, 8, 9, 10, 11, 12",
                "13, 14, 15, 16, 17, 18"
        );

        List<Lotto> manualLottos = input.inputManualLotto(3);

        assertEquals(3, manualLottos.size());
        // 각 로또가 6개 숫자를 가지는지 (Lotto가 getLottoNumbers() 제공한다고 가정)
        assertEquals(6, manualLottos.get(0).getLottoNumbers().size());
        assertEquals(6, manualLottos.get(1).getLottoNumbers().size());
        assertEquals(6, manualLottos.get(2).getLottoNumbers().size());
    }

    @Test
    @DisplayName("수동 로또 입력이 잘못되면 재입력 후 Lotto를 생성한다")
    void inputManualLotto_retries_whenInvalidInput() {
        Input input = inputWithLines(
                "1, 2, a, 4, 5, 6",      // 실패 (a)
                "1, 2, 3, 4, 5, 6",      // 성공 (1번 로또)
                "7, 8, 9, 10, 11, 12"    // 성공 (2번 로또)
        );

        List<Lotto> manualLottos = input.inputManualLotto(2);

        assertEquals(2, manualLottos.size());
        assertEquals(6, manualLottos.get(0).getLottoNumbers().size());
        assertEquals(6, manualLottos.get(1).getLottoNumbers().size());
    }

}