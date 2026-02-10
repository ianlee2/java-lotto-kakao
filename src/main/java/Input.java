import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Input {

    private final Scanner scanner;

    // 운영용: 기본 System.in
    public Input() {
        this(new Scanner(System.in));
    }

    // 테스트용/DI용: Scanner 주입
    public Input(Scanner scanner) {
        this.scanner = scanner;
    }

    // 구입 금액 입력
    public Price inputPrice() {
        System.out.println("구입금액을 입력해 주세요.");

        String input = scanner.nextLine();

        try {
            int value = Integer.parseInt(input);
            // 음수 잡아내기
            if (value < 0) throw new NumberFormatException();
            return new Price(value);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("구입 금액은 숫자여야 합니다.");
        }
    }

    // 당첨 번호 + 보너스 번호 입력
    public WinningLotto inputWinningNumbers() {
        WinningLotto winningLotto = getWinningLotto();

        return getBonusNumber(winningLotto);
    }

    private WinningLotto getBonusNumber(WinningLotto winningLotto) {
        System.out.println("보너스 볼을 입력해 주세요.");

        while (true) {
            String bonusInput = scanner.nextLine();
            int bonusNumber;

            try {
                bonusNumber = Integer.parseInt(bonusInput);
                winningLotto.setBonusNumber(bonusNumber);
                return winningLotto;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private WinningLotto getWinningLotto() {
        WinningLotto winningLotto;

        System.out.println("지난 주 당첨 번호를 입력해 주세요.");
        while (true) {
            try {
                List<Integer> numbers = parseNumbers(scanner.nextLine());
                winningLotto = new WinningLotto(numbers);
                break;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        return winningLotto;
    }

    /**
     * "1, 2, 3, 4, 5, 6" → List<Integer>
     */
    private List<Integer> parseNumbers(String input) {
        try {
            return Arrays.stream(input.split(","))
                    .map(String::trim)
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("당첨 번호는 쉼표로 구분된 숫자여야 합니다.");
        }
    }
}