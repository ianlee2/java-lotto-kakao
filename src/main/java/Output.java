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

    // 로또
    public static void printLottoStatistics(LottoResult result, Price price) {
        System.out.println("당첨 통계");
        System.out.println("---------");

        // 3개 / 4개 / 5개 / 5+보너스 / 6개 순으로 출력
        System.out.println(formatLine(LottoWinningInfo.MATCH_3.getDescription(), LottoWinningInfo.MATCH_3.getPrize(), result.getPrizeCount(LottoWinningInfo.MATCH_3)));
        System.out.println(formatLine(LottoWinningInfo.MATCH_4.getDescription(), LottoWinningInfo.MATCH_4.getPrize(), result.getPrizeCount(LottoWinningInfo.MATCH_4)));
        System.out.println(formatLine(LottoWinningInfo.MATCH_5.getDescription(), LottoWinningInfo.MATCH_5.getPrize(), result.getPrizeCount(LottoWinningInfo.MATCH_5)));
        System.out.println(formatLineWithBonus(LottoWinningInfo.MATCH_5_BONUS.getDescription(), LottoWinningInfo.MATCH_5_BONUS.getPrize(), result.getPrizeCount(LottoWinningInfo.MATCH_5_BONUS)));
        System.out.println(formatLine(LottoWinningInfo.MATCH_6.getDescription(), LottoWinningInfo.MATCH_6.getPrize(), result.getPrizeCount(LottoWinningInfo.MATCH_6)));

        float roi = result.getStatistics(price);

        String meaning = roi < 1.0f ? "손해" : "이득";
        System.out.printf("총 수익률은 %.2f입니다.(기준이 1이기 때문에 결과적으로 %s라는 의미임)%n", roi, meaning);
    }

    private static String formatLine(String title, long prize, int count) {
        return String.format("%s (%d원)- %d개", title, prize, count);
    }

    private static String formatLineWithBonus(String title, long prize, int count) {
        return String.format("%s(%d원) - %d개", title, prize, count);
    }


}
