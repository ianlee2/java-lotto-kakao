package lotto.view;

import java.util.Scanner;

public class Input {

    private final Scanner scanner;

    public Input() {
        this(new Scanner(System.in));
    }

    public Input(Scanner scanner) {
        this.scanner = scanner;
    }

    public String readPrice() {
        System.out.println("구입금액을 입력해 주세요.");
        return scanner.nextLine();
    }

    public String readManualCount() {
        System.out.println("수동으로 구매할 로또 수를 입력해 주세요.");
        return scanner.nextLine();
    }

    public String readWinningNumbers() {
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");
        return scanner.nextLine();
    }

    public String readBonusNumber() {
        System.out.println("보너스 볼을 입력해 주세요.");
        return scanner.nextLine();
    }

    public String readManualLotto() {
        return scanner.nextLine();
    }
}