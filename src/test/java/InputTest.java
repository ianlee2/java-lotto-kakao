import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
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

        assertThrows(IllegalArgumentException.class, input::inputPrice);
    }

    @Test
    @DisplayName("구입 금액이 숫자가 아니면 예외가 발생한다")
    void inputPrice_throws_whenNotNumber() {
        Input input = inputWithLines("abc");

        assertThrows(IllegalArgumentException.class, input::inputPrice);
    }

    @Test
    @DisplayName("당첨 번호와 보너스 번호를 입력하면 WinningLotto를 생성한다")
    void inputWinningNumbers_returnsWinningLotto_whenValid() {
        Input input = inputWithLines(
                "1, 2, 3, 4, 5, 6",
                "7"
        );

        WinningLotto winningLotto = input.inputWinningNumbers();

        // Lotto의 숫자 확인 (Lotto가 getLottoNumbers() 제공한다고 가정)
        assertEquals(6, winningLotto.size());
        assertEquals(7, winningLotto.getBonusNumber().getValue());
    }

    @Test
    @DisplayName("당첨 번호가 숫자가 아니면 예외가 발생한다")
    void inputWinningNumbers_throws_whenWinningNumbersNotNumeric() {
        Input input = inputWithLines(
                "1, 2, a, 4, 5, 6",
                "7"
        );

        assertThrows(NoSuchElementException.class, input::inputWinningNumbers);
    }

    @Test
    @DisplayName("보너스 번호가 숫자가 아니면 예외가 발생한다")
    void inputWinningNumbers_throws_whenBonusNotNumeric() {
        Input input = inputWithLines(
                "1, 2, 3, 4, 5, 6",
                "bonus"
        );

        assertThrows(NoSuchElementException.class, input::inputWinningNumbers);
    }
}