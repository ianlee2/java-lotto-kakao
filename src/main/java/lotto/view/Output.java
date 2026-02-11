package lotto.view;

import lotto.domain.*;
import lotto.domain.enums.LottoWinningInfo;

import java.util.stream.Collectors;

public class Output {

    // 입력 받은 금액으로 로또 몇 개 구매했고, 구매된 로또들의 각 번호 출력
    public static void printLottos(Lottos lottos) {
        System.out.printf("%d개를 구매했습니다.\n", lottos.size());
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

    public static void printLottoStatistics(String statisticsText) {
        System.out.println(statisticsText);
    }
}
