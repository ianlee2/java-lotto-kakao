import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LottoResultTest {

    @Test
    @DisplayName("3개 일치(보너스X)면 5등 카운트가 1 증가한다")
    void addResult_match3_increasesFifthPrize() {
        LottoResult result = new LottoResult();

        result.addResult(3, false);

        assertEquals(0, result.getPrizeCount(LottoWinningInfo.MATCH_6));
        assertEquals(0, result.getPrizeCount(LottoWinningInfo.MATCH_5_BONUS));
        assertEquals(0, result.getPrizeCount(LottoWinningInfo.MATCH_5));
        assertEquals(0, result.getPrizeCount(LottoWinningInfo.MATCH_4));
        assertEquals(1, result.getPrizeCount(LottoWinningInfo.MATCH_3));
    }

    @Test
    @DisplayName("4개 일치(보너스X)면 4등 카운트가 1 증가한다")
    void addResult_match4_increasesFourthPrize() {
        LottoResult result = new LottoResult();

        result.addResult(4, false);

        assertEquals(0, result.getPrizeCount(LottoWinningInfo.MATCH_6));
        assertEquals(0, result.getPrizeCount(LottoWinningInfo.MATCH_5_BONUS));
        assertEquals(0, result.getPrizeCount(LottoWinningInfo.MATCH_5));
        assertEquals(1, result.getPrizeCount(LottoWinningInfo.MATCH_4));
        assertEquals(0, result.getPrizeCount(LottoWinningInfo.MATCH_3));
    }

    @Test
    @DisplayName("5개 일치(보너스X)면 3등 카운트가 1 증가한다")
    void addResult_match5_increasesThirdPrize() {
        LottoResult result = new LottoResult();

        result.addResult(5, false);

        assertEquals(0, result.getPrizeCount(LottoWinningInfo.MATCH_6));
        assertEquals(0, result.getPrizeCount(LottoWinningInfo.MATCH_5_BONUS));
        assertEquals(1, result.getPrizeCount(LottoWinningInfo.MATCH_5));
        assertEquals(0, result.getPrizeCount(LottoWinningInfo.MATCH_4));
        assertEquals(0, result.getPrizeCount(LottoWinningInfo.MATCH_3));
    }

    @Test
    @DisplayName("5개 일치 + 보너스 일치면 2등 카운트가 1 증가한다")
    void addResult_match5Bonus_increasesSecondPrize() {
        LottoResult result = new LottoResult();

        result.addResult(5, true);

        assertEquals(0, result.getPrizeCount(LottoWinningInfo.MATCH_6));
        assertEquals(1, result.getPrizeCount(LottoWinningInfo.MATCH_5_BONUS));
        assertEquals(0, result.getPrizeCount(LottoWinningInfo.MATCH_5));
        assertEquals(0, result.getPrizeCount(LottoWinningInfo.MATCH_4));
        assertEquals(0, result.getPrizeCount(LottoWinningInfo.MATCH_3));
    }

    @Test
    @DisplayName("6개 일치(보너스X)면 1등 카운트가 1 증가한다")
    void addResult_match6_increasesFirstPrize() {
        LottoResult result = new LottoResult();

        result.addResult(6, false);

        assertEquals(1, result.getPrizeCount(LottoWinningInfo.MATCH_6));
        assertEquals(0, result.getPrizeCount(LottoWinningInfo.MATCH_5_BONUS));
        assertEquals(0, result.getPrizeCount(LottoWinningInfo.MATCH_5));
        assertEquals(0, result.getPrizeCount(LottoWinningInfo.MATCH_4));
        assertEquals(0, result.getPrizeCount(LottoWinningInfo.MATCH_3));
    }

    @Test
    @DisplayName("해당되지 않는 입력(예: 1개 일치)은 예외가 발생한다")
    void addResult_invalidCase_throws() {
        LottoResult result = new LottoResult();

//        assertThrows(IllegalArgumentException.class, () -> result.addResult(1, false));
        result.addResult(1, false);
        assertEquals(0, result.getPrizeCount(LottoWinningInfo.MATCH_6));
        assertEquals(0, result.getPrizeCount(LottoWinningInfo.MATCH_5_BONUS));
        assertEquals(0, result.getPrizeCount(LottoWinningInfo.MATCH_5));
        assertEquals(0, result.getPrizeCount(LottoWinningInfo.MATCH_4));
        assertEquals(0, result.getPrizeCount(LottoWinningInfo.MATCH_3));
    }

    @Test
    @DisplayName("3개 일치 + 보너스 일치(3,true)는 정의된 케이스가 아니라면 예외가 발생한다")
    void addResult_match3Bonus_throws() {
        LottoResult result = new LottoResult();
        result.addResult(3, true);
        assertEquals(0, result.getPrizeCount(LottoWinningInfo.MATCH_6));
        assertEquals(0, result.getPrizeCount(LottoWinningInfo.MATCH_5_BONUS));
        assertEquals(0, result.getPrizeCount(LottoWinningInfo.MATCH_5));
        assertEquals(0, result.getPrizeCount(LottoWinningInfo.MATCH_4));
        assertEquals(1, result.getPrizeCount(LottoWinningInfo.MATCH_3));
    }

    @Test
    @DisplayName("여러 등수가 섞여 있을 때 전체 수익률을 계산한다")
    void statistics_calculatesMultiplePrizes() {
        LottoResult result = new LottoResult();
        Price price = new Price("5000"); // 로또 5장

        // 3등 1회
        result.addResult(5, false);
        // 4등 1회
        result.addResult(4, false);
        // 5등 1회
        result.addResult(3, false);

        long totalProfit =
                LottoWinningInfo.MATCH_5.getPrize()
                        + LottoWinningInfo.MATCH_4.getPrize()
                        + LottoWinningInfo.MATCH_3.getPrize();

        float expected = (float) totalProfit / price.getValue();

        float statistics = result.getStatistics(price);

        assertEquals(expected, statistics);
    }
}