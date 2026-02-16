package lotto.view;

import lotto.domain.Lotto;
import lotto.domain.LottoNumber;
import lotto.domain.Price;
import lotto.domain.WinningLotto;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
        while (true) {
            try {
                String input = scanner.nextLine();
                return new Price(input); // 여기서 예외 발생 가능
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                // 다시 반복 → 재입력
            }
        }
    }

    // 수동 로또 구매 수 입력
    public int inputManualCount(int maxCount) {
        System.out.println("수동으로 구매할 로또 수를 입력해 주세요.");
        while (true) {
            try {
                String input = scanner.nextLine();
                return validateManualCount(input, maxCount);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }


    }

    private int validateManualCount(String input, int maxCount) {

        if (!input.matches("^-?\\d+$")) { throw new IllegalArgumentException("숫자만 입력해 주세요."); }

        int count = Integer.parseInt(input);
        if (count < 0) { throw new IllegalArgumentException("0 이상을 입력해 주세요."); }
        if (count > maxCount) { throw new IllegalArgumentException(String.format("구입 금액으로는 최대 %d개까지만 수동 구매할 수 있어요.", maxCount)); }

        return count;
    }


    // 당첨 번호 + 보너스 번호 입력
    public WinningLotto inputWinningNumbersAndBonusNumber() {
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");
        Lotto winningLotto = generateLotto();

        System.out.println("보너스 볼을 입력해 주세요.");
        while (true) {
            try {
                LottoNumber bonusNumber = getBonusNumber();

                return new WinningLotto(winningLotto, bonusNumber);

            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }


    private LottoNumber getBonusNumber() {
        while (true) {
            String bonusInput = scanner.nextLine();
            int bonusNumber;

            try {
                bonusNumber = Integer.parseInt(bonusInput);
                return new LottoNumber(bonusNumber);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    // 구매하고자 하는 수동 로또 갯수만큼 Lotto 생성
    public List<Lotto> inputManualLotto(int manualLottoCount) {
        return IntStream.range(0, manualLottoCount)
                .mapToObj(i -> generateLotto())
                .toList();
    }

    private Lotto generateLotto() {
        while (true) {
            try {
                List<Integer> nums = (parseNumbers(scanner.nextLine()));
                return new Lotto(nums);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
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