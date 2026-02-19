package lotto.controller;

import lotto.domain.*;
import lotto.domain.enums.LottoWinningInfo;
import lotto.view.Input;
import lotto.view.Output;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Controller
 */
public class Main {

    public static void main(String[] args) {
        // 입력 담당 객체
        Input input = new Input();

        // 구입 금액 입력
        Price price = readPrice(input);

        // 수동 로또 구매 갯수 입력 + 자동 로또 갯수 계산
        int manualCount = readManualCount(input, price.getLottoCount());
        int autoCount = price.getLottoCount() - manualCount;

        // 수동 & 자동 로또 갯수 바탕으로 실제 로또 발행 및 출력
        Lottos lottos = issueLottos(input, manualCount, autoCount);

        // 당첨 번호 & 보너스 번호 입력
        WinningLotto winningLotto = readWinningLotto(input);

        // 구매 로또와 당첨 번호 match -> LottoResult에 기록됨
        LottoResult result = lottos.match(winningLotto);

        // 당첨 통계 및 수익률 출력 - Controller 단에서 Domain을 의존하여 처리한 결과만 View로 던져줌
        Output.printLottoStatistics(createStatisticsText(result, price));
    }

    // 구입 금액을 입력받아 Price를 생성(예외 시 재입력)
    private static Price readPrice(Input input) {
        while (true) {
            try { return new Price(input.readPrice()); }
            catch (IllegalArgumentException e) { System.out.println(e.getMessage()); }
        }
    }

    // 수동 구매 개수를 입력받아 검증 후 반환(예외 시 재입력)
    private static int readManualCount(Input input, int maxCount) {
        while (true) {
            try { return validateManualCount(input.readManualCount(), maxCount); }
            catch (Exception e) { System.out.println(e.getMessage()); }
        }
    }

    // 수동 구매 개수 입력값 검증
    private static int validateManualCount(String input, int maxCount) {
        if (!input.matches("^-?\\d+$")) throw new IllegalArgumentException("숫자만 입력해 주세요.");
        int count = Integer.parseInt(input);
        if (count < 0) throw new IllegalArgumentException("0 이상을 입력해 주세요.");
        if (count > maxCount) throw new IllegalArgumentException(String.format("구입 금액으로는 최대 %d개까지만 수동 구매할 수 있어요.", maxCount));
        return count;
    }

    // 수동/자동 로또 발행 후 merge하고 출력한 뒤 반환
    private static Lottos issueLottos(Input input, int manualCount, int autoCount) {
        LottoVendingMachine machine = new LottoVendingMachine();
        Output.printRequestManulLottos();

        List<Lotto> manualList = readManualLottos(input, manualCount);
        Lottos manualLottos = machine.generateManualLottos(manualList);
        Lottos autoLottos = machine.genenrateAutoLottos(autoCount);

        Lottos merged = manualLottos.merge(autoLottos);
        Output.printLottos(manualCount, autoCount, merged);
        return merged;
    }

    // 수동 로또를 manualCount개 입력받아 Lotto 리스트로 변환
    private static List<Lotto> readManualLottos(Input input, int manualCount) {
        return IntStream.range(0, manualCount)
                .mapToObj(i -> readLotto(input))
                .toList();
    }

    // 로또 번호 1줄을 입력받아 Lotto 생성(예외 시 재입력)
    private static Lotto readLotto(Input input) {
        while (true) {
            try { return new Lotto(parseNumbers(input.readManualLotto())); }
            catch (IllegalArgumentException e) { System.out.println(e.getMessage()); }
        }
    }

    // 지난 주 당첨 번호 입력
    private static WinningLotto readWinningLotto(Input input) {
        Lotto winning = readWinning(input);          // 당첨 번호 6개
        LottoNumber bonus = readBonus(input, winning); // 보너스 입력
        return new WinningLotto(winning, bonus);
    }

    // 당첨 번호(6개)를 입력받아 Lotto 생성(예외 시 재입력)
    private static Lotto readWinning(Input input) {
        while (true) {
            try { return new Lotto(parseNumbers(input.readWinningNumbers())); }
            catch (IllegalArgumentException e) { System.out.println(e.getMessage()); }
        }
    }

    // 보너스 번호 입력 받기
    private static LottoNumber readBonus(Input input, Lotto winning) {
        while (true) {
            try {
                String inputBonus = input.readBonusNumber();
                if (!inputBonus.matches("^-?\\d+$")) { throw new IllegalArgumentException("숫자만 입력해 주세요.");}
                int n = Integer.parseInt(inputBonus);

                LottoNumber bonus = new LottoNumber(n);
                validateBonus(winning, bonus);

                return bonus;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    // 보너스 검증
    private static void validateBonus(Lotto winning, LottoNumber bonus) {
        if (winning.contains(bonus)) {
            throw new IllegalArgumentException("당첨 번호와 보너스 번호는 달라야 합니다. 보너스 번호를 다시 입력해주세요.");
        }
    }

    // "1, 2, 3, 4, 5, 6" 입력을 List<Integer>로 파싱
    private static List<Integer> parseNumbers(String input) {
        try {
            return Arrays.stream(input.split(","))
                    .map(String::trim)
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new IllegalArgumentException("쉼표로 구분된 숫자를 입력하세요.");
        }
    }

    // 통계 문자열 생성
    private static String createStatisticsText(LottoResult result, Price price) {
        StringBuilder sb = new StringBuilder("당첨 통계\n---------\n");

        appendLine(sb, LottoWinningInfo.MATCH_3, result);
        appendLine(sb, LottoWinningInfo.MATCH_4, result);
        appendLine(sb, LottoWinningInfo.MATCH_5, result);
        appendLine(sb, LottoWinningInfo.MATCH_5_BONUS, result);
        appendLine(sb, LottoWinningInfo.MATCH_6, result);

        float roi = result.getStatistics(price);
        return sb.append(String.format("총 수익률은 %.2f입니다.%n", roi)).toString();
    }

    // 각 당첨 등수별 통계 한 줄 추가
    private static void appendLine(StringBuilder sb, LottoWinningInfo info, LottoResult result) {
        sb.append(String.format("%s (%d원)- %d개%n",
                info.getDescription(),
                info.getPrize(),
                result.getPrizeCount(info)));
    }
}