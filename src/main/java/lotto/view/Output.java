package lotto.view;

import lotto.domain.*;

import java.util.stream.Collectors;

public class Output {

    // 입력 받은 금액으로 로또 몇 개 구매했고, 구매된 로또들의 각 번호 출력
    public static void printLottos(int manualCount, int autoCount, Lottos lottos) {
        System.out.printf("수동으로 %d장, 자동으로 %d개를 구매했습니다.%n", manualCount, autoCount);
        for (Lotto lotto : lottos.getLottos()) {
            System.out.println(
                    lotto.getLottoNumbers().stream()
                            .map(LottoNumber::getValue)
                            .map(String::valueOf)
                            .collect(Collectors.joining(", ", "[", "]"))
            );
        }
        System.out.println();
    }

    public static void printRequestManulLottos() { System.out.println("수동으로 구매할 번호를 입력해 주세요."); }

    public static void printLottoStatistics(String statisticsText) {
        System.out.println(statisticsText);
    }
}
