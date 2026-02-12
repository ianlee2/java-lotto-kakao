package lotto.controller;

import lotto.domain.*;
import lotto.domain.enums.LottoWinningInfo;
import lotto.view.Input;
import lotto.view.Output;

public class Main {
    public static void main(String[] args) {

        // 입력 담당 객체
        Input input = new Input();

        // 구입 금액 입력
        Price price = input.inputPrice();

        // 수동 로또 구매 갯수 입력
        int manualCount = input.inputManualCount(price.getLottoCount());

        // 구입 금액을 기준으로 로또 발급
        LottoVendingMachine machine = new LottoVendingMachine();
        Lottos lottos = machine.genenrateLottos(price.getLottoCount());

        // 구매한 로또 출력
        Output.printLottos(lottos);

        // 당첨 번호 & 보너스 번호 입력
        WinningLotto winningLotto = input.inputWinningNumbersAndBonusNumber();

        // 구매 로또와 당첨 번호 match -> LottoResult에 기록됨
        LottoResult result = lottos.match(winningLotto);

        // 당첨 통계 및 수익률 출력 - Controller 단에서 Domain을 의존하여 처리한 결과만 View로 던져줌.
        String statisticsText = createStatisticsText(result, price);

        // View에는 완성된 문자열만 전달
        Output.printLottoStatistics(statisticsText);
    }


    // 통계 문자열 생성 메서드
    private static String createStatisticsText(LottoResult result, Price price) {
        StringBuilder sb = new StringBuilder();
        sb.append("당첨 통계\n---------\n");

        appendLine(sb, LottoWinningInfo.MATCH_3, result);
        appendLine(sb, LottoWinningInfo.MATCH_4, result);
        appendLine(sb, LottoWinningInfo.MATCH_5, result);
        appendLine(sb, LottoWinningInfo.MATCH_5_BONUS, result);
        appendLine(sb, LottoWinningInfo.MATCH_6, result);

        float roi = result.getStatistics(price);
        String meaning = roi < 1.0f ? "손해" : "이득";

        return sb.append(String.format("총 수익률은 %.2f입니다.(기준이 1이기 때문에 결과적으로 %s라는 의미임)%n", roi, meaning)).toString();
    }

    private static void appendLine(StringBuilder sb,
                                   LottoWinningInfo info,
                                   LottoResult result) {
        sb.append(String.format("%s (%d원)- %d개%n",
                info.getDescription(),
                info.getPrize(),
                result.getPrizeCount(info)
        ));
    }
}