import java.util.HashMap;
import java.util.Map;

public class LottoResult {
    private final Map<LottoWinningInfo, Integer> result;

    public LottoResult() {
        result = new HashMap<>();
        result.put(LottoWinningInfo.MATCH_3, 0);
        result.put(LottoWinningInfo.MATCH_4, 0);
        result.put(LottoWinningInfo.MATCH_5, 0);
        result.put(LottoWinningInfo.MATCH_5_BONUS, 0);
        result.put(LottoWinningInfo.MATCH_6, 0);
    }


    public void addResult(int matchCount, boolean bonusMatch) {
        LottoWinningInfo lottoWinningInfo = LottoWinningInfo.getLottoWinningInfo(matchCount, bonusMatch);
        if (lottoWinningInfo != LottoWinningInfo.NONE) {
            result.put(lottoWinningInfo, result.get(lottoWinningInfo) + 1);
        }
    }

    public float getStatistics(Price price) {
        long totalProfit = 0;

        totalProfit += LottoWinningInfo.MATCH_6.getPrize() * result.get(LottoWinningInfo.MATCH_6);
        totalProfit += LottoWinningInfo.MATCH_5_BONUS.getPrize() * result.get(LottoWinningInfo.MATCH_5_BONUS);
        totalProfit += LottoWinningInfo.MATCH_5.getPrize() * result.get(LottoWinningInfo.MATCH_5);
        totalProfit += LottoWinningInfo.MATCH_4.getPrize() * result.get(LottoWinningInfo.MATCH_4);
        totalProfit += LottoWinningInfo.MATCH_3.getPrize() * result.get(LottoWinningInfo.MATCH_3);

        return (float) (totalProfit / price.getValue());
    }

    public Integer getPrizeCount(LottoWinningInfo lottoWinningInfo) {
        return result.get(lottoWinningInfo);
    }
}
